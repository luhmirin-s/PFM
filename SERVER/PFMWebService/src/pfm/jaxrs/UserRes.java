package pfm.jaxrs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import pfm.model.User;

@Path("user")
@Stateless
public class UserRes {
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    public UserRes() {
    }
    
    @POST
    @Produces("application/json")
    public User postJson(@FormParam("username") String username, 
							@FormParam("password") String password) {
    	User user = em.createQuery("SELECT u FROM User u " +
    										"WHERE u.username = :username AND u.password = :password", 
    										User.class)
				    	.setParameter("username", username)
				    	.setParameter("password", password)
				    	.getSingleResult();
    	return user;
    }

    @POST
    @Consumes("application/json")
    public void postJson(User user) {
    	try {
			em.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


}