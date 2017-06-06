import apidata.EconomicIndicator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EconomicIndicatorsTests {
    @Test
    public void servesCorrectJapanInterestRateFebruary2017() {
        double expectedToBeCloseTo = 0.05;
        double testValue = 0;
        try {
            testValue = EconomicIndicator.getInterestRate("JPN",
                    LocalDateTime.of(2017, 2, 14, 12, 0));
        } catch (IOException e) {
            System.err.println("JSON parsing error");
        }
        double difference = testValue - expectedToBeCloseTo;

        assertTrue(-0.005 <= difference && difference <= 0.005);
    }

    @Test
    public void servesCorrectJapanInterestRateFebruary2017ShouldUseCachedValue() {
        servesCorrectJapanInterestRateFebruary2017();
    }

    @Test
    public void servesCorrectTurkeyInflationRateDecember2016() {
        double expectedToBeCloseTo = 8.5;
        double testValue = 0;
        try {
            testValue = EconomicIndicator.getInflationRate("TUR",
                    LocalDateTime.of(2016,12, 3, 14, 0));
        } catch (IOException e) {
            System.err.println("JSON parsing error");
        }
        double difference = testValue - expectedToBeCloseTo;

        assertTrue(-0.05 <= difference && difference <= 0.05);
    }

    @Test
    public void servesCorrectTurkeyInflationRateDecember2016ShouldUseCachedValue() {
        servesCorrectTurkeyInflationRateDecember2016();
    }

    @Test
    public void servesCorrectJapanInflationRateFebruary2017() {
        double expectedToBeCloseTo = 0.3;
        double testValue = 0;
        try {
            testValue = EconomicIndicator.getInflationRate("JPN",
                    LocalDateTime.of(2017,2, 3, 14, 0));
        } catch (IOException e) {
            System.err.println("JSON parsing error");
        }
        double difference = testValue - expectedToBeCloseTo;

        assertTrue(-0.05 <= difference && difference <= 0.05);
    }

    @Test
    public void servesCorrectJapanInflationRateFebruary2017ShouldUseCachedValue() {
        servesCorrectJapanInflationRateFebruary2017();
    }

    @Test
    public void servesCorrectUKGrowth2015Q3() {
        double expectedToBeCloseTo = 0.3;
        double testValue = 0;
        try {
            testValue = EconomicIndicator.getQuarterlyGrowth("GBR",
                    LocalDateTime.of(2015,8, 3, 14, 0));
        } catch (IOException e) {
            System.err.println("JSON parsing error");
        }
        double difference = testValue - expectedToBeCloseTo;

        assertTrue(-0.05 <= difference && difference <= 0.05);
    }

    @Test
    public void servesCorrectUKGrowth2015Q3ShouldUseCachedValue() {
        servesCorrectUKGrowth2015Q3();
    }
}
