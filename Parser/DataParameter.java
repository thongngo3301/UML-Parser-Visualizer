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
    public final void setTypeParameter(String type) {
        this.typeParameter = type;
    }
    public String getTypeParameter(){
        return typeParameter;
    }
    public final void setNameParameter(String name){
        this.nameParameter = name;
    }
    public String getNameParameter(){
        return nameParameter;
    }
    @Override
    public String toString(){
        return (getNameParameter() + ": " + getTypeParameter()).trim();
    }
}