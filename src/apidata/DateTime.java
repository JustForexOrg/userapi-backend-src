package apidata;

import java.time.LocalDateTime;

// wrapper for Local data time
// In:  yyyy-mm-ddThh:mm:ss
// Out: yyyymmdd hhmmss
public class DateTime {

    String time;

    public static String time(LocalDateTime time) {
        return parseTime(time);
    }

    //    returns the time in the format in the json
    //    as data is minutely, just floor all times to 0 seconds
    private static String parseTime(LocalDateTime time) {
        return String.valueOf(time.getYear()) +
                   pad(String.valueOf(time.getMonthValue())) +
                   pad(String.valueOf(time.getDayOfMonth())) + " " +
                   pad(String.valueOf(time.getHour())) +
                   pad(String.valueOf(time.getMinute())) + "00";

    }

    //    pads string (which represents a m or d or hr or min or sec) to 2 digits
    private static String pad(String s) {
        return (s.length() == 1) ? "0" + s : s;
    }
}