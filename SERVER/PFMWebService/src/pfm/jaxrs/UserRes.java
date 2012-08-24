package pfm.jaxrs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import pfm.model.User;

@Path("user")
@Stateless
public class UserRes {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    /**
     * Default constructor. 
     */
    public UserRes() {
    }
    
    @GET
    @Produces("application/json")
    public User getJsonTest() {
    	User user = em.find(User.class, 1);
    	//User user = new User();
    	//user.setUsername("test233");
    	//user.setPassword("test");
    	//user.setEmail("test233");
    	//em.persist(user);
        //Source src = new Source();
        //src.setName("asd");
        //src.setUser(user);
        //em.persist(src);
        return user;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public User getJson(@PathParam("id") int id) {
    	User user = em.find(User.class, id);
        return user;
    }

    @POST
    @Consumes("application/json")
    public void postJson(User user) {
    	//System.out.println("POST: " + user.getUsername());
    	em.persist(user);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(User user) {
    	em.persist(user);
    }

}