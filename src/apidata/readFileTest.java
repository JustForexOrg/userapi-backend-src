package apidata;

import utils.JFCurrency;
import java.time.LocalDateTime;

// Testing file
public class readFileTest {

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.parse("2014-01-02T01:02:00");
        LocalDateTime date2 = LocalDateTime.parse("2012-01-02T01:02:00");

//        System.out.println(DateWrapper.time(date));
//
        System.out.println(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.USD, date));
        System.out.println("\n\n");
        System.out.println(CurrencyPrice.getPrice(JFCurrency.USD, JFCurrency.EUR, date));
        System.out.println(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.EUR, date));
        System.out.println(CurrencyPrice.getPrice(JFCurrency.USD, JFCurrency.USD, date2));

        System.out.println(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.GBP, date));
    }

}
