package util;

public class NumberUtils {

    /**
     * Method that checks if a string is an integer
     * @param str the string to convert
     * @return true if it is, false if not
     */
    public static boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
