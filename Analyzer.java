import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 
public class Analyzer {
 
    public static void main(String[] args) throws IOException { 
        boolean shortStat = false;
        boolean fullStat = false;
        boolean addData = false;
        ArrayList<String> array = new ArrayList<String>();

        ArrayList<String> files = new ArrayList<String>();
        for (String each : args) {
            if (each.endsWith(".txt")) {
                File f = new File(each);
                if(f.exists() && !f.isDirectory()) { 
                    files.add(each);
                }
            } else switch (each) {
                case "-s":
                    shortStat = true;
                    break;
                case "-f":
                    fullStat = true;
                    break;
                case "-a":
                    addData = true;
                default:
                    break;
            }
        }
        
        for (String filePath : files) {
            Scanner sc = new Scanner(new FileReader(filePath)).useDelimiter("\n"); // перенос в класс Sorter ??
            String str;
            while (sc.hasNext()) { 
                str = sc.next(); // файл не пустой??????
                String regex = "[\\p{C}]"; 
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(str);
                String cleanStr = matcher.replaceAll("");
                array.add(cleanStr);
            }
            if (sc != null) {
                sc.close();
            }
        }
      
        Sorter sorter = new Sorter(array);
        sorter.sort();
        sorter.output(addData); // TODO удаление старых txt?
        if (fullStat || (shortStat && fullStat)) {
            sorter.fullStat();
        } 
        if (shortStat) { 
            sorter.shortStat(); 
        }
        
    }

}