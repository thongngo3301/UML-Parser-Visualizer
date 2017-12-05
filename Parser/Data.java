package Parser;

import java.util.ArrayList;

public class Data{
    //attribute
    private static final ArrayList<String> types = new ArrayList<String>();
    private static final ArrayList<String> visibilitys = new ArrayList<String>();
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
    public final ArrayList<String> getTypes() {
        return types;
    }
    public final ArrayList<String> getVisibilitys() {
        return visibilitys;
    }
    //method
    public boolean isType(String type) {
        return this.getTypes().contains(type);
    }
    public boolean isVisibility(String visibility) {
        return this.getVisibilitys().contains(visibility);
    }
    public String toStringVisibility(String visibility) {
        String temp = "\n";
        if(visibility.equals("public")) temp = temp.concat(" + ");
        if(visibility.equals("private")) temp = temp.concat(" - ");
        if(visibility.equals("protected")) temp = temp.concat(" # ");
        if(visibility.equals("default")) temp = temp.concat(" ~ ");
        return temp;
    }
}