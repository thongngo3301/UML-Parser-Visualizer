package Parser;

import java.util.ArrayList;

public class Data{
    //attribute
    private static ArrayList<String> types = new ArrayList<String>();
    private static ArrayList<String> visibilitys = new ArrayList<String>();
    //constructor
    public Data(){
        if(this.getTypes().isEmpty()) {
            this.getTypes().add("boolean");this.getTypes().add("char");this.getTypes().add("int");
            this.getTypes().add("short");this.getTypes().add("long");this.getTypes().add("float");
            this.getTypes().add("double");
        }
        if(this.getVisibilitys().isEmpty()) {
            this.getVisibilitys().add("public");this.getVisibilitys().add("private");
            this.getVisibilitys().add("protected");
        }
    }
    //setter getter
    public void addTypes(String type) {
        this.getTypes().add(type);
    }
    public ArrayList<String> getTypes() {
        return types;
    }
    public ArrayList<String> getVisibilitys() {
        return visibilitys;
    }
    //method
    public boolean isType(String type) {
        if(this.getTypes().contains(type)) return true;
        return false;
    }
    public boolean isVisibility(String visibility) {
        if(this.getVisibilitys().contains(visibility)) return true;
        return false;
    }
    public void displayVisibility(String visibility) {
        switch(visibility){
            case "public":
                System.out.printf("\n + ");
                break;
            case "private":
                System.out.printf("\n - ");
                break;
            case "protected":
                System.out.printf("\n # ");
                break;
            case "default":
                System.out.printf("\n ~ ");
                break;
        }
    }
}