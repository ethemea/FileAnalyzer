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
        String path = null;
        String filename = null;

        ArrayList<String> files = new ArrayList<String>();
        int len = args.length;
        for (int i = 0; i < len; i++) {
            if (args[i].endsWith(".txt")) {
                File f = new File(args[i]);
                if(f.exists() && !f.isDirectory()) { 
                    files.add(args[i]);
                }
            } else switch (args[i]) {
                case "-s":
                    shortStat = true;
                    break;
                case "-f":
                    fullStat = true;
                    break;
                case "-a":
                    addData = true;
                    break;
                case "-p":
                    if (i + 1 < len) {
                        if (!(args[i+1].endsWith(".txt"))) {
                            filename = args[i+1];
                            i++;
                        }
                    }
                    break;
                case "-o":
                    if (i + 1 < len) {
                        if (!(args[i+1].endsWith(".txt"))) {
                            path = args[i+1];
                            i++;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        
        for (String filePath : files) {
            Scanner sc = new Scanner(new FileReader(filePath)).useDelimiter("\n"); // TODO перенос в класс Sorter ??
            String str;
            while (sc.hasNext()) { 
                str = sc.next(); 
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
        sorter.output(addData, path, filename);
        if (fullStat || (shortStat && fullStat)) {
            sorter.fullStat();
        } 
        if (shortStat) { 
            sorter.shortStat(); 
        }
        
    }

}