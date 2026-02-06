import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Sorter {
    private int numberOfStrings;
    private int numberOfIntegers;
    private int numberOfFloats;
    private int intMin;
    private int intMax;
    private int intSum = 0;
    private float floatMin;
    private float floatMax;
    private float floatSum = 0;
    private int strMin;
    private int strMax;
    private ArrayList<String> array;
    private ArrayList<String> arrayOfStrings = new ArrayList<>();
    private ArrayList<String> arrayOfIntegers = new ArrayList<>();
    private ArrayList<String> arrayOfFloats = new ArrayList<>();
    public Sorter(ArrayList<String> array) {
        this.numberOfStrings = 0;
        this.numberOfIntegers = 0;
        this.numberOfFloats = 0;
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
            Integer.parseInt(str);
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
                Integer intN = Integer.parseInt(str);
                intSum =+ intN;
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
                floatSum =+ floatN;
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
    private void write(String filename, ArrayList<String> toWrite) throws IOException {
        if (toWrite.size() > 0) { // TODO оптимизация?
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for (String each : toWrite) {
                writer.println(each);
            }
            writer.close();
        }
    }
    public void output() throws IOException {
        write("integers.txt", arrayOfIntegers);
        write("floats.txt", arrayOfFloats);
        write("strings.txt", arrayOfStrings);
    }
    public void shortStat() {
        if (numberOfIntegers > 0) {
            System.out.println("Number of integers: " + numberOfIntegers);
        }
        if (numberOfFloats > 0) {
            System.out.println("Number of real: " + numberOfFloats);
        }
        if (numberOfStrings > 0) {
            System.out.println("Number of strings: " + numberOfStrings);
        }
    }
    public void fullStat() {// TODO 
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
