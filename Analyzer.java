import java.io.*;

 
public class Analyzer {
 
    public static void main(String[] args) throws IOException { 
        Sorter sorter = new Sorter();
        sorter.readArgs(args);
        sorter.readFiles();
        sorter.sort();
        sorter.output();        
    }

}