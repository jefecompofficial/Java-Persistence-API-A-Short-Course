/**
 * 
 */
package org.jefecomp.jpa.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jefecomp.jpa.dao.PersistenceUnitEnum;
import org.jefecomp.jpa.dao.impl.AuthorDAO;
import org.jefecomp.jpa.dao.impl.BookDAO;
import org.jefecomp.jpa.entities.Author;
import org.jefecomp.jpa.entities.Book;
import org.jefecomp.jpa.entities.Person;
import org.jefecomp.jpa.entities.Publisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jefecomp
 *
 */
public class BookDAOTest {

	private BookDAO bookDAO;
	
	private AuthorDAO authorDAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.bookDAO = new BookDAO();
		this.authorDAO = new AuthorDAO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.bookDAO = null;
	}

	/**
	 * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#persist(java.lang.Object)}.
	 */
	@Test
	public void testPersist() {
		
		Book book = new Book();
		Set<Author> authors = new HashSet<>();
		book.setTitle("Introduction to Algorithms");
		book.setEdition("3rd");
		
		Publisher p = new Publisher();
		
		Set<Book> books = new HashSet<>();
		books.add(book);
		
		p.setName("Cambridge Press");
		p.setBooks(books);
		
		book.setPublisher(p);
		
		
		
		Author author = new Author();
		author.setName("Thomas");
		author.setSurname("H. Cormen");
		
		books = new HashSet<>();
		books.add(book);
		author.setBooks(books);
		
		authors.add(author);
		
		author = new Author();
		
		author.setName("Charles");
		author.setSurname("E. Leiserson");
		books = new HashSet<>();
		books.add(book);
		author.setBooks(books);
		
		authors.add(author);
		
		author = new Author();
		
		author.setName("Ronald");
		author.setSurname("L. Rivest");
		books = new HashSet<>();
		books.add(book);
		author.setBooks(books);
		
		authors.add(author);
		
		author = new Author();
		
		author.setName("Clifford");
		author.setSurname("Stein");
		books = new HashSet<>();
		books.add(book);
		author.setBooks(books);
		
		authors.add(author);

		book.setAuthors(authors);
		
		assertTrue(bookDAO.persist(book));
		
	}

//	/**
//	 * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#update(java.lang.Object)}.
//	 */
//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.jefecomp.jpa.dao.impl.GenericDAOImpl#delete(java.lang.Object)}.
//	 */
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}

}
