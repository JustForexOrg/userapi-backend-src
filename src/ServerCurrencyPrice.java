import utils.JFCurrency;

import javax.ws.rs.*;
import java.time.LocalDateTime;

@Path("/currency_price")
public class ServerCurrencyPrice {
    @GET
    @Produces("text/plain")
    public String getPrice(@DefaultValue("USD") @QueryParam("target") String target, @DefaultValue("USD") @QueryParam("base") String base, @QueryParam("time") String time) {
        JFCurrency targetJFCurrency = utils.ServerUtils.currencyFromString(target);
        JFCurrency baseJFCurrency = utils.ServerUtils.currencyFromString(base);
        LocalDateTime localDateTime = utils.ServerUtils.timeFromString(time);
        return "currency price not yet implemented";
    }
}