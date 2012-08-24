package pfm.jaxrs;

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

import pfm.model.Category;
import pfm.model.Income;

@Path("income")
@Stateless
public class IncomeRes {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;

    public IncomeRes() {
    }

    @GET
    @Produces("application/json")
    public Income getXml() {    	
    	Income inc = new Income();
    	inc.setAmount(5);
    	return inc;	
    }

    @POST
    @Consumes("application/json")
    public void postXml(String content) {
    }

    @PUT
    @Consumes("application/json")
    public void putXml(String content) {
    }
    
    @DELETE
    @Path("/{id}")
    public void putXml(@PathParam("id") int id) {

    }

}