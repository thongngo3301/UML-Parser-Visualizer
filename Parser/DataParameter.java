package Parser;

public class DataParameter {
    //attribute
    private String typeParameter;
    private String nameParameter;
    //constructor
    public DataParameter(String type, String name){
        this.setTypeParameter(type);
        this.setNameParameter(name);
    }
    //setter getter
    public void setTypeParameter(String type) {
        this.typeParameter = type;
    }
    public String getTypeParameter(){
        return typeParameter;
    }
    public void setNameParameter(String name){
        this.nameParameter = name;
    }
    public String getNameParameter(){
        return nameParameter;
    }
    public void display(){
        System.out.printf("%s : %s", getNameParameter(), getTypeParameter());
    }
}