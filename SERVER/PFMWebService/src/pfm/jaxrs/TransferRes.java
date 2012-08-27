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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import pfm.model.Account;
import pfm.model.Currency;
import pfm.model.Income;
import pfm.model.Source;
import pfm.model.Transfer;

@Path("transfer")
@Stateless
public class TransferRes {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    public TransferRes() {
    }
    
    @POST
	@Consumes("application/json")
	public Response postJson(Transfer trs) {
		try {
			Account fromAcc = em.find(Account.class, trs.getFromAccountId());
			Account toAcc = em.find(Account.class, trs.getToAccountId());
			Currency cur = em.find(Currency.class, trs.getCurrencyId());
			
			trs.setFromAccount(fromAcc);
			trs.setToAccount(toAcc);
			trs.setCurrency(cur);
			
			em.persist(trs);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
    
    @DELETE
    @Path("/{transferId}")
    public Response deleteJson(@PathParam("transferId") int id) {
    	try {
    		Transfer trs = em.find(Transfer.class, id);
        	em.remove(trs);
        	return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
    }
}