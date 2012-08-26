package pfm.jaxrs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import pfm.model.Account;
import pfm.model.User;
import pfm.model.helper.Balance;

@Path("balance")
@Stateless
public class BalanceRes {
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    public BalanceRes() {
    }
    
    @GET
    @Path("/list/{accountId}")
    @Produces("application/json")
    public List<Balance> getJsonList(@PathParam("accountId") int id) {

    	return null;
    }
}