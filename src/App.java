import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.Scanner;

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
        String routerPath, routerLinkList, routerLinkInsert, routerLinkUpdate, routerImportComponentList, routerImportComponentInsert, routerImportComponentUpdate;
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
            HandyManUtils.extractDir(Constantes.DATA_PATH+"/"+language.getSkeleton()+"."+Constantes.SKELETON_EXTENSION, project.getPath());
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
                for(int i=0;i<entities.length;i++){
                    entities[i].initialize(connect, credentials, database, language);
                }
                models=new String[entities.length];
                controllers=new String[entities.length];
                views=new String[entities.length][language.getArchitecture().getView().getContentViews().length];
                navLink="";
                navLinkInsert="";
                routerLinkList="";
                routerLinkInsert=""; 
                routerLinkUpdate="";
                routerImportComponentList="";
                routerImportComponentInsert="";
                routerImportComponentUpdate="";
                for(int i=0;i<models.length;i++){
                    models[i]=language.generateModel(entities[i], projectName);
                    controllers[i]=language.generateController(entities[i], database, credentials, projectName);
                    views[i]=language.generateView(entities[i], projectName);
                    modelFile=language.getModel().getModelSavePath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    controllerFile=language.getArchitecture().getController().getControllerSavepath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    
                    modelFile=modelFile.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    controllerFile=controllerFile.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    modelFile+="/"+HandyManUtils.majStart(entities[i].getClassName())+"."+language.getModel().getModelExtension();
                    controllerFile+="/"+HandyManUtils.majStart(entities[i].getClassName())+language.getArchitecture().getController().getControllerNameSuffix()+"."+language.getArchitecture().getController().getControllerExtension();
                    for(int j=0; j<views[i].length; j++) {
                        viewFile=language.getArchitecture().getView().getViewSavePath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        viewFile=viewFile.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        if(frontProject!=null) {
                            viewFile=viewFile.replace("[projectFront]", frontProject);
                        }
                        viewFile=viewFile.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        // System.out.println(viewFile);
                        viewFile=viewFile.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        viewFile+="/"+language.getArchitecture().getView().getContentViews()[j].getViewName()+"."+language.getArchitecture().getView().getViewExtension();
                        System.out.println(viewFile);
                        HandyManUtils.createFile(viewFile);
                        viewFile=viewFile.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        viewFile=viewFile.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        HandyManUtils.overwriteFileContent(viewFile, views[i][j]);
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
                        customFileContent=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+f.getContent()).replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        customFileContent=customFileContent.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        customFileContent=customFileContent.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        customFileContent=customFileContent.replace("[databaseHost]", credentials.getHost());
                        customFileContent=customFileContent.replace("[databaseName]", credentials.getDatabaseName());
                        customFileContent=customFileContent.replace("[user]", credentials.getUser());
                        customFileContent=customFileContent.replace("[pwd]", credentials.getPwd());
                        customFileContent=customFileContent.replace("[modelForeignContextAttr]", foreignContext);
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
                    if(language.getArchitecture().getView().getRouter()!=null) {routerLinkList+=language.getArchitecture().getView().getRouter().getLinkList();
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
                        routerImportComponentList+=language.getArchitecture().getView().getRouter().getComponentImportList();
                        routerImportComponentList=routerImportComponentList.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                        routerImportComponentList=routerImportComponentList.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                        routerImportComponentList=routerImportComponentList.replace("[classNameMin]", HandyManUtils.minStart(entities[i].getClassName()));
                        routerImportComponentList=routerImportComponentList.replace("[classNameMaj]", HandyManUtils.majStart(entities[i].getClassName()));
                        routerImportComponentList=routerImportComponentList.replace("[classNameformattedMin]", HandyManUtils.minStart(HandyManUtils.formatReadable(entities[i].getClassName())));
                        routerImportComponentList=routerImportComponentList.replace("[classNameformattedMaj]", HandyManUtils.majStart(HandyManUtils.formatReadable(entities[i].getClassName())));
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
                System.out.println(navLinkPath);
                HandyManUtils.overwriteFileContent(navLinkPath, HandyManUtils.getFileContent(navLinkPath).replace("[navbarLinks]", navLink));
                HandyManUtils.overwriteFileContent(navLinkPath, HandyManUtils.getFileContent(navLinkPath).replace("[navbarLinksInsert]", navLinkInsert));
                if(language.getArchitecture().getView().getRouter()!=null) {
                    routerPath=language.getArchitecture().getView().getRouter().getPath().replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
                    routerPath=routerPath.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
                    routerPath=routerPath.replace("[projectFront]", frontProject);
                    System.out.println(routerPath);
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerLinkList]", routerLinkList));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerLinkInsert]", routerLinkInsert));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerLinkUpdate]", routerLinkUpdate));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[routerComponentImportList]", routerImportComponentList));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[componentImportInsert]", routerImportComponentInsert));
                    HandyManUtils.overwriteFileContent(routerPath, HandyManUtils.getFileContent(routerPath).replace("[componentImportUpdate]", routerImportComponentUpdate));
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
