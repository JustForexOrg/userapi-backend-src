package apidata;

import utils.JFCurrency;
import java.time.LocalDateTime;

// Testing file
public class readFileTest {

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.parse("2014-01-02T01:02:00");
        LocalDateTime date2 = LocalDateTime.parse("2012-01-02T01:02:00");

        float t = 1000;

//        System.out.println(DateWrapper.time(date));
        float lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.USD, date));
        float lEndTime = System.nanoTime();
        float output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.USD, JFCurrency.EUR, date));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.EUR, date));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.USD, JFCurrency.USD, date2));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.GBP, date));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        System.out.println("\n");

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.USD, date));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.USD, JFCurrency.EUR, date));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.EUR, date));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.USD, JFCurrency.USD, date2));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

        lStartTime = System.nanoTime();
        System.out.print(CurrencyPrice.getPrice(JFCurrency.EUR, JFCurrency.GBP, date));
        lEndTime = System.nanoTime();
        output = lEndTime - lStartTime;
        System.out.println(" | Time: " + output/t);

    }

}
