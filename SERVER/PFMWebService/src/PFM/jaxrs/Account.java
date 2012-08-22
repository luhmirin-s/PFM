package PFM.jaxrs;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import model.Employee;

@Path("account")
@Stateless
public class Account {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @PersistenceContext
    EntityManager em;

    /**
     * Default constructor. 
     */
    public Account() {
    	
    }

    /**
     * Retrieves representation of an instance of Account
     * @return an instance of String
     */
    @GET
    @Produces("application/json")
    public String getJson() {       
    	//Employee emp = new Employee();
    	//emp.setFirstName("gennadij");
    	//emp.setLastName("lokenbah");
    	//emp.setLevel(2);
        //em.persist(emp);
        
    	Employee emp = em.find(Employee.class, 2);
        return emp.getFirstName();
    }

    /**
     * PUT method for updating or creating an instance of Account
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}