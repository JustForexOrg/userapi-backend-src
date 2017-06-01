import utils.JFCurrency;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.time.LocalDateTime;

@Path("/currency_price")
public class ServerCurrencyPrice {
    @GET
    @Produces("text/plain")
    public String getPrice(@QueryParam("targetCurr") String target, @QueryParam("baseCurr") String base, @QueryParam("time") String time) {
        JFCurrency targetJFCurrency = utils.ServerUtils.currencyFromString(target);
        JFCurrency baseJFCurrency = utils.ServerUtils.currencyFromString(base);
        LocalDateTime localDateTime = utils.ServerUtils.timeFromString(time);
        return "currency price not yet implemented";
    }
}