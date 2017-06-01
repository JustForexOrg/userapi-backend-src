import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public class ServerEconomicIndicator {
    @Path("/economicIndicator/{indicatorType}")
    public class ServerCurrencyPrice {
        @GET
        @Produces("text/plain")
        public String getIndicator(@PathParam("indicatorType") String indicatorType) {
            // TODO: Implement and delegate this to other methods
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
