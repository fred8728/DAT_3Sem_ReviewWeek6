package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("p")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/Person",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerson() {
        List<Person> person = FACADE.getAllPersons();
        return GSON.toJson(person);
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersons(@PathParam("id") int id){
        Person person = FACADE.getPerson(id);
        return GSON.toJson(person);
    }

    @POST
    @Produces ({MediaType.APPLICATION_JSON})
    public String addPerson(String person){
        Person p = GSON.fromJson(person, Person.class);
        Person p1 = FACADE.addPerson(p.getName(), p.getTalent());
        return GSON.toJson(p1);
    }
    
    @DELETE
    @Produces ({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public String deletePerson(@PathParam("id") int id){
        Person p = FACADE.getPerson(id);
        Person p1 = FACADE.deletePerson(p.getId());
        return GSON.toJson(p1);
    }
    
 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String editName(String p, @PathParam("id") int id) {
        Person change = FACADE.getPerson(id);
        Person personNew = GSON.fromJson(p, Person.class);
        change.setName(personNew.getName());
        return GSON.toJson(change);
    }
}
