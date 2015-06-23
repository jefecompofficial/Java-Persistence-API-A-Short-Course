/**
 * 
 */
package org.jefecomp.jpa.entities;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author jefecomp
 *
 */
@Entity
@AttributeOverride(column=@Column(name="description_record"), name="description")
public class Book extends Record {
	
	@Basic(optional=false)
	private String title;
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY,targetEntity=Author.class,mappedBy="books")
	private Set<Author> authors;
	
	private String edition;
	
	private String isbn;
	
	private String filesystemPath;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Publisher publisher;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getFilesystemPath() {
		return filesystemPath;
	}

	public void setFilesystemPath(String filesystemPath) {
		this.filesystemPath = filesystemPath;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
}
