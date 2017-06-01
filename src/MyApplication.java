
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(ServerCurrencyPrice.class);
        h.add(ServerCurrencyPriceStat.class);
        h.add(ServerEconomicIndicator.class);
        h.add(ServerMarketIndex.class);
        h.add(ServerUserWallet.class);
        return h;
    }
}