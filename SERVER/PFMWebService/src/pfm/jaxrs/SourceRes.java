package pfm.jaxrs;

import java.util.ArrayList;
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

import pfm.model.Source;
import pfm.model.User;

@Path("/source")
@Stateless
public class SourceRes {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;

    public SourceRes() {
    }
    
    @GET
    @Path("/{sourceId}")
    @Produces("application/json")
    public Source getJson(@PathParam("sourceId") int id) {
        Source src = em.find(Source.class, id);
        return src;
    }
    
    @GET
    @Path("/list/{userId}")
    @Produces("application/json")
    public List<Source> getJsonList(@PathParam("userId") int id) {
    	User user = em.find(User.class, id);
    	return user.getSources();
    }
    
    @POST
	@Consumes("application/json")
	public void postJson(Source src) {
		try {
			User user = em.find(User.class, src.getUserId());
			src.setUser(user);
			em.persist(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @PUT
    @Consumes("application/json")
    public void putJson(Source src) {
    	try {
			User user = em.find(User.class, src.getUserId());
			src.setUser(user);
			em.merge(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @DELETE
    @Path("/{sourceId}")
    public void deleteJson(@PathParam("sourceId") int id) {
    	try {
    		Source src = em.find(Source.class, id);
        	em.remove(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}