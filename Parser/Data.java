package Parser;

import java.util.ArrayList;

public class Data {

    //attribute
    private static final ArrayList<String> types = new ArrayList<String>();
    private static final ArrayList<String> visibilitys = new ArrayList<String>();

    //constructor
    public Data() {
        if (getVisibilitys().isEmpty()) {
            getVisibilitys().add("public");
            getVisibilitys().add("private");
            getVisibilitys().add("protected");
        }
    }

    //setter getter
    public void addTypes(String type) {
        getTypes().add(type);
    }

    public static final ArrayList<String> getTypes() {
        return types;
    }

    public static final ArrayList<String> getVisibilitys() {
        return visibilitys;
    }

    //method
    public static boolean isType(String type) {
        return getTypes().contains(type);
    }

    public static boolean isVisibility(String visibility) {
        return getVisibilitys().contains(visibility);
    }

    public String toStringVisibility(String visibility) {
        String temp = "\n";
        if (visibility.equals("public")) {
            temp = temp.concat(" + ");
        }
        if (visibility.equals("private")) {
            temp = temp.concat(" - ");
        }
        if (visibility.equals("protected")) {
            temp = temp.concat(" # ");
        }
        if (visibility.equals("default")) {
            temp = temp.concat(" ~ ");
        }
        return temp;
    }
}
