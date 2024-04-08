package genesis;

import java.util.HashMap;

public class View {
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
    private String objectInsertForeign;
    private TechnoView[] listeTechnoView;
    private TechnoView technoView;
    private NavbarLink navbarLinks;
    private Router router;
    private String[] pathSecurityConfig;

    public void setTechnoContent() {
        this.viewForeignList=technoView.getViewForeignList();
        this.viewTableHeader=technoView.getViewTableHeader();
        this.foreignFieldGet=technoView.getForeignFieldGet();
        this.viewTableLine=technoView.getViewTableLine();
        this.viewUpdateFormForeignField=technoView.getViewUpdateFormForeignField();
        this.viewUpdateFormField=technoView.getViewUpdateFormField();
        this.viewInsertFormForeignField=technoView.getViewInsertFormForeignField();
        this.viewInsertFormField=technoView.getViewInsertFormField();
        this.contentViews=technoView.getContentViews();
        this.viewSavePath=technoView.getViewSavePath();
        this.viewExtension=technoView.getViewExtension();
        this.fetchApiFunction=technoView.getFetchApiFunction();
        this.fetchApiStock=technoView.getFetchApiStock();
        this.objectInsertForeign=technoView.getObjectInsertForeign();
        this.contentLogin=technoView.getContentLogin();
    }
    public TechnoView[] getListeTechnoView() {
        return listeTechnoView;
    }
    public void setListeTechnoView(TechnoView[] listeTechnoView) {
        this.listeTechnoView = listeTechnoView;
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
    public TechnoView getTechnoView() {
        return technoView;
    }
    public void setTechnoView(TechnoView technoView) {
        this.technoView = technoView;
        this.setTechnoContent();
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
    public NavbarLink getNavbarLinks() {
        return navbarLinks;
    }
    public void setNavbarLinks(NavbarLink navbarLinks) {
        this.navbarLinks = navbarLinks;
    }
    public Router getRouter() {
        return router;
    }
    public void setRouter(Router router) {
        this.router = router;
    }
    public String getObjectInsertForeign() {
        return objectInsertForeign;
    }
    public void setObjectInsertForeign(String objectInsertForeign) {
        this.objectInsertForeign = objectInsertForeign;
    }
    public String[] getPathSecurityConfig() {
        return pathSecurityConfig;
    }
    public void setPathSecurityConfig(String[] pathSecurityConfig) {
        this.pathSecurityConfig = pathSecurityConfig;
    }
    public ContentView getContentLogin() {
        return contentLogin;
    }
    public void setContentLogin(ContentView contentLogin) {
        this.contentLogin = contentLogin;
    }
    
}
