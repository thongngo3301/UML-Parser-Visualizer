package Parser;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class DataClass extends Data{
    //attribute
    private boolean isClass;
    private boolean isInterface;
    private String visibilityClass;
    private String scopeClass;
    private String nameClass;
    private String superClass;
    private final ArrayList<String> interfaceClasses;
    private final ArrayList<DataAttribute> dataAttributeClasses;
    private final ArrayList<DataMethod> dataMethodClasses;
    //constructor
    public DataClass(File file) throws Exception{
        this.isClass = false;
        this.isInterface = false;
        this.setVisibilityClass("default");
        this.setScopeClass("non-static");
        this.setSuperClass("no");
        this.interfaceClasses = new ArrayList<String>();
        this.dataAttributeClasses = new ArrayList<DataAttribute>();
        this.dataMethodClasses = new ArrayList<DataMethod>();
        this.setDataClass(file);
    }
    //setter getter
    public final void setVisibilityClass(String visibilityClass) { this.visibilityClass = visibilityClass; }
    public String getVisibilityClass() { return visibilityClass; }
    
    public final void setScopeClass(String scopeClass) { this.scopeClass = scopeClass; }
    public String getScopeClass() { return scopeClass; }
    
    public void setNameClass(String nameClass) { this.nameClass = nameClass; }
    public String getNameClass() { return nameClass; }
    
    public final void setSuperClass(String superClass) { this.superClass = superClass; }
    public String getSuperClass() { return superClass; }
    
    public void addInterfaceClasses(String interfaceClass) { this.interfaceClasses.add(interfaceClass); }
    public ArrayList<String> getInterfaceClasses() { return interfaceClasses; }
    
    public void addDataAttributeClasses(DataAttribute dataattribute){ this.dataAttributeClasses.add(dataattribute); }
    public ArrayList<DataAttribute> getDataAttributeClasses() { return dataAttributeClasses; }
    
    public void addDataMethodClasses(DataMethod datamethod){ this.dataMethodClasses.add(datamethod); }
    public ArrayList<DataMethod> getDataMethodClasses() { return dataMethodClasses; }
    //method
    public final void setDataClass(File file) throws Exception{
        FileReader filereader = new FileReader(file);
        String sourceCode;
        try (BufferedReader read = new BufferedReader(filereader)) {
            sourceCode = "";
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
        }
        sourceCode = this.deleteComments(sourceCode);
        sourceCode = this.deleteBodyMethods(sourceCode);
        String[] arr_lines = sourceCode.split("\n");
        for (String iter : arr_lines) {
            iter = iter.trim();
            if(iter.contains("import")) {
            } else if(iter.contains("package")) {
            } else if(iter.contains("class")) {
                this.isClass = true;
                String[] arr_iter = iter.split(" ");
                for (int i = 0; i < arr_iter.length; i++) {
                    if(this.isVisibility(arr_iter[i])){
                        this.setVisibilityClass(arr_iter[i]);
                    }
                    else if(arr_iter[i].equals("static")) {
                        this.setScopeClass("static");
                    }
                    else if(arr_iter[i].equals("class")) {
                        this.setNameClass(arr_iter[i+1]);
                        this.addTypes(getNameClass());
                    }
                    else if(arr_iter[i].equals("extends")) {
                        this.setSuperClass(arr_iter[i+1]);
                    }
                    else if(arr_iter[i].equals("implements")) {
                        this.addInterfaceClasses(arr_iter[i+1]);
                        int j = i + 2;
                        while (arr_iter[j].equals(",")) {
                            this.addInterfaceClasses(arr_iter[j+1]);
                            j+=2;
                        }
                    }
                }
            }
            else if(iter.contains("interface")) {
                this.isInterface = true;
                String[] arr_iter = iter.split(" ");
                for (int i = 0; i < arr_iter.length; i++) {
                    if(this.isVisibility(arr_iter[i])){
                        this.setVisibilityClass(arr_iter[i]);
                    }
                    else if(arr_iter[i].equals("static")) {
                        this.setScopeClass("static");
                    }
                    else if(arr_iter[i].equals("interface")) {
                        this.setNameClass(arr_iter[i+1]);
                        this.addTypes(getNameClass());
                    }
                    else if(arr_iter[i].equals("extends")) {
                        this.setSuperClass(arr_iter[i+1]);
                    }
                    else if(arr_iter[i].equals("implements")) {
                        this.addInterfaceClasses(arr_iter[i+1]);
                        int j = i + 2;
                        while (arr_iter[j].equals(",")) {
                            this.addInterfaceClasses(arr_iter[j+1]);
                            j+=2;
                        }
                    }
                }
            }
            else if(iter.contains("(") && iter.contains(")") && iter.contains("{") && iter.contains("}")) {
                DataMethod datamethod = new DataMethod(iter);
                if(datamethod.getNameMethod().equals(this.getNameClass()))
                    datamethod.setTypeMethod("Constructor");
                this.addDataMethodClasses(datamethod);
            }
            else if(iter.contains(" ( ) ;") && iter.contains("abstract")) {
                DataMethod datamethod = new DataMethod(iter);
                if(datamethod.getNameMethod().equals(this.getNameClass()))
                    datamethod.setTypeMethod("Constructor");
                this.addDataMethodClasses(datamethod);
            }
            else if(iter.contains(";")) {
                DataAttribute dataattribute = new DataAttribute(iter);
                this.addDataAttributeClasses(dataattribute);
            }
        }
    }
    private String deleteComments(String sourceCode) {
        sourceCode = sourceCode.replaceAll("((//.*\n+)|(/\\*([^*]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/\n+))", "");
        return sourceCode;
    }
    private String deleteBodyMethods(String sourceCode) {
        String newsourceCode = "";
        String[] arr_sourceCode = sourceCode.split(" ");
        int[] arr_temp = new int[arr_sourceCode.length];
        int inbodyClass = 0;
        for(int i = 0; i < arr_sourceCode.length; i++) {
            switch (arr_sourceCode[i]) {
                case "{":
                    arr_temp[i] = inbodyClass++;
                    break;
                case "}":
                    arr_temp[i] = --inbodyClass;
                    break;
                default:
                    arr_temp[i] = inbodyClass;
                    break;
            }
        }
        for(int i = 0; i < arr_sourceCode.length; i++) {
            if(arr_temp[i] >= 2) arr_sourceCode[i] = "";
            newsourceCode = newsourceCode.concat(arr_sourceCode[i] + " ");
        }
        newsourceCode = newsourceCode.replaceAll("( +)", " ");
        newsourceCode = newsourceCode.trim();
        return newsourceCode;
    }
    @Override
    public String toString() {
        String temp = "";
        if(this.isClass) temp = temp.concat("\nClass " + getNameClass());
        if(this.isInterface) temp = temp.concat("\nInterface " + getNameClass());
        temp = temp.concat("\nVisibility: " + getVisibilityClass());
        temp = temp.concat("\nScope: " + getScopeClass());
        temp = temp.concat("\nSuperClass: " + getSuperClass());
        temp = temp.concat("\nInterface: ");
        for (String var : interfaceClasses) {
            temp = temp.concat(var + " ");
        }
        temp = temp.concat("\nAttribute: ");
        for(DataAttribute iter : dataAttributeClasses) {
            temp = temp.concat(iter.toString());
        }
        temp = temp.concat("\nMethod: ");
        for (DataMethod iter : dataMethodClasses) {
            temp = temp.concat(iter.toString());
        }
        return temp;
    }
    // public static void main(String[] args) throws Exception {
    //     File ex = new File("/home/xuanquynh/Downloads/OOP/UML-Parser-Visualizer-master/Test/Data.java");
    //     DataClass dataclass = new DataClass(ex);
    // }
}
