package pfm.jaxrs;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import pfm.model.helper.JournalEntry;
import pfm.model.helper.JournalEntryType;

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
