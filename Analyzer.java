import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 
public class Analyzer {
 
    public static void main(String[] args) throws IOException { 
        String filePath = "1.txt"; // TODO получение списка txt из args[]
        boolean shortStat = true;
        boolean fullStat = true;
        ArrayList<String> array = new ArrayList<String>();
        Scanner sc = new Scanner(new FileReader(filePath)).useDelimiter("\n"); // TODO перенос в класс Sorter
        String str;
      
        while (sc.hasNext()) { // TODO перенос в класс Sorter
            str = sc.next();
            String regex = "[\\p{C}]"; // removing invisible characters
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            String cleanStr = matcher.replaceAll("");
            array.add(cleanStr);
        }
        if (sc != null) {
            sc.close();
        }
      
        Sorter sorter = new Sorter(array);
        sorter.sort();
        sorter.output(); // удаление старых txt?
        if (shortStat) { 
            sorter.shortStat(); // только при наличии команды
        }
        if (fullStat) {
            sorter.fullStat();
        } 
    }

}