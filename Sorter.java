import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sorter {
    private boolean shortStat = false;
    private boolean fullStat = false;
    private boolean addData = false;
    private String path = null;
    private String filename = null;
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
    private ArrayList<String> array = new ArrayList<String>();
    private ArrayList<String> files = new ArrayList<String>();
    private ArrayList<String> arrayOfStrings = new ArrayList<>();
    private ArrayList<String> arrayOfIntegers = new ArrayList<>();
    private ArrayList<String> arrayOfFloats = new ArrayList<>();
    private static final String[] illegalChars = { "/", "\n", "\r", "\t", "\0", "\f", "`", "\\?", "\\*", "\\\\", "<", ">", "|", "\"", ":" };

    public Sorter() {
        this.numberOfStrings = 0;
        this.numberOfIntegers = 0;
        this.numberOfFloats = 0;
        this.floatSum = Float.valueOf(0);
        this.intSum = Long.valueOf(0);
    }

    public void readArgs(String[] args) {
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
    }

    public void readFiles() {
        for (String filePath : files) {
            try {
                Scanner sc = new Scanner(new FileReader(filePath)).useDelimiter("\n");
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
            } catch (FileNotFoundException ex) {
                System.out.println("Input files were nor found: " + ex.getMessage());
            }
        }
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
        } else {
            for (String ch : illegalChars) {
                name = name.replaceAll(ch.toString(), "");
            }
        }
        return name;
    }

    private File createFile(File theDir, String name) {
        File file;
        if (theDir != null) {
            file = new File(theDir, name);
        } else {
            file = new File(name);
        }
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.err.println("Failed to create file " + name + ": " + ex.getMessage());
            return null;
        }
        return file;
    }

    public void output() throws IOException {
        filename = nameCheck(filename); 
        if (pathCheck(path)) {
            String absolutePath = Paths.get("").toAbsolutePath().toString();
            File theDir = new File(absolutePath + path);
            try {
                theDir.mkdirs();
            } catch (Exception ex) {
                System.err.println("Failed to create directories: " + ex.getMessage());
            }
            write(createFile(theDir, filename + "integers.txt"), arrayOfIntegers);
            write(createFile(theDir, filename + "floats.txt"), arrayOfFloats);
            write(createFile(theDir, filename + "strings.txt"), arrayOfStrings);
        } else {
            write(createFile(null, filename + "integers.txt"), arrayOfIntegers);
            write(createFile(null, filename + "floats.txt"), arrayOfFloats);
            write(createFile(null, filename + "strings.txt"), arrayOfStrings);
        }
        if ((shortStat && fullStat) || fullStat) {
            fullStat();
        } else if (shortStat) {
            shortStat();
        }
    }
    private void write(File file, ArrayList<String> toWrite) throws IOException {
        if (toWrite.size() > 0) {
            try (FileWriter writer = new FileWriter(file, addData)) {
                for (String each : toWrite) {
                    writer.write(each + "\n");
                }
                writer.close();
            }
            catch (IOException | NullPointerException ex) {
                System.out.println("Failed to write output file");
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
            System.out.println("Max of integers: " + intMax);
        }
        if (numberOfFloats > 0) {
            System.out.println("FLOATS\nNumber of floats: " + numberOfFloats);
            System.out.println("Average of floats: " + floatSum/numberOfFloats);
            System.out.println("Summary of floats: " + floatSum);
            System.out.println("Min of floats: " + floatMin);
            System.out.println("Max of floats: " + floatMax);
        }
        if (numberOfStrings > 0) {
            System.out.println("STRINGS\nNumber of strings: " + numberOfStrings);
            System.out.println("Min length of strings: " + strMin);
            System.out.println("Max length of strings: " + strMax);
        }
    }
}
