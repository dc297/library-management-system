package com.dc297.core;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@NamedNativeQueries({@NamedNativeQuery(name="SEARCH_BOOK_LOAN", query="select bl.\"Id\" as \"id\", " + 
	"b.\"Title\" as \"bookName\", " + 
	" b.\"Id\" as \"bookId\", " +
	" br.\"Id\" as \"borrowerId\", " +
	"(br.\"FirstName\" || ' ' || br.\"LastName\")AS \"borrowerName\", " + 
	"bl.\"DateOut\" AS \"dateOut\", " + 
	"bl.\"DueDate\" AS \"dueDate\" " + 
	"" + 
	"FROM \"BookLoans\" bl, \"Books\" b, \"Borrowers\" br " + 
	"" + 
	"WHERE bl.\"BookId\" = b.\"Id\" " + 
	"AND bl.\"BorrowerId\" = br.\"Id\" " + 
	"AND bl.\"DateIn\" IS NULL " + 
	"AND (:bookName = '' OR b.\"Title\" ilike :bookName) " + 
	"AND (:bookId <=0 OR b.\"Id\" = :bookId) " + 
	"AND (:borrowerName = '' OR (br.\"FirstName\" ilike :borrowerName OR br.\"LastName\" ilike :borrowerName)) " + 
	"AND (:borrowerId <=0 OR br.\"Id\" = :borrowerId); " + 
"")})
public class BookLoanSearchResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "`id`", nullable = false)
    @JsonProperty
	public Integer id;
	
	public Integer bookId;
	
	public Integer borrowerId;
	
	public String bookName;
	
	public String borrowerName;
	
	public Date dueDate;
	
	public Date dateOut;

	public BookLoanSearchResult(Integer id, String bookName, String borrowerName, Date dueDate, Date outDate) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.borrowerName = borrowerName;
		this.dueDate = dueDate;
		this.dateOut = dateOut;
	}
	
	public BookLoanSearchResult() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
	
	
}
