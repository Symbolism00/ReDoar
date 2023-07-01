package util;

import java.math.BigDecimal;

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

    /**
     * Method that checks if an integer is less or equal than zero
     * @param number the integer to compare
     * @return true if it is, false if not
     */
    public static boolean isZeroOrNegative(Integer number){
        return number != null && number <= 0;
    }

    /**
     * Method that checks if a double is less or equal than zero
     * @param number the double to compare
     * @return true if it is, false if not
     */
    public static boolean isZeroOrNegative(Double number){
        return number != null && number <= 0;
    }

    /**
     * Method that checks if a latitude is valid
     * @param latitude the latitude to compare
     * @return true if it is, false if not
     */
    public static boolean isValidLatitude(BigDecimal latitude){
        return latitude != null && latitude.doubleValue() >= GlobalConstant.MIN_LATITUDE
                && latitude.doubleValue() <= GlobalConstant.MAX_LATITUDE;
    }

    /**
     * Method that checks if a longitude is valid
     * @param longitude the longitude to compare
     * @return true if it is, false if not
     */
    public static boolean isValidLongitude(BigDecimal longitude){
        return longitude != null && longitude.doubleValue() >= GlobalConstant.MIN_LONGITUDE
                && longitude.doubleValue() <= GlobalConstant.MAX_LONGITUDE;
    }
}
