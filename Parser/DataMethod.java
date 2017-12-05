package Parser;

import java.util.ArrayList;

public class DataMethod extends Data{
    //attribute
    private String visibilityMethod;
    private String scopeMethod;
    private String typeMethod;
    private String nameMethod;
    private final ArrayList<DataParameter> parameterMethods;
    //constructor
    public DataMethod(String line){
        this.parameterMethods = new ArrayList<DataParameter>();
        this.setVisibilityMethod("default");
        this.setScopeMethod("non-static");
        this.setDataMethod(line);
    }
    //setter getter
    public final void setVisibilityMethod(String visibilityMethod){
        this.visibilityMethod = visibilityMethod;
    }
    public String getVisibilityMethod(){
        return visibilityMethod;
    }
    public final void setScopeMethod(String scopeMethod){
        this.scopeMethod = scopeMethod;
    }
    public String getScopeMethod(){
        return scopeMethod;
    }
    public void setTypeMethod(String typeMethod){
        this.typeMethod = typeMethod;
    }
    public String getTypeMethod(){
        return typeMethod;
    }
    public void setNameMethod(String nameMethod){
        this.nameMethod = nameMethod;
    }
    public String getNameMethod(){
        return nameMethod;
    }
    public void addParameterMethods(DataParameter parameterMethod){
        this.parameterMethods.add(parameterMethod);
    }
    public ArrayList<DataParameter> getParameterMethods(){
        return parameterMethods;
    }
    //method 
    public final void setDataMethod(String line){ //public void getName ( ) { }
            int start_rou_bra = line.indexOf('(');
            int end_rou_bra = line.indexOf(')');
            String datamethod = line.substring(0, start_rou_bra - 1);
            String[] arr_datamethod = datamethod.split(" ");
            for(int i = 0; i < arr_datamethod.length - 2; i++){
                if(this.isVisibility(arr_datamethod[i])){
                    this.setVisibilityMethod(arr_datamethod[i]);
                } else if(arr_datamethod[i].equals("static")){
                    this.setScopeMethod("static");
                }
            }
            if(end_rou_bra - start_rou_bra >= 3) {
                String dataParas = line.substring(start_rou_bra + 2, end_rou_bra - 1);
                String arr_dataParas[] = dataParas.split(" , ");
                for (String dataPara : arr_dataParas) {
                    String arr_dataPara[] = dataPara.split(" ");
                    DataParameter parameter = new DataParameter(arr_dataPara[0], arr_dataPara[1]);
                    this.addParameterMethods(parameter);
                }
        }
        this.setTypeMethod(arr_datamethod[arr_datamethod.length - 2]);
        this.setNameMethod(arr_datamethod[arr_datamethod.length - 1]);
    }
    @Override
    public String toString() {
        String temp = "";
        temp = temp.concat(toStringVisibility(visibilityMethod));
        temp = temp.concat(getNameMethod() + "(");
        for (int i = 0; i < parameterMethods.size(); i++){
            temp = temp.concat(parameterMethods.get(i).toString());
            if(i < (parameterMethods.size() - 1)) temp = temp.concat(", ");
        }
        temp = temp.concat("): " + getTypeMethod());
        return temp.trim();
    }
}