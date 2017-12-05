package Parser;

import java.io.File;
import java.util.ArrayList;

public class DataProject{
    //attribute
    private final File file;
    private final File[] files;
    private final ArrayList<DataClass> dataClasses;
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
    public final void addDataClasses(DataClass dataclass){
        this.dataClasses.add(dataclass);
    }
    public ArrayList<DataClass> getDataClasses() {
        return dataClasses;
    }
    //method
    @Override
    public String toString() {
        String temp = "";
        temp = temp.concat("Project: " + getFile().getName());
        for (DataClass var : getDataClasses()) {
            temp = temp.concat(var.toString());
        }
        return temp;
    }
}