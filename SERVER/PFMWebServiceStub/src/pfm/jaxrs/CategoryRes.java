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

@Path("category")
@Stateless
public class CategoryRes {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;

    public CategoryRes() {
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Category getXml(@PathParam("id") int id) {    	
    	Category cat = (Category) em.find(Category.class, id);
    	return cat;	
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