package apidata;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

// wrapper for localDataTime to convert into epoch times (in milliseconds)
// In:  yyyy-mm-ddThh:mm:ss
// Out: epoch time
public class DateWrapper {

    public static String time(LocalDateTime time) {
        return parseTime(time);
    }

    private static String parseTime(LocalDateTime time) {
        ZoneOffset offset = ZoneOffset.UTC;
        return String.valueOf(time.toEpochSecond(offset)*1000);
    }

}
