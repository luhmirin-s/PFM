package pfm.jaxrs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
    @SuppressWarnings("unchecked")
    public Response getJsonList(@PathParam("accountId") int id) {
    	Query query = em.createNativeQuery("CALL findAccountBalance(?)", Balance.class);   
    	query.setParameter(1, id);
		List<Balance> list = query.getResultList();  
    	return Response.ok().build();
    }
}