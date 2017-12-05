package Parser;

public class DataAttribute extends Data{
    //attribute
    private String visibilityAttribute;
    private String scopeAttribute;
    private String typeAttribute;
    private String nameAttribute;
    private String valueAttribute;
    //constructor
    public DataAttribute(String line){
        this.setVisibilityAttribute("default");
        this.setScopeAttribute("non-static");
        this.setValueAttribute("default");
        this.setDataAttribute(line);
    }
    // setter getter
    public final void setVisibilityAttribute(String visibilityAttribute) { this.visibilityAttribute = visibilityAttribute; }
    public String getVisibilityAttribute() { return visibilityAttribute; }
    
    public final void setScopeAttribute(String scopeAttribute) { this.scopeAttribute = scopeAttribute; }
    public String getScopeAttribute() { return scopeAttribute; }
    
    public void setTypeAttribute(String typeAttribute) { this.typeAttribute = typeAttribute; }
    public String getTypeAttribute() { return typeAttribute; }
    
    public void setNameAttribute(String nameAttribute) { this.nameAttribute = nameAttribute; }
    public String getNameAttribute() { return nameAttribute; }
    
    public final void setValueAttribute(String valueAttribute) { this.valueAttribute = valueAttribute; }
    public String getValueAttribute() { return valueAttribute; }
    // method 
    public final void setDataAttribute(String line){ //public String dataMethod;
        if(line.contains("=")) {
            int equal = line.indexOf('=');
            String dataattribute = line.substring(0, equal - 1);
            String arr_dataattribute[] = dataattribute.split(" ");
            for (String iter : arr_dataattribute) {
                if(this.isVisibility(iter)) {
                    this.setVisibilityAttribute(iter);
                } else if(iter.equals("static")) {
                    this.setScopeAttribute("static");
                }
            }
            this.setTypeAttribute(arr_dataattribute[arr_dataattribute.length - 2]);
            this.setNameAttribute(arr_dataattribute[arr_dataattribute.length - 1]);
            String datavalue = line.substring(equal + 2, line.length() - 1);
            this.setValueAttribute(datavalue);
        } else {
            String arr_dataattribute[] = line.split(" ");
            for (String iter : arr_dataattribute) {
                if(this.isVisibility(iter)) {
                    this.setVisibilityAttribute(iter);
                } else if(iter.equals("static")) {
                    this.setScopeAttribute("static");
                }
            }
            this.setTypeAttribute(arr_dataattribute[arr_dataattribute.length - 3]);
            this.setNameAttribute(arr_dataattribute[arr_dataattribute.length - 2]);
        }
    }
    
    @Override
    public String toString() {
        String temp = "";
        temp = temp.concat(toStringVisibility(visibilityAttribute) + getNameAttribute() + ":" + getTypeAttribute());
        if(this.getValueAttribute().equals("default"));
        else temp = temp.concat("=" + getValueAttribute());
        return temp;
    }
}