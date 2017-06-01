package apidata;

import java.time.LocalDateTime;

public interface IEconomicIndicator {
    // TODO: enumerate datasets i.e. Bank of England base rate, Federal Reserve Base Rate etc.

    float getInterestRate(LocalDateTime time); // TODO add enum parameter or just use currency?
    float getInflationRate(LocalDateTime time); // TODO add enum parameter or just use currency?
    float getQuarterlyGrowth(LocalDateTime time); // TODO add enum parameter or just use currency?
    float getCreditRating(LocalDateTime time); // TODO add enum parameter or just use currency?
}
