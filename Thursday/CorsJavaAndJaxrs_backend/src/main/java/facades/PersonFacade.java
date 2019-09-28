package facades;

import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Person addPerson(String name, String talent){
        Person p = new Person();
        p = new Person(name, talent);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Person deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return p;
    }
    

    public Person getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Person.class, id);
        }
        finally{
            em.close();
        } 
    }

    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try{
            List <Person> getAll = em.createNamedQuery("Person.getAll").getResultList();
            return getAll;
        }finally{
            em.close();
        }
    }

    public Person updateName(Person p) {
        EntityManager em = emf.createEntityManager();
        p = new Person();
        TypedQuery query = em.createQuery("UPDATE Person p SET p.name:name where p.id =:id", Person.class);
        try {
            em.getTransaction().begin();
            em.setProperty("name", p.getName());
            em.setProperty("name", p.getTalent());
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
}
