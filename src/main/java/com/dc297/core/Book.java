package com.dc297.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="`Books`")
public class Book{

    @Id
    @Column(name = "`Id`", nullable = false)
    @NotNull
    @JsonProperty
    private Integer id;

	@Column(name = "`Title`", length = 100, nullable = false)
    @NotNull
    @JsonProperty
    private String title;

    @Column(name = "`Isbn`", length = 100, nullable = false)
    @NotNull
    @JsonProperty
    private String isbn;

    @Column(name = "`Cover`", length = 100, nullable = true)
    @NotNull
    @JsonProperty
    private String cover;

    @Column(name = "`Publisher`", length = 100, nullable = false)
    @NotNull
    @JsonProperty
    private String publisher;

    @Column(name = "`Isbn13`", length = 100, nullable = false)
    @NotNull
    @JsonProperty
    private String isbn13;

    @Column(name = "`Pages`", length = 100, nullable = false)
    @NotNull
    @JsonProperty
    private Integer pages;
    
    
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}
}