package Parser;

import java.io.File;
import java.util.ArrayList;

public class DataProject{
    //attribute
    private File file;
    private File[] files;
    private ArrayList<DataClass> dataClasses;
    //constructor
    public DataProject(String path) throws Exception{
        this.file = new File(path);
        this.files = file.listFiles();
        dataClasses = new ArrayList<DataClass>();
        for(File temp : files){
            DataClass dataclass = new DataClass(temp);
            this.addDataClasses(dataclass);
        }
    }
    //setter getter
    public File getFile() {
        return file;
    }
    public File[] getFiles() {
        return files;
    }
    public void addDataClasses(DataClass dataclass){
        this.dataClasses.add(dataclass);
    }
    public ArrayList<DataClass> getDataClasses() {
        return dataClasses;
    }
    //method
    public void display(){
        System.out.printf("Project: %s", getFile().getName());
        for (DataClass var : getDataClasses()) {
            var.display();
        }
    }

}