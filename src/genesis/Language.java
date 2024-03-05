package genesis;

import java.io.IOException;
import java.util.HashMap;
import handyman.HandyManUtils;

public class Language {
    private int id;
    private String nom;
    private HashMap<String, String> syntax;
    private HashMap<String, String> types, typeParsers;
    private String skeleton;
    private String[] projectNameTags;
    private CustomFile[] additionnalFiles;
    private Architecture[] listeArchitecture;
    private Architecture architecture;
    private Model model;
    // private Controller controller;
    // private View view;
    private CustomChanges[] customChanges;
    public CustomChanges[] getCustomChanges() {
        return customChanges;
    }
    public void setCustomChanges(CustomChanges[] customChanges) {
        this.customChanges = customChanges;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public HashMap<String, String> getSyntax() {
        return syntax;
    }
    public void setSyntax(HashMap<String, String> syntax) {
        this.syntax = syntax;
    }
    public HashMap<String, String> getTypes() {
        return types;
    }
    public void setTypes(HashMap<String, String> types) {
        this.types = types;
    }
    public HashMap<String, String> getTypeParsers() {
        return typeParsers;
    }
    public void setTypeParsers(HashMap<String, String> typeParsers) {
        this.typeParsers = typeParsers;
    }
    public String getSkeleton() {
        return skeleton;
    }
    public void setSkeleton(String skeleton) {
        this.skeleton = skeleton;
    }
    public String[] getProjectNameTags() {
        return projectNameTags;
    }
    public void setProjectNameTags(String[] projectNameTags) {
        this.projectNameTags = projectNameTags;
    }
    public CustomFile[] getAdditionnalFiles() {
        return additionnalFiles;
    }
    public void setAdditionnalFiles(CustomFile[] additionnalFiles) {
        this.additionnalFiles = additionnalFiles;
    }
    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }
    public String generateModel(Entity entity, String projectName) throws IOException, Exception{
        String content=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+getModel().getModelTemplate()+"."+Constantes.MODEL_TEMPLATE_EXT);
        content=content.replace("[namespace]", getSyntax().get("namespace"));
        content=content.replace("[namespaceStart]", getSyntax().get("namespaceStart"));
        content=content.replace("[namespaceEnd]", getSyntax().get("namespaceEnd"));
        content=content.replace("[package]", getModel().getModelPackage());
        String imports="";
        for(String i:getModel().getModelImports()){
            imports+=i+"\n";
        }
        content=content.replace("[imports]", imports);
        String annotes="";
        for(String a:getModel().getModelAnnotations()){
            annotes+=a+"\n";
        }
        content=content.replace("[classAnnotations]", annotes);
        content=content.replace("[extends]", getModel().getModelExtends());
        String constructors="";
        for(String c:getModel().getModelConstructors()){
            constructors+=c+"\n";
        }
        content=content.replace("[constructors]", constructors);
        String fields="", fieldAnnotes;
        // System.out.println("Table : "+entity.getTableName()+", "+entity.getFields().length);
        for(int i=0;i<entity.getFields().length;i++){
            fieldAnnotes="";
            if(entity.getFields()[i].isPrimary()){
                for(String primAnnote:getModel().getModelPrimaryFieldAnnotations()){
                    fieldAnnotes+=primAnnote+"\n";
                }
            }else if(entity.getFields()[i].isForeign()){
                for(String forAnnote:getModel().getModelForeignFieldAnnotations()){
                    fieldAnnotes+=forAnnote+"\n";
                    fieldAnnotes=fieldAnnotes.replace("[referencedFieldNameMin]", HandyManUtils.minStart(entity.getFields()[i].getReferencedField()));
                    fieldAnnotes=fieldAnnotes.replace("[referencedFieldNameMaj]", HandyManUtils.majStart(entity.getFields()[i].getReferencedField()));
                }
            }
            for(String fa:getModel().getModelFieldAnnotations()){
                fieldAnnotes+=fa+"\n";
            }
            fields+=fieldAnnotes;
            fields+=getModel().getModelFieldContent()+"\n";
            fields+=getModel().getModelGetter()+"\n";
            fields+=getModel().getModelSetter()+"\n";
            fields=fields.replace("[columnName]", entity.getColumns()[i].getName());
            // System.out.println(entity.getTableName());
            fields=fields.replace("[fieldType]", entity.getFields()[i].getType());
            fields=fields.replace("[modelFieldCase]", getModel().getModelFieldCase());
            fields=fields.replace("[fieldNameMin]", HandyManUtils.minStart(entity.getFields()[i].getName()));
            fields=fields.replace("[fieldNameMaj]", HandyManUtils.majStart(entity.getFields()[i].getName()));
        }
        content=content.replace("[fields]", fields);
        content=content.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
        content=content.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
        content=content.replace("[classNameMaj]", HandyManUtils.majStart(entity.getClassName()));
        content=content.replace("[modelFieldCase]", getModel().getModelFieldCase());
        content=content.replace("[primaryFieldType]", entity.getPrimaryField().getType());
        content=content.replace("[primaryFieldNameMin]", HandyManUtils.minStart(entity.getPrimaryField().getName()));
        content=content.replace("[primaryFieldNameMaj]", HandyManUtils.majStart(entity.getPrimaryField().getName()));
        content=content.replace("[tableName]", entity.getTableName());
        return content;
    }
    public String generateController(Entity entity, Database database, Credentials credentials, String projectName) throws IOException, Exception{
        String content=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getController().getControllerTemplate()+"."+Constantes.CONTROLLER_TEMPLATE_EXT);
        content=content.replace("[namespace]", getSyntax().get("namespace"));
        content=content.replace("[namespaceStart]", getSyntax().get("namespaceStart"));
        content=content.replace("[namespaceEnd]", getSyntax().get("namespaceEnd"));
        content=content.replace("[package]", this.getArchitecture().getController().getControllerPackage());
        String imports="";
        for(String i:this.getArchitecture().getController().getControllerImports()){
            imports+=i+"\n";
        }
        content=content.replace("[imports]", imports);
        String annotes="";
        for(String a:this.getArchitecture().getController().getControllerAnnotations()){
            annotes+=a+"\n";
        }
        content=content.replace("[controllerAnnotations]", annotes);
        content=content.replace("[extends]", this.getArchitecture().getController().getControllerExtends());
        String fields="", fieldAnnotes;
        for(ControllerField cf:this.getArchitecture().getController().getControllerFields()){
            fieldAnnotes="";
            for(String a:cf.getControllerFieldAnnotations()){
                fieldAnnotes+=a+"\n";
            }
            fields+=fieldAnnotes;
            fields+=cf.getControllerFieldContent()+"\n";
        }
        for(EntityField ef:entity.getFields()){
            if(ef.isForeign()&&this.getArchitecture().getController().getControllerFieldsForeign()!=null){
                fieldAnnotes="";
                for(String a:this.getArchitecture().getController().getControllerFieldsForeign().getControllerFieldAnnotations()){
                    fieldAnnotes+=a+"\n";
                }
                fields+=fieldAnnotes;
                fields+=this.getArchitecture().getController().getControllerFieldsForeign().getControllerFieldContent()+"\n";
                fields=fields.replace("[foreignType]", HandyManUtils.majStart(ef.getType()));
                fields=fields.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
            }
        }
        content=content.replace("[fields]", fields);
        String constructors="";
        String constructorParameter, foreignInstanciation;
        for(String c:this.getArchitecture().getController().getControllerConstructors()){
            constructorParameter="";
            foreignInstanciation="";
            for(EntityField ef:entity.getFields()){
                if(ef.isForeign()){
                    constructorParameter=constructorParameter+","+this.getArchitecture().getController().getControllerForeignContextParam();
                    constructorParameter=constructorParameter.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
                    foreignInstanciation+=this.getArchitecture().getController().getControllerForeignContextInstanciation();
                    foreignInstanciation=foreignInstanciation.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()))+"\n";
                }
            }
            constructors+=c+"\n";
            constructors=constructors.replace("[controllerForeignContextParam]", constructorParameter);
            constructors=constructors.replace("[controllerForeignContextInstanciation]", foreignInstanciation);
        }
        content=content.replace("[constructors]", constructors);
        String methods="", methodAnnotes, methodParameters;
        String changeInstanciation, whereInstanciation, foreignList, foreignInclude;
        for(ControllerMethod m:this.getArchitecture().getController().getControllerMethods()){
            methodAnnotes="";
            for(String ma:m.getControllerMethodAnnotations()){
                methodAnnotes+=ma+"\n";
            }
            methods+=methodAnnotes;
            methodParameters="";
            for(EntityField ef:entity.getFields()){
                methodParameters+=m.getControllerMethodParameter();
                if(methodParameters.isEmpty()==false){ methodParameters+=","; }
                methodParameters=methodParameters.replace("[fieldType]", ef.getType());
                methodParameters=methodParameters.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
            }
            if(methodParameters.isEmpty()==false){
                methodParameters=methodParameters.substring(0, methodParameters.length()-1);
            }
            methods+=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+m.getControllerMethodContent()+"."+Constantes.CONTROLLER_TEMPLATE_EXT);
            methods=methods.replace("[controllerMethodParameter]", methodParameters);
            changeInstanciation="";
            foreignList="";
            foreignInclude="";
            for(int i=0;i<entity.getFields().length;i++){
                if(entity.getFields()[i].isPrimary()){
                    continue;
                }else if(entity.getFields()[i].isForeign()){
                    changeInstanciation+=this.getArchitecture().getController().getControllerForeignInstanciation().get("template");
                    changeInstanciation=changeInstanciation.replace("[content]", getTypeParsers().get(getTypes().get(database.getTypes().get(entity.getColumns()[i].getType()))));
                    changeInstanciation=changeInstanciation.replace("[value]", this.getArchitecture().getController().getControllerForeignInstanciation().get("value"));
                    changeInstanciation=changeInstanciation.replace("[fieldNameMin]", HandyManUtils.minStart(entity.getFields()[i].getName()));
                    changeInstanciation=changeInstanciation.replace("[fieldNameMaj]", HandyManUtils.majStart(entity.getFields()[i].getName()));
                    changeInstanciation=changeInstanciation.replace("[foreignType]", entity.getFields()[i].getType());
                    changeInstanciation=changeInstanciation.replace("[referencedFieldNameMaj]", HandyManUtils.majStart(entity.getFields()[i].getReferencedField()));
                    changeInstanciation=changeInstanciation.replace("[foreignNameMin]", HandyManUtils.minStart(entity.getFields()[i].getName()));
                    foreignList+=this.getArchitecture().getController().getControllerForeignList();
                    foreignList=foreignList.replace("[foreignType]", entity.getFields()[i].getType());
                    foreignList=foreignList.replace("[foreignNameMin]", HandyManUtils.minStart(entity.getFields()[i].getName()));
                    foreignInclude+=this.getArchitecture().getController().getControllerForeignInclude();
                    foreignInclude=foreignInclude.replace("[foreignNameMaj]", HandyManUtils.majStart(entity.getFields()[i].getName()));
                    continue;
                }
                changeInstanciation+=this.getArchitecture().getController().getControllerChangeInstanciation().get("template");
                changeInstanciation=changeInstanciation.replace("[content]", getTypeParsers().get(entity.getFields()[i].getType()));
                changeInstanciation=changeInstanciation.replace("[value]", this.getArchitecture().getController().getControllerChangeInstanciation().get("value"));
                changeInstanciation=changeInstanciation.replace("[fieldNameMin]", HandyManUtils.minStart(entity.getFields()[i].getName()));
                changeInstanciation=changeInstanciation.replace("[fieldNameMaj]", HandyManUtils.majStart(entity.getFields()[i].getName()));
            }
            whereInstanciation="";
            whereInstanciation+=this.getArchitecture().getController().getControllerWhereInstanciation().get("template");
            whereInstanciation=whereInstanciation.replace("[content]", getTypeParsers().get(entity.getPrimaryField().getType()));
            whereInstanciation=whereInstanciation.replace("[value]", this.getArchitecture().getController().getControllerWhereInstanciation().get("value"));
            methods=methods.replace("[primaryParse]", getTypeParsers().get(entity.getPrimaryField().getType()).replace("[value]", "[primaryNameMin]"));
            methods=methods.replace("[controllerChangeInstanciation]", changeInstanciation);
            methods=methods.replace("[controllerWhereInstanciation]", whereInstanciation);
            methods=methods.replace("[controllerForeignList]", foreignList);
            methods=methods.replace("[controllerForeignInclude]", foreignInclude);
            methods=methods.replace("[classNameMin]", HandyManUtils.minStart(entity.getClassName()));
            methods=methods.replace("[classNameMaj]", HandyManUtils.majStart(entity.getClassName()));
            methods=methods.replace("[primaryNameMaj]", HandyManUtils.majStart(entity.getPrimaryField().getName()));
            methods=methods.replace("[primaryType]", entity.getPrimaryField().getType());
            methods=methods.replace("[primaryNameMin]", HandyManUtils.minStart(entity.getPrimaryField().getName()));
            methods=methods.replace("[databaseDriver]", database.getDriver());
            methods=methods.replace("[databaseSgbd]", database.getNom());
            methods=methods.replace("[databaseHost]", credentials.getHost());
            methods=methods.replace("[databasePort]", database.getPort());
            methods=methods.replace("[databaseName]", credentials.getDatabaseName());
            methods=methods.replace("[user]", credentials.getUser());
            methods=methods.replace("[pwd]", credentials.getPwd());
            methods=methods.replace("[databaseUseSSL]", String.valueOf(credentials.isUseSSL()));
            methods=methods.replace("[databaseAllowKey]", String.valueOf(credentials.isAllowPublicKeyRetrieval()));
        }
        content=content.replace("[methods]", methods);
        content=content.replace("[controllerNameMaj]", this.getArchitecture().getController().getControllerName());
        content=content.replace("[classNameMaj]", HandyManUtils.majStart(entity.getClassName()));
        content=content.replace("[classNameMin]", HandyManUtils.minStart(entity.getClassName()));
        content=content.replace("[projectNameMin]", HandyManUtils.minStart(projectName));
        content=content.replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
        content=content.replace("[databaseDriver]", database.getDriver());
        content=content.replace("[databaseSgbd]", database.getNom());
        content=content.replace("[databaseHost]", credentials.getHost());
        content=content.replace("[databaseName]", credentials.getDatabaseName());
        content=content.replace("[databasePort]", database.getPort());
        content=content.replace("[databaseID]", String.valueOf(database.getId()));
        content=content.replace("[user]", credentials.getUser());
        content=content.replace("[pwd]", credentials.getPwd());
        content=content.replace("[databaseUseSSL]", String.valueOf(credentials.isUseSSL()));
        content=content.replace("[databaseAllowKey]", String.valueOf(credentials.isAllowPublicKeyRetrieval()));
        return content;
    }
    public String[] generateView(Entity entity, String projectName) throws IOException, Exception{
        String[] content=new String[this.getArchitecture().getView().getContentViews().length];
        for(int i=0; i<this.getArchitecture().getView().getContentViews().length; i++) {
            content[i]=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getView().getContentViews()[i].getViewContents()+"."+Constantes.VIEW_TEMPLATE_EXT);
            String foreignList="";
            String tableHeader="";
            String tableLine="";
            String foreignGet;
            // String updateForm="", 
            String insertForm="";
            String contentTemplateFunct="";
            String varFetch="";
            String foreignAttrObj="";
            varFetch="";
            contentTemplateFunct="";
            for(EntityField ef:entity.getFields()){
                foreignGet="";
                tableHeader+=this.getArchitecture().getView().getViewTableHeader();
                tableHeader=tableHeader.replace("[fieldNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
                tableHeader=tableHeader.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
                tableHeader=tableHeader.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
                tableLine+=this.getArchitecture().getView().getViewTableLine();
                tableLine=tableLine.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
                tableLine=tableLine.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
                if(ef.isPrimary()){
                    tableLine=tableLine.replace("[foreignFieldGet]", foreignGet);
                    continue;
                }
                if(ef.isForeign()==false){
                    // updateForm+=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getView().getViewUpdateFormField().get(ef.getType())+"."+Constantes.VIEW_TEMPLATE_EXT);
                    // updateForm=updateForm.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
                    // updateForm=updateForm.replace("[fieldNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
                    // updateForm=updateForm.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
                    insertForm+=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getView().getViewInsertFormField().get(ef.getType())+"."+Constantes.VIEW_TEMPLATE_EXT);
                    insertForm=insertForm.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
                    insertForm=insertForm.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
                    insertForm=insertForm.replace("[fieldNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
                    tableLine=tableLine.replace("[foreignFieldGet]", foreignGet);
                    continue;
                } else {
                    if(this.getArchitecture().getView().getFetchApiFunction()!=null) {
                        contentTemplateFunct+=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getView().getFetchApiFunction()+"."+Constantes.VIEW_TEMPLATE_EXT);
                        contentTemplateFunct= contentTemplateFunct.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
                        contentTemplateFunct= contentTemplateFunct.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
                        contentTemplateFunct= contentTemplateFunct.replace("[foreignType]", HandyManUtils.minStart(ef.getType()));
                        contentTemplateFunct= contentTemplateFunct.replace("[foreignTypeMaj]", HandyManUtils.majStart(ef.getType()));
                        varFetch+=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getView().getFetchApiStock()+"."+Constantes.VIEW_TEMPLATE_EXT);
                        varFetch= varFetch.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
                        varFetch= varFetch.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
                        foreignAttrObj+=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getView().getObjectInsertForeign()+"."+Constantes.VIEW_TEMPLATE_EXT);
                        foreignAttrObj= foreignAttrObj.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
                        foreignAttrObj= foreignAttrObj.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
                    }
                    // System.out.println(HandyManUtils.majStart(ef.getName()));
                }
                // updateForm+=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getView().getViewUpdateFormForeignField()+"."+Constantes.VIEW_TEMPLATE_EXT);
                // updateForm=updateForm.replace("[foreignType]", ef.getType());
                // updateForm=updateForm.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
                // updateForm=updateForm.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
                // updateForm=updateForm.replace("[foreignPrimaryNameMaj]", HandyManUtils.majStart(ef.getReferencedField()));
                // updateForm=updateForm.replace("[foreignPrimaryNameMin]", HandyManUtils.minStart(ef.getReferencedField()));
                // updateForm=updateForm.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
                // updateForm=updateForm.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
                // updateForm=updateForm.replace("[foreignNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
                insertForm+=HandyManUtils.getFileContent(Constantes.DATA_PATH+"/"+this.getArchitecture().getView().getViewInsertFormForeignField()+"."+Constantes.VIEW_TEMPLATE_EXT);
                insertForm=insertForm.replace("[foreignType]", ef.getType());
                insertForm=insertForm.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
                insertForm=insertForm.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
                insertForm=insertForm.replace("[foreignPrimaryNameMaj]", HandyManUtils.majStart(ef.getReferencedField()));
                insertForm=insertForm.replace("[foreignPrimaryNameMin]", HandyManUtils.minStart(ef.getReferencedField()));
                insertForm=insertForm.replace("[fieldNameMin]", HandyManUtils.minStart(ef.getName()));
                insertForm=insertForm.replace("[fieldNameMaj]", HandyManUtils.majStart(ef.getName()));
                insertForm=insertForm.replace("[foreignNameFormattedMaj]", HandyManUtils.formatReadable(ef.getName()));
                foreignGet=this.getArchitecture().getView().getForeignFieldGet();
                tableLine=tableLine.replace("[foreignFieldGet]", foreignGet);
                foreignList+=this.getArchitecture().getView().getViewForeignList();
                foreignList=foreignList.replace("[foreignType]", ef.getType());
                foreignList=foreignList.replace("[foreignNameMin]", HandyManUtils.minStart(ef.getName()));
                foreignList=foreignList.replace("[foreignNameMaj]", HandyManUtils.majStart(ef.getName()));
            }
            content[i]=content[i].replace("[viewForeignList]", foreignList);
            content[i]=content[i].replace("[viewTableHeader]", tableHeader);
            content[i]=content[i].replace("[viewTableLine]", tableLine);
            // content=content.replace("[viewUpdateFormField]", updateForm);
            content[i]=content[i].replace("[viewInsertFormField]", insertForm);
            content[i]=content[i].replace("[projectNameMin]", HandyManUtils.minStart(projectName));
            content[i]=content[i].replace("[projectNameMaj]", HandyManUtils.majStart(projectName));
            content[i]=content[i].replace("[classNameMaj]", HandyManUtils.majStart(entity.getClassName()));
            content[i]=content[i].replace("[classNameMin]", HandyManUtils.minStart(entity.getClassName()));
            content[i]=content[i].replace("[primaryNameMaj]", HandyManUtils.majStart(entity.getPrimaryField().getName()));
            content[i]=content[i].replace("[primaryNameMin]", HandyManUtils.minStart(entity.getPrimaryField().getName()));
            content[i]=content[i].replace("[fetchApi]", contentTemplateFunct);
            content[i]=content[i].replace("[listeVariableForeignFetch]", varFetch);
            content[i]=content[i].replace("[objectInsertForeign]", foreignAttrObj);
        }
        return content;
    }
    public Architecture getArchitecture() {
        return architecture;
    }
    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }
    public Architecture[] getListeArchitecture() {
        return listeArchitecture;
    }
    public void setListeArchitecture(Architecture[] listeArchitecture) {
        this.listeArchitecture = listeArchitecture;
    }
}
