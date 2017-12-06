package Parser;

public class DataTest {

    public static void main(String[] args) {
        try {
            DataProject dataproject = new DataProject("/home/xuanquynh/Downloads/OOP/Bai tap lon/projectTest");
            System.out.printf("%s", dataproject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
