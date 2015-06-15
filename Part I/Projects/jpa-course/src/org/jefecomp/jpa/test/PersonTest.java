/**
 * 
 */
package org.jefecomp.jpa.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jefecomp.jpa.dao.PersistenceUnitEnum;
import org.jefecomp.jpa.dao.impl.PersonDAO;
import org.jefecomp.jpa.entities.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jefecomp
 *
 */
public class PersonTest {

	private PersonDAO personDAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		this.personDAO = new PersonDAO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
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
		
		assertTrue(this.personDAO.persist(person));
	}

	/**
	 * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#update(java.lang.Object)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#delete(java.lang.Object)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#findByPrimaryKey(java.lang.Class, java.lang.Object)}.
	 */
	@Test
	public void testFindAll() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PersistenceUnitEnum.JPA_COURSE_PU.getPersistenceUnitName());
		
		EntityManager em = emFactory.createEntityManager();
		
	Query q = em.createQuery("Select p from Person p");
	
	List persons = q.getResultList();
	
	Person p = null;
	for(int i= 0; i < persons.size(); i++){
		
		p = (Person)persons.get(i);
		
		System.out.println("ID: "+p.getId());
		System.out.println("NAME:"+p.getName());
		System.out.println("SURNAME:"+p.getSurname());
	}
		
		
	}

}
