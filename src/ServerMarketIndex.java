import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/marketIndex/{index}")
public class ServerMarketIndex {
    @GET
    @Produces("text/plain")
    public String getIndicator(@PathParam("index") String index) {
        System.out.println("Sorry, still writing code!");

        // TODO: Implement and delegate this to other methods - enum?
        switch (index) {
            default:
        }
        return "economic market indicators not yet implemented";
    }
}
