import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

/**
 * @author 24penry
 * @version 12/2/22
 * Base converter that takes a number with a certain base and converts it to a number with a different base
 */
public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */
    public int strToInt(String num, String fromBase)    {
        // "2AFB13" base-16 ==> ?? base-10
        int value = 0, exp = 0;
        /*
        start at rightmost digit of num
        run a loop for x or i digits of num-- backwards
            pull out character at index i
            find indexOf that character in DIGITS
            value += indexOf that character * Math.pow(fromBase, exp);
         */
        for (int i = num.length()-1; i >= 0; i--)  {
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp ++;
        }
        return value;
    }

    /**
     * Javadoc me
     * @param num the original number
     * @param toBase the base you want to convert the number to
     * @return the converted num or 0
     */
    public String intToStr(int num, int toBase) {
        if(num == 0)
            return "0";
        String toNum = new String();
        while(num > 0)  {
            toNum = DIGITS.charAt(num % toBase) + toNum;
            num /= toBase;
        }
        return toNum;
    }

    /**
     * It takes an input number than converts to a new base and prints to the screen and the output file
     */
    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter out = null;
        try   {
            in = new Scanner(new File("datafiles/values30.dat"));
            out = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line;
            while(in.hasNext())  {
                line = in.nextLine().split("\t");
                if(Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                    System.out.println("Invalid input base " + line[1]);
                else if(Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                    System.out.println("Invalid output base " + line[2]);
                else {
                    System.out.println(line[0] + " base " + line[1] + " = " +
                            intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2])) + " base " + line[2]);
                    out.println(line[0] + "\t" + line[1] + "\t" + intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2])) + "\t" + line[2]);
                }
            }
            if (out != null)
                out.close();
            if(in != null)
                in.close();
            //System.out.println("End of program");
        }
        catch(Exception e)   {
            System.out.println("Something bad happened. Details here: " + e.toString());
        }
    }

    /**
     * Main method of the BaseConverter class
     * @param args command-Line arguments, if needed
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();
    }
}