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

@Path("account")
@Stateless
public class AccountRes {
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    public AccountRes() {
    }
    
    @GET
    @Path("/{accountId}")
    @Produces("application/json")
    public Account getJson(@PathParam("accountId") int id) {
        Account acc = em.find(Account.class, id);
        return acc;
    }
    
    @GET
    @Path("/list/{userId}")
    @Produces("application/json")
    public List<Account> getJsonList(@PathParam("userId") int id) {
    	User user = em.find(User.class, id);
    	return user.getAccounts();
    }
    
    @POST
	@Consumes("application/json")
	public void postJson(Account acc) {
		try {
			User user = em.find(User.class, acc.getUserId());
			acc.setUser(user);
			em.persist(acc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @PUT
    @Consumes("application/json")
    public void putJson(Account acc) {
    	try {
    		User user = em.find(User.class, acc.getUserId());
			acc.setUser(user);
			em.merge(acc);	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @DELETE
    @Path("/{accountId}")
    public void deleteJson(@PathParam("accountId") int id) {
    	try {
    		Account acc = em.find(Account.class, id);
        	em.remove(acc);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
