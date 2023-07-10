package eco.solutions.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";
    public static final String HH_MI = "HH:mm";

    /**
     * Method that checks if one date is after the current date
     * @param date the date to compare
     * @param dateFormat the format to compare
     * @return true if it is, false if not
     */
    public static boolean isAfterCurrentDate(Date date, String dateFormat){
        return isAfter(date, new Date(), dateFormat);
    }

    /**
     * Method that checks if one date is after the other date
     * @param date1 the date 1 to compare
     * @param date2 the date 2 to compare
     * @param dateFormat the format to compare
     * @return true if it is, false if not
     */
    public static boolean isAfter(Date date1, Date date2, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        // convert dates to the same format
        String date1Str = sdf.format(date1);
        String date2Str = sdf.format(date2);
        // checks if date 1 is after date 2
        try {
            return sdf.parse(date1Str).after(sdf.parse(date2Str));
        } catch (ParseException e) {
            return false;
        }
    }
}
