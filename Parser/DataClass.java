package Parser;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class DataClass extends Data{
    //attribute
    private String visibilityClass;
    private String scopeClass;
    private String nameClass;
    private String superClass;
    private String interfaceClass;
    private ArrayList<DataAttribute> dataAttributeClasses;
    private ArrayList<DataMethod> dataMethodClasses;
    //constructor
    public DataClass(File file) throws Exception{
        this.setVisibilityClass("default");
        this.setScopeClass("non-static");
        this.setSuperClass("no");
        this.setInterfaceClass("no");
        this.dataAttributeClasses = new ArrayList<DataAttribute>();
        this.dataMethodClasses = new ArrayList<DataMethod>();
        this.setDataClass(file);
    }
    //setter getter
    public void setVisibilityClass(String visibilityClass) {
        this.visibilityClass = visibilityClass;
    }
    public String getVisibilityClass() {
        return visibilityClass;
    }
    public void setScopeClass(String scopeClass) {
        this.scopeClass = scopeClass;
    }
    public String getScopeClass() {
        return scopeClass;
    }
    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
    public String getNameClass() {
        return nameClass;
    }
    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }
    public String getSuperClass() {
        return superClass;
    }
    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
    public String getInterfaceClass() {
        return interfaceClass;
    }
    public void addDataAttributeClasses(DataAttribute dataattribute){
        this.dataAttributeClasses.add(dataattribute);
    }
    public ArrayList<DataAttribute> getDataAttributeClasses() {
        return dataAttributeClasses;
    }
    public void addDataMethodClasses(DataMethod datamethod){
        this.dataMethodClasses.add(datamethod);
    }
    public ArrayList<DataMethod> getDataMethodClasses() {
        return dataMethodClasses;
    }
    //method
    public void setDataClass(File file) throws Exception{
        FileReader filereader = new FileReader(file);
        BufferedReader read = new BufferedReader(filereader);
        String sourceCode = new String("");
        String line = null;
        while((line = read.readLine()) != null){
            line = line.replaceAll("(\\{)", " { ");
            line = line.replaceAll("(\\})", " } ");
            line = line.replaceAll("(\\;)", " ;\n");
            line = line.replaceAll("(\\()", " ( ");
            line = line.replaceAll("(\\))", " ) ");
            line = line.replaceAll("(\\,)", " , ");
            line = line.replaceAll("(\\s+)", " ");
            sourceCode = sourceCode.concat(line + "\n");
        }
        read.close();
        sourceCode = this.deleteComments(sourceCode);
        sourceCode = this.deleteBodyMethods(sourceCode);
        String[] arr_lines = sourceCode.split("\n");
        for (String iter : arr_lines) {
            iter = iter.trim();
            if(iter.contains("import "));
            else if(iter.contains(" class ")){
                String[] arr_iter = iter.split(" ");
                for (int i = 0; i < arr_iter.length; i++){
                    if(this.isVisibility(arr_iter[i])){
                        this.setVisibilityClass(arr_iter[i]);
                    }
                    else if(arr_iter[i].equals("static")){
                        this.setScopeClass("static");
                    }
                    else if(arr_iter[i].equals("class")){
                        this.setNameClass(arr_iter[i+1]);
                        this.addTypes(getNameClass());
                    }
                    else if(arr_iter[i].equals("extends")){
                        this.setSuperClass(arr_iter[i+1]);
                    }
                    else if(arr_iter[i].equals("implements")){
                        this.setInterfaceClass(arr_iter[i+1]);
                    }
                }
            }
            else if(iter.contains(";")){
                DataAttribute dataattribute = new DataAttribute(iter);
                this.addDataAttributeClasses(dataattribute);
            }
            else if(iter.contains(getNameClass()));
            else if(iter.contains("(") && iter.contains(")") && iter.contains("{") && iter.contains("}")){
                DataMethod datamethod = new DataMethod(iter);
                this.addDataMethodClasses(datamethod);
            }
        }
    }
    private String deleteComments(String sourceCode){
        sourceCode = sourceCode.replaceAll("((//.*\n+)|(/\\*([^*]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/\n+))", "");
        return sourceCode;
    }
    private String deleteBodyMethods(String sourceCode){
        String newsourceCode = new String("");
        String[] arr_sourceCode = sourceCode.split(" ");
        int[] arr_temp = new int[arr_sourceCode.length];
        int inbodyClass = 0;
        for(int i = 0; i < arr_sourceCode.length; i++){
            if(arr_sourceCode[i].equals("{")) arr_temp[i] = inbodyClass++;
            else if(arr_sourceCode[i].equals("}")) arr_temp[i] = --inbodyClass;
            else arr_temp[i] = inbodyClass;
        }
        for(int i = 0; i < arr_sourceCode.length; i++){
            if(arr_temp[i] >= 2) arr_sourceCode[i] = "";
            newsourceCode = newsourceCode.concat(arr_sourceCode[i] + " ");
        }
        newsourceCode = newsourceCode.replaceAll("( +)", " ");
        newsourceCode = newsourceCode.trim();
        return newsourceCode;
    }
    public void display(){
        System.out.printf("\nClass: %s", getNameClass());
        System.out.printf("\nSuperClass: %s", getSuperClass());
        System.out.printf("\nAttribute:");
        for(DataAttribute iter : dataAttributeClasses) {
            iter.display();
        }
        System.out.printf("\nMethod:");
        for (DataMethod iter : dataMethodClasses) {
            iter.display();
        }
    }
}