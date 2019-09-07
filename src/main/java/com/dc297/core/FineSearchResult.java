package com.dc297.core;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@NamedNativeQueries({@NamedNativeQuery(
	name="CALCULATE_FINES",
	query= "SELECT count(*) from calculate_fines();"
), @NamedNativeQuery(
	name="SEARCH_FINES",
	query="SELECT br.\"Id\" AS \"borrowerId\", " + 
			"	SUM(CASE WHEN f.\"Paid\" = true THEN f.\"FineAmount\" ELSE 0 END) AS \"paidAmount\"," + 
			"	SUM(CASE WHEN (f.\"Paid\" = false AND bl.\"DateIn\" IS NOT NULL) THEN f.\"FineAmount\" ELSE 0 END) AS \"unpaidAmount\"," + 
			"	SUM(CASE WHEN (f.\"Paid\" = false AND bl.\"DateIn\" IS NULL) THEN f.\"FineAmount\" ELSE 0 END) AS \"estimatedAmount\"," + 
			"	br.\"FirstName\" || ' ' || br.\"LastName\" AS \"borrowerName\" " + 
			"FROM \"Fines\" f " + 
			"JOIN \"BookLoans\" bl " + 
			"ON f.\"LoanId\" = bl.\"Id\" " + 
			"JOIN \"Borrowers\" br " + 
			"ON bl.\"BorrowerId\" = br.\"Id\" " + 
			"GROUP BY br.\"Id\";"
), @NamedNativeQuery(
		name="PAY_FINES",
		query="UPDATE \"Fines\" " + 
				"SET \"Paid\" = true " + 
				"WHERE \"LoanId\" in " + 
				"(" + 
				"	SELECT bl.\"Id\" " + 
				" 	FROM \"BookLoans\" bl " + 
				" 	WHERE bl.\"BorrowerId\" = :borrowerId " + 
				"	AND bl.\"DateIn\" IS NOT NULL " + 
				") " + 
				"AND \"Paid\" = false;"
)
}
)
public class FineSearchResult {

	@Id
    @Column(name = "`borrowerId`", nullable = false)
    @NotNull
    @JsonProperty
	public Integer borrowerId;
	
	public String borrowerName;
	
	public BigDecimal paidAmount;
	
	public BigDecimal unpaidAmount;
	
	public BigDecimal estimatedAmount;

	public Integer getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getUnpaidAmount() {
		return unpaidAmount;
	}

	public void setUnpaidAmount(BigDecimal unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}

	public BigDecimal getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(BigDecimal estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}
	
	
	
}
