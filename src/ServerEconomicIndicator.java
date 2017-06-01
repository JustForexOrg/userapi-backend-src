import javax.ws.rs.*;

@Path("/economicIndicator/{indicatorType}")

public class ServerEconomicIndicator {
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
