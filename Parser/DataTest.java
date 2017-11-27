package Parser;

public class DataTest{
    public static void main(String[] args){
        try {
			DataProject dataproject = new DataProject("C:\\Users\\Capital Poker\\Desktop\\Bai tap lon\\projectTest");
			dataproject.display();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}