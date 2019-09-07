package com.dc297.core;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@NamedNativeQueries({@NamedNativeQuery(name="SEARCH_BOOKS", 
	query = "SELECT (" + 
			"		SELECT string_agg(\"Name\", ', ') " + 
			"		FROM \"Authors\" " + 
			"		WHERE \"Id\" " + 
			"		IN (" + 
			"			SELECT \"AuthorId\" " + 
			"			FROM \"BookAuthors\" " + 
			"			WHERE \"BookId\" = b.\"Id\")) AS \"authorName\", " + 
			"	b.\"Id\" AS \"id\", " + 
			"	b.\"Title\" AS \"title\", " + 
			"	b.\"Isbn\" AS \"isbn\", " + 
			"	bl.\"Id\" AS \"bookLoanId\" " + 
			"FROM \"Authors\" a, \"BookAuthors\" c, \"Books\" b " + 
			"FULL JOIN \"BookLoans\" bl" + 
			"	ON (bl.\"BookId\" = b.\"Id\" AND bl.\"DateIn\" IS NULL)" + 
			"WHERE (	a.\"Name\" ilike :searchQuery " + 
			"	OR b.\"Title\" ilike :searchQuery " + 
			"	OR b.\"Isbn\" ilike :searchQuery)" + 
			"	and a.\"Id\" = c.\"AuthorId\"" + 
			"	and b.\"Id\" = c.\"BookId\"" +
			"	GROUP BY b.\"Id\", " +
			"	bl.\"Id\"")})
public class BookSearchResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookSearchResult() { }
	
	public BookSearchResult(Integer id, String authorName, String title, String isbn, Integer bookLoanId) {
		this.id = id;
		this.authorName = authorName;
		this.title = title;
		this.isbn = isbn;
		this.bookLoanId = bookLoanId;
	}

	@Id
    @Column(name = "`Id`", nullable = false)
    @NotNull
    @JsonProperty
    private Integer id;
	
    private String authorName;

    private String title;

    private String isbn;

    private Integer bookLoanId;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getBookLoanId() {
		return bookLoanId;
	}

	public void setBookLoanId(Integer bookLoanId) {
		this.bookLoanId = bookLoanId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "{id:" + id + ", authorName:" + authorName + ", title:" + title + ", isbn:" + isbn
				+ ", bookLoanId:" + bookLoanId + "}";
	}

}
