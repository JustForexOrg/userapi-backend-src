package apidata;

import org.json.JSONObject;
import utils.MyCache;

import java.io.IOException;
import java.time.LocalDateTime;

import static utils.JSONUtils.*;

public class EconomicIndicator {
    private static MyCache<Integer, Double> interestRateCache = new MyCache<>();
    private static MyCache<Integer, Double> inflationRateCache = new MyCache<>();
    private static MyCache<Integer, Double> quarterlyGrowthCache = new MyCache<>();

    public static double getInterestRate(String indicator, LocalDateTime time) throws IOException {
        int hash = ("" + time.getYear() + time.getMonth() + indicator).hashCode();

        Double cacheValue = interestRateCache.get(hash);

        if (cacheValue != null) {
            return cacheValue;
        }

        String url = "http://stats.oecd.org/SDMX-JSON/data/MEI_FIN/IRLT." +
                indicator +
                ".M/all?startTime=" +
                time.getYear() + "-" + formatMonthString(time.getMonthValue()) +
                "&endTime=" +
                time.getYear() + "-" + formatMonthString(time.getMonthValue()) +
                "&dimensionAtObservation=MeasureDimension&detail=DataOnly";

        JSONObject jsonDocument = readJsonFromUrl(url);
        double value = getInterestRateFromJSON(jsonDocument);
        interestRateCache.put(hash, value);
        return value;
    }

    private static double getInterestRateFromJSON(JSONObject j) {
        return j.getJSONArray("dataSets")
                .getJSONObject(0)
                .getJSONObject("series")
                .getJSONObject("0:0:0")
                .getJSONObject("observations")
                .getJSONArray("0")
                .getDouble(0);
    }

    public static double getInflationRate(String indicator, LocalDateTime time) throws IOException {
        int hash = ("" + time.getYear() + time.getMonth() + indicator).hashCode();

        Double cacheValue = inflationRateCache.get(hash);

        if (cacheValue != null) {
            return cacheValue;
        }

        String url = "http://stats.oecd.org/SDMX-JSON/data/MEI_PRICES/CPALTT01." +
                indicator +
                ".GY.M/all?startTime=" +
                time.getYear() + "-" + formatMonthString(time.getMonthValue()) +
                "&endTime=" +
                time.getYear() + "-" + formatMonthString(time.getMonthValue()) +
                "&dimensionAtObservation=MeasureDimension&detail=DataOnly";

        JSONObject jsonDocument = readJsonFromUrl(url);
        double value = getInflationRateFromJSON(jsonDocument);
        inflationRateCache.put(hash, value);
        return value;
    }

    private static double getInflationRateFromJSON(JSONObject j) {
        return j.getJSONArray("dataSets")
                .getJSONObject(0)
                .getJSONObject("series")
                .getJSONObject("0:0:0:0")
                .getJSONObject("observations")
                .getJSONArray("0")
                .getDouble(0);
    }

    public static double getQuarterlyGrowth(String indicator, LocalDateTime time) throws IOException {
        int hash = ("" + time.getYear() + monthToQuarter(time.getMonthValue()) + indicator).hashCode();

        Double cacheValue = quarterlyGrowthCache.get(hash);

        if (cacheValue != null) {
            return cacheValue;
        }

        String url = "http://stats.oecd.org/SDMX-JSON/data/QNA/" +
                indicator +
                ".B1_GE.GPSA.Q/all?startTime=" +
                time.getYear() + "-" + monthToQuarter(time.getMonthValue()) +
                "&endTime=" +
                time.getYear() + "-" + monthToQuarter(time.getMonthValue());

        JSONObject jsonDocument = readJsonFromUrl(url);
        double value = getGrowthRateFromJSON(jsonDocument);
        quarterlyGrowthCache.put(hash, value);
        return value;
    }

    private static double getGrowthRateFromJSON(JSONObject j) {
        return j.getJSONArray("dataSets")
                .getJSONObject(0)
                .getJSONObject("series")
                .getJSONObject("0:0:0:0")
                .getJSONObject("observations")
                .getJSONArray("0")
                .getDouble(0);
    }
}
