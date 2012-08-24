package pfm.jaxrs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import pfm.model.Source;
import pfm.model.User;

@Path("source")
@Stateless
public class SourceRes {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @PersistenceContext
    EntityManager em;
    
    /**
     * Default constructor. 
     */
    public SourceRes() {
    }
    
    @GET
    @Produces("application/json")
    public Source getJsonTest() {
    	//User user = em.find(User.class, 1);
//    	User user = new User();
//    	user.setUsername("test233");
//    	user.setPassword("test");
//    	user.setEmail("test233");
//    	em.persist(user);
        Source src = new Source();
        src.setName("asd");
//        src.setUser(user);
//        em.persist(src);
        return src;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Source getJson(@PathParam("id") int id) {
        Source src = em.find(Source.class, id);
        return src;
    }
    
    @GET
    @Path("/list/{userId}")
    @Produces("application/json")
    public List<Source> getJsonList(@PathParam("userId") int userId) {
        User user = em.find(User.class, userId);
        return user.getSources();
    }

    /**
     * PUT method for updating or creating an instance of SourceRes
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

}