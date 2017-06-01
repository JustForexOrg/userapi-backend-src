import javax.ws.rs.*;

@Path("/marketIndex/{index}")
public class ServerMarketIndex {
    @GET
    @Produces("text/plain")
    public String getIndicator(@PathParam("index") String index, @QueryParam("time") String time) {
        System.out.println("Sorry, still writing code!");

        // TODO: Implement and delegate this to other methods - enum?
        switch (index) {
            default:
        }
        return "economic market indicators not yet implemented";
    }
}
