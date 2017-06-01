package apidata;

import utils.JFCurrency;

import java.time.LocalDateTime;

public interface ICurrencyPriceStat {
    float getHourStat(LocalDateTime time, JFCurrency currency, boolean isHigh);
    float getDayStat(LocalDateTime time, JFCurrency currency, boolean isHigh);
    float getWeekStat(LocalDateTime time, JFCurrency currency, boolean isHigh);
    float getMonthStat(LocalDateTime time, JFCurrency currency, boolean isHigh);
    float get6MonthStat(LocalDateTime time, JFCurrency currency, boolean isHigh);
    float getYearStat(LocalDateTime time, JFCurrency currency, boolean isHigh);
}
