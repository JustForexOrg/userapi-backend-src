import javax.ws.rs.*;

public class ServerEconomicIndicator {
    @Path("/economicIndicator/{indicatorType}")
    public class ServerCurrencyPrice {
        @GET
        @Produces("text/plain")
        public String getIndicator(@PathParam("indicatorType") String indicatorType, @QueryParam("time") String time) {
            // TODO: Implement and delegate this to other methods - enums for each dataset
            switch (indicatorType) {
                case "interestRate":
                case "inflationRate":
                case "quarterlyGrowth":
                case "creditRating":
                default:
            }
            return "economic market indicators not yet implemented";
        }
    }
}
