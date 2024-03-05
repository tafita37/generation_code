package genesis;

public class Architecture {
    int idArchitecture;
    String nomArchitecture;
    Controller controller;
    View view;
    public Architecture(int idArchitecture, String nomArchitecture, Controller controller, View view) {
        this.idArchitecture = idArchitecture;
        this.nomArchitecture = nomArchitecture;
        this.controller = controller;
        this.view = view;
    }
    public Architecture() {
    }
    public int getIdArchitecture() {
        return idArchitecture;
    }
    public void setIdArchitecture(int idArchitecture) {
        this.idArchitecture = idArchitecture;
    }
    public String getNomArchitecture() {
        return nomArchitecture;
    }
    public void setNomArchitecture(String nomArchitecture) {
        this.nomArchitecture = nomArchitecture;
    }
    public Controller getController() {
        return controller;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public View getView() {
        return view;
    }
    public void setView(View view) {
        this.view = view;
    }
}
