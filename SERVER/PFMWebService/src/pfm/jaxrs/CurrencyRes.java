package pfm.jaxrs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import pfm.model.Currency;

@Path("currency")
@Stateless

public class CurrencyRes {
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    public CurrencyRes() {
    }

    @SuppressWarnings("unchecked")
	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Currency> getJsonList() {
    	return em.createQuery("SELECT c FROM Currency c").getResultList();
	}
}