package pfm.jaxrs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import pfm.model.Category;
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
	public Response getJsonList() {
    	try {
			List<Currency> results = em.createQuery("SELECT c FROM Currency c").getResultList();
			GenericEntity<List<Currency>> entity = new GenericEntity<List<Currency>>(results) {};
			return Response.ok(entity).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
    	
	}
}