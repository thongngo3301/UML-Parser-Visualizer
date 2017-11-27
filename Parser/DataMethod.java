package Parser;

import java.util.ArrayList;

public class DataMethod extends Data{
    //attribute
    private String visibilityMethod;
    private String scopeMethod;
    private String typeMethod;
    private String nameMethod;
    private ArrayList<DataParameter> parameterMethods;
    //constructor
    public DataMethod(String line){
        this.parameterMethods = new ArrayList<DataParameter>();
        this.setVisibilityMethod("default");
        this.setScopeMethod("non-static");
        this.setDataMethod(line);
    }
    //setter getter
    public void setVisibilityMethod(String visibilityMethod){
        this.visibilityMethod = visibilityMethod;
    }
    public String getVisibilityMethod(){
        return visibilityMethod;
    }
    public void setScopeMethod(String scopeMethod){
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
    //method public void getName ( ) { }
    public void setDataMethod(String line){
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
    public void display(){
        this.displayVisibility(visibilityMethod);
        System.out.printf("%s ( ", getNameMethod());
        for (int i = 0; i < parameterMethods.size(); i++){
            parameterMethods.get(i).display();
            if(i < (parameterMethods.size() - 1)) System.out.printf(", ");
        }
        System.out.printf(" ) : %s", getTypeMethod());
    }
}