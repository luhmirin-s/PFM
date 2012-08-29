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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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
    	em.setProperty("eclipselink.jpa.uppercase-column-names", true);
    	Query query = em.createNamedQuery("findAccountBalance");
    	query.setParameter(1, id);
    	List<Balance> list = query.getResultList();
    	
    	if (!list.isEmpty()) {
    		GenericEntity<List<Balance>> entity = new GenericEntity<List<Balance>>(list) {};
    		return Response.ok(entity).build();
    	} 
    		
    	return Response.status(Status.NOT_FOUND).build();
    }
}