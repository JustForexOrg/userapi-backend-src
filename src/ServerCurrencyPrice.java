import apidata.CurrencyPrice;
import utils.JFCurrency;

import javax.ws.rs.*;
import java.io.IOException;
import java.time.LocalDateTime;

@Path("/currency_price")
public class ServerCurrencyPrice {
    @GET
    @Produces("text/plain")
    public String getPrice(@DefaultValue("USD") @QueryParam("target") String target,
                           @DefaultValue("USD") @QueryParam("base") String base,
                           @QueryParam("time") String time) throws IOException {
        // time format: e.g. 2007-12-03T10:15:30
        JFCurrency targetJFCurrency = utils.ServerUtils.currencyFromString(target);
        JFCurrency baseJFCurrency = utils.ServerUtils.currencyFromString(base);
        LocalDateTime localDateTime = utils.ServerUtils.timeFromString(time);
        return String.valueOf(CurrencyPrice.getPrice(targetJFCurrency, baseJFCurrency, localDateTime));
    }
}