/**
 * 
 */
package org.jefecomp.jpa.entities;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import org.jefecomp.jpa.entities.Book;

/**
 * @author jefecomp
 *
 */
@Entity
public class Author extends Person {
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Book_Author")
	@OrderBy("title DESC")
	private Set<Book> books;

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
