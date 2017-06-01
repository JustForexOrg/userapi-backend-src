package apidata;

import utils.JFCurrency;

import java.time.LocalDateTime;

public interface ICurrencyPrice {
    float getPrice(JFCurrency targetCurrency, JFCurrency baseCurrency, LocalDateTime time);
}
