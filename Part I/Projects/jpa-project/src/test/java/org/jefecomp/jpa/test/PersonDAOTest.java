/**
 * 
 */
package org.jefecomp.jpa.test;

import org.junit.Assert;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jefecomp.jpa.dao.PersistenceUnitEnum;
import org.jefecomp.jpa.dao.impl.PersonDAO;
import org.jefecomp.jpa.dao.impl.BookDAO;;
import org.jefecomp.jpa.entities.Address;
import org.jefecomp.jpa.entities.Person;
import org.jefecomp.jpa.entities.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jefecomp
 *
 */
public class PersonDAOTest {

    private PersonDAO personDAO;

    private BookDAO bookDAO;
	
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

	this.bookDAO = new BookDAO();
	this.personDAO = new PersonDAO();

	this.bookDAO.deleteAll(Book.class);
	this.personDAO.deleteAll(Person.class);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {

	this.bookDAO.deleteAll(Book.class);
	this.personDAO.deleteAll(Person.class);
	this.bookDAO = null;
	this.personDAO = null;
    }

    /**
     * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#persist(java.lang.Object)}.
     */
    @Test
    public void testPersist() {
	Person person = new Person();
		
	person.setName("Jeferson");
	person.setSurname("Souza");
		
	Address address = new Address();
		
	address.setCity("Lisboa");
	address.setState("SC");
		
	person.setAddress(address);
		
	Assert.assertTrue(this.personDAO.persist(person));
    }

    /**
     * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#update(java.lang.Object)}.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#delete(java.lang.Object)}.
     */
    @Test
    public void testDelete() {
		
    }

    /**
     * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#findByPrimaryKey(java.lang.Class, java.lang.Object)}.
     */
   @Test
    public void testFindAll() {

       this.testPersist();
 	
	List<Person> persons = this.personDAO.findAll(Person.class);

	Assert.assertFalse(persons.isEmpty());
	Assert.assertEquals(1,persons.size());
	
	Person p = null;
	for(int i= 0; i < persons.size(); i++){
		
	    p = (Person)persons.get(i);
		
	    System.out.println("ID: "+p.getId());
	    System.out.println("NAME:"+p.getName());
	    System.out.println("SURNAME:"+p.getSurname());
	    System.out.println("ADDRESS:"+p.getAddress());
	}
	       
    }

}
