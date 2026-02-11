import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
//import java.io.PrintWriter;
import java.util.ArrayList;

public class Sorter {
    private Integer numberOfStrings;
    private Integer numberOfIntegers;
    private Integer numberOfFloats;
    private Long intMin;
    private Long intMax;
    private Long intSum;
    private Float floatMin;
    private Float floatMax;
    private Float floatSum;
    private Integer strMin;
    private Integer strMax;
    private ArrayList<String> array;
    private ArrayList<String> arrayOfStrings = new ArrayList<>();
    private ArrayList<String> arrayOfIntegers = new ArrayList<>();
    private ArrayList<String> arrayOfFloats = new ArrayList<>();
    private String[] = {};
    public Sorter(ArrayList<String> array) {
        this.numberOfStrings = 0;
        this.numberOfIntegers = 0;
        this.numberOfFloats = 0;
        this.floatSum = Float.valueOf(0);
        this.intSum = Long.valueOf(0);
        this.array = array;
    }
    private static boolean isFloat(String str) {
        if (str == null) {
            return false;
        }
        try {
            Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }   
    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }   
    public void sort() {
        for (String str : array) {
            if (isInteger(str)) { 
                arrayOfIntegers.add(str);
                numberOfIntegers++;
                Long intN = Long.parseLong(str);
                intSum += intN;
                if (numberOfIntegers == 1) {
                    intMax = intN;
                    intMin = intN;
                } else {
                    if (intN > intMax) {
                        intMax = intN;
                    } 
                    if (intN < intMin) {
                        intMin = intN;
                    }
                }
            } else if (isFloat(str)) { 
                arrayOfFloats.add(str);
                numberOfFloats++;
                Float floatN = Float.parseFloat(str);
                floatSum += floatN;
                if (numberOfFloats == 1) {
                    floatMax = floatN;
                    floatMin = floatN;
                } else {
                    if (floatN > floatMax) {
                        floatMax = floatN;
                    } 
                    if (floatN < floatMin) {
                        floatMin = floatN;
                    }
                }
            } else {
                arrayOfStrings.add(str);
                numberOfStrings++;
                int size = str.length();
                if (numberOfStrings == 1) {
                    strMax = size;
                    strMin = size;
                } else {
                    if (size > strMax) {
                        strMax = size;
                    } 
                    if (size < strMin) {
                        strMin = size;
                    }
                }
            }
        }
    }
    private boolean pathCheck (String path) {
        try {
        Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }
    private String nameCheck (String name) {
        if (name == null) {
            return "";
        }
        return name;
    }
    private File createFile(File theDir, String name) {
        File file;
        if (theDir != null) {
            file = new File(theDir + "\\" + name);
        } else {
            file = new File(name);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public void output(boolean a, String path, String name) throws IOException { // TODO задать путь -o
        name = nameCheck(name); // TODO сделать нормальный неймчек 
        if (pathCheck(path)) {
            File theDir = new File(path);
            theDir.mkdirs();
            write(createFile(theDir, name + "integers.txt"), arrayOfIntegers, a);
            write(createFile(theDir, name + "floats.txt"), arrayOfFloats, a);
            write(createFile(theDir, name + "strings.txt"), arrayOfStrings, a);
        } else {
            write(createFile(null, name + "integers.txt"), arrayOfIntegers, a);
            write(createFile(null, name + "floats.txt"), arrayOfFloats, a);
            write(createFile(null, name + "strings.txt"), arrayOfStrings, a);
        }
    }
    private void write(File file, ArrayList<String> toWrite, boolean a) throws IOException {
        if (toWrite.size() > 0) {
            try (FileWriter writer = new FileWriter(file, a)) {
                for (String each : toWrite) {
                    writer.write(each + "\n");
                }
                writer.close();
            }
            catch(IOException ex) {
            System.out.println(ex.getMessage());
            } 
        }
    }
    public void shortStat() {
        if (numberOfIntegers > 0) {
            System.out.println("Number of integers: " + numberOfIntegers);
        }
        if (numberOfFloats > 0) {
            System.out.println("Number of floats: " + numberOfFloats);
        }
        if (numberOfStrings > 0) {
            System.out.println("Number of strings: " + numberOfStrings);
        }
    }
    public void fullStat() {
        if (numberOfIntegers > 0) {
            System.out.println("INTEGERS\nNumber of integers: " + numberOfIntegers);
            System.out.println("Average of integers: " + intSum/numberOfIntegers);
            System.out.println("Summary of integers: " + intSum);
            System.out.println("Min of integers: " + intMin);
            System.out.println("Maximum of integers: " + intMax);
        }
        if (numberOfFloats > 0) {
            System.out.println("FLOATS\nNumber of floats: " + numberOfFloats);
            System.out.println("Average of floats: " + floatSum/numberOfFloats);
            System.out.println("Summary of floats: " + floatSum);
            System.out.println("Min of floats: " + floatMin);
            System.out.println("Maximum of floats: " + floatMax);
        }
        if (numberOfStrings > 0) {
            System.out.println("STRINGS\nNumber of strings: " + numberOfStrings);
            System.out.println("Minimum length of strings: " + strMin);
            System.out.println("Maximum length of strings: " + strMax);
        }
    }
}
