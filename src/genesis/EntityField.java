package genesis;

public class EntityField {
    private String name;
    private String type;
    private boolean primary;
    private boolean foreign;
    private String referencedField;
    private boolean isUserName;
    private boolean isPassword;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isPrimary() {
        return primary;
    }
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
    public boolean isForeign() {
        return foreign;
    }
    public void setForeign(boolean foreign) {
        this.foreign = foreign;
    }
    public String getReferencedField() {
        return referencedField;
    }
    public void setReferencedField(String referencedField) {
        this.referencedField = referencedField;
    }
    public boolean isUserName() {
        return isUserName;
    }
    public void setUserName(boolean isUserName) {
        this.isUserName = isUserName;
    }
    public boolean isPassword() {
        return isPassword;
    }
    public void setPassword(boolean isPassword) {
        this.isPassword = isPassword;
    }
    
}
