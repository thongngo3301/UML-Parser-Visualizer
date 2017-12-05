package Parser;

public class DataTest{
    public static void main(String[] args){
        try {
			DataProject dataproject = new DataProject("Test");
			System.out.printf("%s", dataproject.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}