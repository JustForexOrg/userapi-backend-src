package apidata;

import utils.JFCurrency;

import java.time.LocalDateTime;

public class CurrencyPrice {
    float getPrice(JFCurrency targetCurrency, JFCurrency baseCurrency, LocalDateTime t) {
        String year = String.valueOf(t.getYear());
        DateTime time  = new DateTime(t);
        String filename = "_"+year+".json";

        if(baseCurrency == JFCurrency.USD) {

        } else {
            filename = "src/stockData/" + targetCurrency.toString() + filename;
            // open file as array of arrays
            // search outer array for inner head of arrays & return the tail
        }
        return 0;
    }

}
