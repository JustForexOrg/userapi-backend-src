package utils;

import java.time.LocalDateTime;

public class ServerUtils {
    public static JFCurrency currencyFromString(String s) throws IllegalArgumentException{
        return JFCurrency.valueOf(s);
    }

    public static LocalDateTime timeFromString(String time) {
        return LocalDateTime.parse(time);
    }
}
