package com.dc297.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.dc297.core.BookSearchResult;

import io.dropwizard.hibernate.AbstractDAO;

public class BookSearchDAO  extends AbstractDAO<BookSearchResult> {

	public BookSearchDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<BookSearchResult> getAll(String searchQuery){
		return (List<BookSearchResult>) currentSession()
				.createNamedQuery("SEARCH_BOOKS")
				.setParameter("searchQuery",'%' + searchQuery + '%')
				.setResultTransformer(Transformers.aliasToBean(BookSearchResult.class))
				.list();
	}

}
