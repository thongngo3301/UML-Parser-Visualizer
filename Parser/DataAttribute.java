package Parser;

import java.util.ArrayList;

public class DataAttribute extends Data{
    //attribute
    private String visibilityAttribute;
    private String scopeAttribute;
    private String typeAttribute;
    private String nameAttribute;
    private String valueAttribute;
    //constructor
    public DataAttribute(){
        this.setVisibilityAttribute("default");
        this.setScopeAttribute("non-static");
        this.setValueAttribute("default");
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
    public static final ArrayList<DataAttribute> setDataAttribute(String line){ //public int a ;
        ArrayList<DataAttribute> dataAttributes = new ArrayList<DataAttribute>();
        String[] arr_partline = line.split(",");

        DataAttribute dataFirstAttribute = new DataAttribute();
        arr_partline[0] = arr_partline[0].trim();
        if(arr_partline[0].contains("=")) {
            int equal = arr_partline[0].indexOf('=');
            String dataattribute = arr_partline[0].substring(0, equal - 1);
            String arr_dataattribute[] = dataattribute.split(" ");
            for (String iter : arr_dataattribute) {
                if(isVisibility(iter)) {
                    dataFirstAttribute.setVisibilityAttribute(iter);
                } else if(iter.equals("static")) {
                    dataFirstAttribute.setScopeAttribute("static");
                }
            }
            dataFirstAttribute.setTypeAttribute(arr_dataattribute[arr_dataattribute.length - 2]);
            dataFirstAttribute.setNameAttribute(arr_dataattribute[arr_dataattribute.length - 1]);
            String datavalue = arr_partline[0].substring(equal + 2, arr_partline[0].length());
            dataFirstAttribute.setValueAttribute(datavalue);
        } else {
            String arr_dataattribute[] = arr_partline[0].replaceAll(";", "").split(" ");
            for (String iter : arr_dataattribute) {
                if(isVisibility(iter)) {
                    dataFirstAttribute.setVisibilityAttribute(iter);
                } else if(iter.equals("static")) {
                    dataFirstAttribute.setScopeAttribute("static");
                }
            }
            dataFirstAttribute.setTypeAttribute(arr_dataattribute[arr_dataattribute.length - 2]);
            dataFirstAttribute.setNameAttribute(arr_dataattribute[arr_dataattribute.length - 1]);
        }
        dataAttributes.add(dataFirstAttribute);
        if(arr_partline.length >= 2) {
            for(int i = 1; i < arr_partline.length; i++) {
                DataAttribute dataTempAttribute = new DataAttribute();
                dataTempAttribute.setTypeAttribute(dataFirstAttribute.getTypeAttribute());
                dataTempAttribute.setVisibilityAttribute(dataFirstAttribute.getVisibilityAttribute());
                dataTempAttribute.setScopeAttribute(dataFirstAttribute.getScopeAttribute());
                arr_partline[i] = arr_partline[i].trim();
                if(arr_partline[i].contains("=")) {
                    String[] arr_dataAttributeNexStrings = arr_partline[i].split("=");
                    dataTempAttribute.setNameAttribute(arr_dataAttributeNexStrings[0].trim());
                    dataTempAttribute.setValueAttribute(arr_dataAttributeNexStrings[1].replaceAll(";", "").trim());
                } else {
                    dataTempAttribute.setNameAttribute(arr_partline[i].replaceAll(";", "").trim());
                }
                dataAttributes.add(dataTempAttribute);
            }
        }
        return dataAttributes;
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