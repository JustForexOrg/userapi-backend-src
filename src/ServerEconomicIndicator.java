import apidata.EconomicIndicator;

import javax.ws.rs.*;
import java.io.IOException;
import java.time.LocalDateTime;

@Path("/economicIndicator/{indicatorType}")

public class ServerEconomicIndicator {
        @GET
        @Produces("text/plain")
        public String getIndicator(@PathParam("indicatorType") String indicatorType,
                                   @QueryParam("indicator") String indicator,
                                   @QueryParam("time") String time) {
            LocalDateTime localDateTime = LocalDateTime.parse(time);
            try {
                switch (indicatorType) {
                    case "interestRate":
                        return String.valueOf(EconomicIndicator.getInterestRate(indicator, localDateTime));
                    case "inflationRate":
                        return String.valueOf(EconomicIndicator.getInflationRate(indicator, localDateTime));
                    case "quarterlyGrowth":
                        return String.valueOf(EconomicIndicator.getQuarterlyGrowth(indicator, localDateTime));
                }
            } catch (IOException ioe) {
                return "An error occurred";
            }
            return null;
        }
}
