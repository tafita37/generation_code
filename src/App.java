import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import genesis.Constantes;
import genesis.Credentials;
import genesis.CustomChanges;
import genesis.CustomFile;
import genesis.Database;
import genesis.Entity;
import genesis.EntityField;
import genesis.Language;
import handyman.HandyManUtils;
public class App {

    public static void extractZipFile(String zipFilePath, String targetDirectory) throws IOException {
        byte[] buffer = new byte[1024];

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String entryName = zipEntry.getName();
                File newFile = new File(targetDirectory + File.separator + entryName);

                // Create directories if the entry is a directory
                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    // Create parent directories if not exist
                    newFile.getParentFile().mkdirs();

                    // Extract file content
                    try (FileOutputStream outputStream = new FileOutputStream(newFile)) {
                        int length;
                        while ((length = zipInputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, length);
                        }
                    }
                }

                // Move to next entry
                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }

    private static void copyDirectory(Path source, Path target) throws IOException {
        Files.walk(source)
             .forEach(sourcePath -> {
                 try {
                     Path relativePath = source.relativize(sourcePath);
                     Path targetPath = target.resolve(relativePath);

                     // Copie avec remplacement si déjà existant
                     Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             });
    }
    public static void main(String[] args) throws Exception {
        System.out.println(HandyManUtils.majStart("user_name"));
        Database[] databases=HandyManUtils.fromJson(Database[].class, HandyManUtils.getFileContent(Constantes.DATABASE_JSON));
        Language[] languages=HandyManUtils.fromJson(Language[].class, HandyManUtils.getFileContent(Constantes.LANGUAGE_JSON));
        Database database;
        Language language;
        String databaseName, user, pwd, host;
        boolean useSSL, allowPublicKeyRetrieval;
        String projectName, entityName, frontProject=null;
        Credentials credentials;
        String projectNameTagPath, projectNameTagContent;
        File project, credentialFile, frontProjectFile;
        String customFilePath, customFileContentOuter;
        Entity[] entities;
        String[] models, controllers;
        String[][] views;
        String modelFile, controllerFile, viewFile, customFile;
        String customFileContent;
        String foreignContext;
        String customChanges, changesFile;
        String navLink, navLinkPath, navLinkInsert;
        String routerPath, routerLinkList, routerLinkInsert, routerLinkUpdate, 
        routerImportComponentList, routerImportComponentInsert, routerImportComponentUpdate, routerLinkLogin, 
        routerImportComponentLogin;
        int nbLignePage;
        try(Scanner scanner=new Scanner(System.in)){
            System.out.println("Choose a database engine:");
            for(int i=0;i<databases.length;i++){
                System.out.println((i+1)+") "+databases[i].getNom());
            }
            System.out.print("> ");
            database=databases[scanner.nextInt()-1];
            System.out.println("Choose a framework:");
            for(int i=0;i<languages.length;i++){
                System.out.println((i+1)+") "+languages[i].getNom());
            }
            System.out.print("> ");
            language=languages[scanner.nextInt()-1];
            System.out.println("Choose an architecture:");
            for(int i=0; i<language.getListeArchitecture().length; i++) {
                System.out.println((i+1)+") "+language.getListeArchitecture()[i].getNomArchitecture());
            }
            System.out.print("> ");
            language.setArchitecture(language.getListeArchitecture()[scanner.nextInt()-1]);
            System.out.println("Choose a techno view:");
            if(language.getArchitecture().getView().getListeTechnoView()!=null) {
                for(int i=0; i<language.getArchitecture().getView().getListeTechnoView().length; i++) {
                    System.out.println((i+1)+") "+language.getArchitecture().getView().getListeTechnoView()[i].getNameTechno());
                }
                System.out.print("> ");
                language.getArchitecture().getView().setTechnoView(language.getArchitecture().getView().getListeTechnoView()[scanner.nextInt()-1]);
                System.out.print("Choose the front project name : ");
                frontProject=scanner.next();
                frontProjectFile=new File(frontProject);
                File source=new File(Constantes.DATA_PATH+"/"+language.getArchitecture().getView().getTechnoView().getViewSkeleton());
                copyDirectory(source.toPath(), frontProjectFile.toPath());
            }
            System.out.println("Enter your database credentials:");
            System.out.print("Database name: ");
            databaseName=scanner.next();
            System.out.print("Username: ");
            user=scanner.next();
            System.out.print("Password: ");
            pwd=scanner.next();
            System.out.print("Database host: ");
            host=scanner.next();
            System.out.print("Use SSL ?(Y/n): ");
            useSSL=scanner.next().equalsIgnoreCase("Y");
            System.out.print("Allow public key retrieval ?(Y/n): ");
            allowPublicKeyRetrieval=scanner.next().equalsIgnoreCase("Y");
            System.out.println();
            System.out.print("Enter your project name: ");
            projectName=scanner.next();
            System.out.print("Which entities to import ?(* to select all): ");
            entityName=scanner.next();
            System.out.print("Nombre de ligne d'une page : ");
            nbLignePage=scanner.nextInt();
            credentials=new Credentials(databaseName, user, pwd, host, useSSL, allowPublicKeyRetrieval);
            project=new File(projectName);
            project.mkdir();
            for(CustomFile c:language.getAdditionnalFiles()){
                customFilePath=c.getName();
                customFilePath=customFilePath.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                HandyManUtils.createFile(customFilePath);
                customFileContentOuter=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+c.getContent()).replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                HandyManUtils.overwriteFileContent(customFilePath, customFileContentOuter);
            }
            extractZipFile(Constantes.DATA_PATH+"\\"+language.getSkeleton()+"."+Constantes.SKELETON_EXTENSION, project.getPath());
            // System.out.println(Constantes.DATA_PATH+"\\"+language.getSkeleton()+"."+Constantes.SKELETON_EXTENSION);
            credentialFile=new File(project.getPath(), Constantes.CREDENTIAL_FILE);
            credentialFile.createNewFile();
            HandyManUtils.overwriteFileContent(credentialFile.getPath(), HandyManUtils.toJson(credentials));
            for(String replace:language.getProjectNameTags()){
                projectNameTagPath=replace.replace("[projectNameMaj]", HandyManUtils.majStart(projectName)).replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                projectNameTagContent=HandyManUtils.getFileContent(projectNameTagPath).replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                projectNameTagContent=projectNameTagContent.replace("[databaseHost]", credentials.getHost());
                projectNameTagContent=projectNameTagContent.replace("[databasePort]", database.getPort());
                projectNameTagContent=projectNameTagContent.replace("[databaseName]", credentials.getDatabaseName());
                projectNameTagContent=projectNameTagContent.replace("[user]", credentials.getUser());
                projectNameTagContent=projectNameTagContent.replace("[pwd]", credentials.getPwd());
                projectNameTagContent=projectNameTagContent.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                HandyManUtils.overwriteFileContent(projectNameTagPath, projectNameTagContent);
            }
            try(Connection connect=database.getConnexion(credentials)){
                entities=database.getEntities(connect, credentials, entityName);
                System.out.print("Table de securite : ");
                String tableLogin=scanner.next();
                System.out.print("User name column : ");
                String userNameColumn=scanner.next();
                System.out.print("Password column : ");
                String paswordColumn=scanner.next();
                for(int i=0;i<entities.length;i++){
                    entities[i].initialize(connect, credentials, database, language, tableLogin, userNameColumn, paswordColumn);
                }
                models=new String[entities.length];
                controllers=new String[entities.length];
                views=new String[entities.length][language.getArchitecture().getView().getContentViews().length];
                navLink="";
                navLinkInsert="";
                routerLinkList="";
                routerLinkLogin="";
                routerLinkInsert=""; 
                routerLinkUpdate="";
                routerImportComponentList="";
                routerImportComponentLogin="";
                routerImportComponentInsert="";
                routerImportComponentUpdate="";
                Entity loginEntity=null;
                EntityField userNameField=null;
                for(int i=0;i<models.length;i++){
                    models[i]=language.generateModel(entities[i], projectName, tableLogin, userNameColumn, paswordColumn);
                    controllers[i]=language.generateController(entities[i], database, credentials, projectName, nbLignePage, tableLogin);
                    views[i]=language.generateView(entities[i], projectName, tableLogin);
                    modelFile=language.getModel().getModelSavePath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    controllerFile=language.getArchitecture().getController().getControllerSavepath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    
                    modelFile=modelFile.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    controllerFile=controllerFile.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    modelFile+="/"+HandyManUtils.majStart(entities[i].getClassName())+"."+language.getModel().getModelExtension();
                    controllerFile+="/"+HandyManUtils.majStart(entities[i].getClassName())+language.getArchitecture().getController().getControllerNameSuffix()+"."+language.getArchitecture().getController().getControllerExtension();
                    for(int j=0; j<language.getArchitecture().getView().getContentViews().length; j++) {
                        viewFile=language.getArchitecture().getView().getViewSavePath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        viewFile=viewFile.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        if(frontProject!=null) {
                            viewFile=viewFile.replace("[projectFront]", frontProject);
                        }
                        viewFile=viewFile.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        // System.out.println(viewFile);
                        viewFile=viewFile.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        viewFile+="/"+language.getArchitecture().getView().getContentViews()[j].getViewName()+"."+language.getArchitecture().getView().getViewExtension();
                        // System.out.println(viewFile);
                        HandyManUtils.createFile(viewFile);
                        viewFile=viewFile.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        viewFile=viewFile.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        HandyManUtils.overwriteFileContent(viewFile, views[i][j]);
                    }
                    if(views[i].length!=language.getArchitecture().getView().getContentViews().length) {
                        viewFile=language.getArchitecture().getView().getViewSavePath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        viewFile=viewFile.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        if(frontProject!=null) {
                            viewFile=viewFile.replace("[projectFront]", frontProject);
                        }
                        viewFile=viewFile.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        // System.out.println(viewFile);
                        viewFile=viewFile.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        viewFile+="/"+language.getArchitecture().getView().getContentLogin().getViewName()+"."+language.getArchitecture().getView().getViewExtension();
                        // System.out.println(viewFile);
                        HandyManUtils.createFile(viewFile);
                        viewFile=viewFile.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        viewFile=viewFile.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        HandyManUtils.overwriteFileContent(viewFile, views[i][views[i].length-1]);
                    }
                    HandyManUtils.createFile(modelFile);
                    for(CustomFile f:language.getModel().getModelAdditionnalFiles()){
                        foreignContext="";
                        for(EntityField ef:entities[i].getFields()){
                            if(ef.isForeign()){
                                foreignContext+=language.getModel().getModelForeignContextAttr();
                                foreignContext=foreignContext.replace("[classNameMaj]", HandyManUtils.majStart(ef.getType()));
                            }
                        }
                        customFile=f.getName().replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        customFile=language.getModel().getModelSavePath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName))+"/"+customFile;
                        customFile=customFile.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        customFileContent=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+f.getContent());
                        customFileContent=customFileContent.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        customFileContent=customFileContent.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        customFileContent=customFileContent.replace("[databaseHost]", credentials.getHost());
                        customFileContent=customFileContent.replace("[databaseName]", credentials.getDatabaseName());
                        customFileContent=customFileContent.replace("[user]", credentials.getUser());
                        customFileContent=customFileContent.replace("[pwd]", credentials.getPwd());
                        customFileContent=customFileContent.replace("[modelForeignContextAttr]", foreignContext);
                        if(entities[i].getTableName().compareTo(tableLogin)!=0) {
                            customFileContent=customFileContent.replace("public Optional<[classNameMaj]> findBy[fieldUserNameMaj](String userName);", "");
                        } else {
                            loginEntity=entities[i];
                            for(EntityField ef:entities[i].getFields()){
                                if(ef.isUserName()) {
                                    customFileContent=customFileContent.replace("[fieldUserNameMaj]", HandyManUtils.majStart(ef.getName()));
                                    userNameField=ef;
                                }
                            }
                        } 
                        customFileContent=customFileContent.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        HandyManUtils.createFile(customFile);
                        HandyManUtils.overwriteFileContent(customFile, customFileContent);
                    }
                    HandyManUtils.createFile(controllerFile);
                    HandyManUtils.overwriteFileContent(modelFile, models[i]);
                    HandyManUtils.overwriteFileContent(controllerFile, controllers[i]);
                    navLink+=language.getArchitecture().getView().getNavbarLinks().getLink();
                    navLink=navLink.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    navLink=navLink.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    navLink=navLink.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                    navLink=navLink.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                    navLink=navLink.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                    navLink=navLink.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                    navLinkInsert+=language.getArchitecture().getView().getNavbarLinks().getLinkInsert();
                    navLinkInsert=navLinkInsert.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    navLinkInsert=navLinkInsert.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    navLinkInsert=navLinkInsert.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                    navLinkInsert=navLinkInsert.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                    navLinkInsert=navLinkInsert.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                    navLinkInsert=navLinkInsert.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                    if(language.getArchitecture().getView().getRouter()!=null) {
                        routerLinkList+=language.getArchitecture().getView().getRouter().getLinkList();
                        routerLinkList=routerLinkList.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        routerLinkList=routerLinkList.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        routerLinkList=routerLinkList.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        routerLinkList=routerLinkList.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        routerLinkList=routerLinkList.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerLinkList=routerLinkList.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerLinkInsert+=language.getArchitecture().getView().getRouter().getLinkInsert();
                        routerLinkInsert=routerLinkInsert.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        routerLinkInsert=routerLinkInsert.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        routerLinkInsert=routerLinkInsert.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        routerLinkInsert=routerLinkInsert.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        routerLinkInsert=routerLinkInsert.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerLinkInsert=routerLinkInsert.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerLinkUpdate+=language.getArchitecture().getView().getRouter().getLinkUpdate();
                        routerLinkUpdate=routerLinkUpdate.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        routerLinkUpdate=routerLinkUpdate.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        routerLinkUpdate=routerLinkUpdate.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        routerLinkUpdate=routerLinkUpdate.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        routerLinkUpdate=routerLinkUpdate.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerLinkUpdate=routerLinkUpdate.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        if(entities[i].getUserNameColumn()!=null) {
                            routerLinkLogin+=language.getArchitecture().getView().getRouter().getLinkLogin();
                            routerLinkLogin=routerLinkLogin.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                            routerLinkLogin=routerLinkLogin.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                            routerLinkLogin=routerLinkLogin.replace("[classNameMin]", HandyManUtils.minStart(loginEntity.getClassName()));
                            routerLinkLogin=routerLinkLogin.replace("[classNameMaj]", HandyManUtils.majStart(loginEntity.getClassName()));
                        }
                        routerLinkLogin=routerLinkLogin.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerLinkLogin=routerLinkLogin.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerImportComponentList+=language.getArchitecture().getView().getRouter().getComponentImportList();
                        routerImportComponentList=routerImportComponentList.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        routerImportComponentList=routerImportComponentList.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        routerImportComponentList=routerImportComponentList.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        routerImportComponentList=routerImportComponentList.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        routerImportComponentList=routerImportComponentList.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerImportComponentList=routerImportComponentList.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        if(entities[i].getUserNameColumn()!=null) {
                            routerImportComponentLogin+=language.getArchitecture().getView().getRouter().getComponentImportLogin();
                            routerImportComponentLogin=routerImportComponentLogin.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                            routerImportComponentLogin=routerImportComponentLogin.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                            routerImportComponentLogin=routerImportComponentLogin.replace("[classNameMin]", HandyManUtils.minStart(loginEntity.getClassName()));
                            routerImportComponentLogin=routerImportComponentLogin.replace("[classNameMaj]", HandyManUtils.majStart(loginEntity.getClassName()));
                        }
                        routerImportComponentLogin=routerImportComponentLogin.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerImportComponentLogin=routerImportComponentLogin.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerImportComponentInsert+=language.getArchitecture().getView().getRouter().getComponentImportInsert();
                        routerImportComponentInsert=routerImportComponentInsert.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        routerImportComponentInsert=routerImportComponentInsert.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        routerImportComponentInsert=routerImportComponentInsert.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        routerImportComponentInsert=routerImportComponentInsert.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        routerImportComponentInsert=routerImportComponentInsert.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerImportComponentInsert=routerImportComponentInsert.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerImportComponentUpdate+=language.getArchitecture().getView().getRouter().getComponentImportUpdate();
                        routerImportComponentUpdate=routerImportComponentUpdate.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        routerImportComponentUpdate=routerImportComponentUpdate.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        routerImportComponentUpdate=routerImportComponentUpdate.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        routerImportComponentUpdate=routerImportComponentUpdate.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        routerImportComponentUpdate=routerImportComponentUpdate.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerImportComponentUpdate=routerImportComponentUpdate.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                    }
                }
                navLinkPath=language.getArchitecture().getView().getNavbarLinks().getPath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                navLinkPath=navLinkPath.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                navLinkPath=navLinkPath.replace("[projectFront]", frontProject);
                // System.out.println(navLinkPath);
                for(int i=0; i<language.getArchitecture().getView().getPathSecurityConfig().length; i++) {
                    String securityPath=language.getArchitecture().getView().getPathSecurityConfig()[i].replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    String securityContent = HandyManUtils.getFileContent(securityPath);
                    securityContent=securityContent.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    securityContent=securityContent.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    securityContent=securityContent.replace("[classNameMaj]", HandyManUtils.majStart(loginEntity.getClassName()));
                    securityContent=securityContent.replace("[fieldUserNameMaj]", HandyManUtils.majStart(userNameField.getName()));
                    securityContent=securityContent.replace("[classLoginMin]", HandyManUtils.minStart(loginEntity.getClassName()));
                    securityContent=securityContent.replace("[classLoginMaj]", HandyManUtils.majStart(loginEntity.getClassName()));
                    HandyManUtils.overwriteFileContent(securityPath, securityContent);
                }
                HandyManUtils.overwriteFileContent(navLinkPath, HandyManUtils.getFileContent(navLinkPath).replace("[navbarLinks]", navLink));
                HandyManUtils.overwriteFileContent(navLinkPath, HandyManUtils.getFileContent(navLinkPath).replace("[navbarLinksInsert]", navLinkInsert));
                HandyManUtils.overwriteFileContent(navLinkPath, HandyManUtils.getFileContent(navLinkPath).replace("[classNameLoginMaj]", HandyManUtils.majStart(loginEntity.getClassName())));
                if(language.getArchitecture().getView().getRouter()!=null) {
                    routerPath=language.getArchitecture().getView().getRouter().getPath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    routerPath=routerPath.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    routerPath=routerPath.replace("[projectFront]", frontProject);
                    // System.out.println(routerPath);
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerLinkLogin]", routerLinkLogin));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerLinkList]", routerLinkList));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerLinkInsert]", routerLinkInsert));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerLinkUpdate]", routerLinkUpdate));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerComponentImportList]", routerImportComponentList));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[componentImportInsert]", routerImportComponentInsert));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[componentImportUpdate]", routerImportComponentUpdate));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[componentImportLogin]", routerImportComponentLogin));
                }
                for(CustomChanges c:language.getCustomChanges()){
                    customChanges="";
                    for(Entity e:entities){
                        customChanges+=c.getChanges();
                        customChanges=customChanges.replace("[classNameMaj]", HandyManUtils.majStart(e.getClassName()));
                        customChanges=customChanges.replace("[classNameMin]", HandyManUtils.minStart(e.getClassName()));
                        customChanges=customChanges.replace("[databaseHost]", credentials.getHost());
                        customChanges=customChanges.replace("[user]", credentials.getUser());
                        customChanges=customChanges.replace("[databaseName]", credentials.getDatabaseName());
                        customChanges=customChanges.replace("[pwd]", credentials.getPwd());
                    }
                    if(c.isWithEndComma()==false){
                        customChanges=customChanges.substring(0,customChanges.length()-1);
                    }
                    changesFile=c.getPath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    HandyManUtils.overwriteFileContent(changesFile, HandyManUtils.getFileContent(changesFile).replace("[customChanges]", customChanges));
                }
            }
        }
    }
}
