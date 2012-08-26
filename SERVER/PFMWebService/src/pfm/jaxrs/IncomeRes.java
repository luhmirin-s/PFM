package pfm.jaxrs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import pfm.model.Account;
import pfm.model.Currency;
import pfm.model.Income;
import pfm.model.Source;

@Path("income")
@Stateless
public class IncomeRes {
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    public IncomeRes() {
    }
    
    @POST
	@Consumes("application/json")
	public void postJson(Income inc) {
		try {
			Account acc = em.find(Account.class, inc.getAccountId());
			Source src = em.find(Source.class, inc.getSourceId());
			Currency cur = em.find(Currency.class, inc.getCurrencyId());
			
			inc.setAccount(acc);
			inc.setSource(src);
			inc.setCurrency(cur);
			
			em.persist(inc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @DELETE
    @Path("/{incomeId}")
    public void deleteJson(@PathParam("incomeId") int id) {
    	try {
    		Income inc = em.find(Income.class, id);
        	em.remove(inc);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
