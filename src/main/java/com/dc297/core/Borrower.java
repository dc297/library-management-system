package com.dc297.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="`Borrowers`")
public class Borrower {

	@Id
    @Column(name = "`Id`")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

	@Column(name = "`FirstName`", length = 150, nullable = false)
    @NotNull
    @JsonProperty
    private String firstName;
	
	@Column(name = "`LastName`", length = 150, nullable = false)
    @NotNull
    @JsonProperty
    private String lastName;
	
	@Column(name = "`Ssn`", length = 11, nullable = false, unique = true)
    @NotNull
    @JsonProperty
    private String ssn;
	
	@Column(name = "`StreetAddress`", length = 300, nullable = false)
    @NotNull
    @JsonProperty
    private String streetAddress;
	
	@Column(name = "`City`", length = 20, nullable = false)
    @NotNull
    @JsonProperty
    private String city;
	
	@Column(name = "`State`", length = 2, nullable = false)
    @NotNull
    @JsonProperty
    private String state;
	
	@Column(name = "`Phone`", length = 15, nullable = false)
    @JsonProperty
    private String phone;
	
	@Column(name = "`Email`", length = 300, nullable = false)
    @JsonProperty
    private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
