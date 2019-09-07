package com.dc297.core;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="`BookLoans`")
public class BookLoan {
	@Id
    @Column(name = "`Id`", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;
	
	@Column(name = "`BookId`", nullable = false)
    @NotNull
    @JsonProperty
    private Integer bookId;
	
	@Column(name = "`BorrowerId`", nullable = false)
    @NotNull
    @JsonProperty
    private Integer borrowerId;
	
	@Column(name = "`DateOut`", nullable = false)
    @JsonProperty
    private Date dateOut;
	
	@Column(name = "`DateIn`", nullable = false)
    @JsonProperty
    private Date dateIn;
	
	@Column(name = "`DueDate`", nullable = false)
    @JsonProperty
    private Date dueDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
}
