package pfm.jaxrs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("journal")
@Stateless
public class JournalRes {
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    public JournalRes() {
    }
    
//    @GET
//	@Path("/list/{accountId}")
//	@Produces("application/json")
//	public List<JournalEntry> getJsonList(@PathParam("accountId") int id,
//											@QueryParam("type") JournalEntryType type,
//											@QueryParam("from") Date from,
//											@QueryParam("to") Date to) {
//		em.createQuery("");
//	    return null;	
//	}
}
