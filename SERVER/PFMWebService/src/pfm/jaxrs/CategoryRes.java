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

import pfm.model.Category;
import pfm.model.User;


@Path("category")
@Stateless
public class CategoryRes {
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    public CategoryRes() {
    }
    
    @GET
    @Path("/{categoryId}")
    @Produces("application/json")
    public Category getJson(@PathParam("categoryId") int id) {
        Category cat = em.find(Category.class, id);
        return cat;
    }
    
	@GET
	@Path("/list/{userId}")
	@Produces("application/json")
	public List<Category> getJsonList(@PathParam("userId") int id) {
		User user = em.find(User.class, id);
	    return user.getCategories();	
	}
	
	@POST
	@Consumes("application/json")
	public void postJson(Category cat) {
		try {
			User user = em.find(User.class, cat.getUserId());
			cat.setUser(user);
			em.persist(cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @PUT
    @Consumes("application/json")
    public void putJson(Category cat) {
    	try {
    		User user = em.find(User.class, cat.getUserId());
			cat.setUser(user);
			em.merge(cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @DELETE
    @Path("/{categoryId}")
    public void deleteJson(@PathParam("categoryId") int id) {
    	try {
    		Category cat = em.find(Category.class, id);
        	em.remove(cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}