import utils.JFCurrency;

import javax.ws.rs.*;
import java.time.LocalDateTime;

public class ServerCurrencyPriceStat {
    @Path("/currency_price_stat/{statType}")
    public class ServerCurrencyPrice {
        @GET
        @Produces("text/plain")
        public String getPriceStat(@PathParam("statType") String statType, @QueryParam("currency") String currency, @QueryParam("time") String time) {
            // TODO: highs and lows for each interval
            // Hour H/L, Day H/L, Week H/L, Month H/L, 6-month H/L, year H/L

            JFCurrency jfCurrency = utils.ServerUtils.currencyFromString(currency);

            // TODO: Implement and delegate this to other methods
            switch (statType) {
                case "hourLow":
                case "hourHigh":
                case "dayLow":
                case "dayHigh":
                case "weekLow":
                case "weekHigh":
                case "monthHigh":
                case "monthLow":
                case "6mWLow":
                case "6mHigh":
                case "yearLow":
                case "yearHigh":
                default:
            }
            return "currency price averages not yet implemented";
        }
    }
}
