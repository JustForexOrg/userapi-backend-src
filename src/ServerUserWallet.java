import javax.ws.rs.*;

@Path("/wallet")
public class ServerUserWallet {
    @GET
    @Produces("text/plain")
    public String getUserWallet() {
        return "wallet not yet implemented";
    }
}
