package apidata;

import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;

import static utils.JSONUtils.formatMonthString;
import static utils.JSONUtils.monthToQuarter;
import static utils.JSONUtils.readJsonFromUrl;

public class EconomicIndicator {
    public static double getInterestRate(String indicator, LocalDateTime time) throws IOException {
        String url = "http://stats.oecd.org/SDMX-JSON/data/MEI_FIN/IRLT." +
                indicator +
                ".M/all?startTime=" +
                time.getYear() + "-" + formatMonthString(time.getMonthValue()) +
                "&endTime=" +
                time.getYear() + "-" + formatMonthString(time.getMonthValue()) +
                "&dimensionAtObservation=MeasureDimension&detail=DataOnly";

        JSONObject jsonDocument = readJsonFromUrl(url);
        return getInterestRateFromJSON(jsonDocument);
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
        String url = "http://stats.oecd.org/SDMX-JSON/data/MEI_PRICES/CPALTT01." +
                indicator +
                ".GY.M/all?startTime=" +
                time.getYear() + "-" + formatMonthString(time.getMonthValue()) +
                "&endTime=" +
                time.getYear() + "-" + formatMonthString(time.getMonthValue()) +
                "&dimensionAtObservation=MeasureDimension&detail=DataOnly";

        JSONObject jsonDocument = readJsonFromUrl(url);
        return getInflationRateFromJSON(jsonDocument);
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
        String url = "http://stats.oecd.org/SDMX-JSON/data/QNA/" +
                indicator +
                ".B1_GE.GPSA.Q/all?startTime=" +
                time.getYear() + "-" + monthToQuarter(time.getMonthValue()) +
                "&endTime=" +
                time.getYear() + "-" + monthToQuarter(time.getMonthValue());

        JSONObject jsonDocument = readJsonFromUrl(url);
        return getGrowthRateFromJSON(jsonDocument);
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
