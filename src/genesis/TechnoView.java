package genesis;

import java.util.HashMap;

public class TechnoView {
    private int idTechno;
    private String nameTechno;
    private String viewForeignList;
    private String viewTableHeader;
    private String foreignFieldGet;
    private String viewTableLine;
    private String viewUpdateFormForeignField;
    private HashMap<String, String> viewUpdateFormField;
    private String viewInsertFormForeignField;
    private HashMap<String, String> viewInsertFormField;
    private ContentView[] contentViews;
    private ContentView contentLogin;
    private String fetchApiFunction;
    private String fetchApiStock;
    private String viewSavePath;
    private String viewExtension;
    private String viewSkeleton;
    private String objectInsertForeign;
    public int getIdTechno() {
        return idTechno;
    }
    public void setIdTechno(int idTechno) {
        this.idTechno = idTechno;
    }
    public String getNameTechno() {
        return nameTechno;
    }
    public void setNameTechno(String nameTechno) {
        this.nameTechno = nameTechno;
    }
    public String getViewForeignList() {
        return viewForeignList;
    }
    public void setViewForeignList(String viewForeignList) {
        this.viewForeignList = viewForeignList;
    }
    public String getViewTableHeader() {
        return viewTableHeader;
    }
    public void setViewTableHeader(String viewTableHeader) {
        this.viewTableHeader = viewTableHeader;
    }
    public String getForeignFieldGet() {
        return foreignFieldGet;
    }
    public void setForeignFieldGet(String foreignFieldGet) {
        this.foreignFieldGet = foreignFieldGet;
    }
    public String getViewTableLine() {
        return viewTableLine;
    }
    public void setViewTableLine(String viewTableLine) {
        this.viewTableLine = viewTableLine;
    }
    public String getViewUpdateFormForeignField() {
        return viewUpdateFormForeignField;
    }
    public void setViewUpdateFormForeignField(String viewUpdateFormForeignField) {
        this.viewUpdateFormForeignField = viewUpdateFormForeignField;
    }
    public HashMap<String, String> getViewUpdateFormField() {
        return viewUpdateFormField;
    }
    public void setViewUpdateFormField(HashMap<String, String> viewUpdateFormField) {
        this.viewUpdateFormField = viewUpdateFormField;
    }
    public String getViewInsertFormForeignField() {
        return viewInsertFormForeignField;
    }
    public void setViewInsertFormForeignField(String viewInsertFormForeignField) {
        this.viewInsertFormForeignField = viewInsertFormForeignField;
    }
    public HashMap<String, String> getViewInsertFormField() {
        return viewInsertFormField;
    }
    public void setViewInsertFormField(HashMap<String, String> viewInsertFormField) {
        this.viewInsertFormField = viewInsertFormField;
    }
    public String getViewSavePath() {
        return viewSavePath;
    }
    public void setViewSavePath(String viewSavePath) {
        this.viewSavePath = viewSavePath;
    }
    public String getViewExtension() {
        return viewExtension;
    }
    public void setViewExtension(String viewExtension) {
        this.viewExtension = viewExtension;
    }
    public String getViewSkeleton() {
        return viewSkeleton;
    }
    public void setViewSkeleton(String viewSkeleton) {
        this.viewSkeleton = viewSkeleton;
    }
    public ContentView[] getContentViews() {
        return contentViews;
    }
    public void setContentViews(ContentView[] contentViews) {
        this.contentViews = contentViews;
    }
    public String getFetchApiFunction() {
        return fetchApiFunction;
    }
    public void setFetchApiFunction(String fetchApiFunction) {
        this.fetchApiFunction = fetchApiFunction;
    }
    public String getFetchApiStock() {
        return fetchApiStock;
    }
    public void setFetchApiStock(String fetchApiStock) {
        this.fetchApiStock = fetchApiStock;
    }
    public String getObjectInsertForeign() {
        return objectInsertForeign;
    }
    public void setObjectInsertForeign(String objectInsertForeign) {
        this.objectInsertForeign = objectInsertForeign;
    }
    public ContentView getContentLogin() {
        return contentLogin;
    }
    public void setContentLogin(ContentView contentLogin) {
        this.contentLogin = contentLogin;
    }
}
