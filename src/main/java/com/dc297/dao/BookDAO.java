package com.dc297.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.dc297.core.Book;

import io.dropwizard.hibernate.AbstractDAO;

public class BookDAO extends AbstractDAO<Book> {

	public BookDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<Book> getAll(){
		return (List<Book>) currentSession().createCriteria(Book.class).list();
	}
	
	public Book findById(int id) {
		return (Book) currentSession().get(Book.class, id);
	}
	
	public void delete(Book book) {
        currentSession().delete(book);
    }

    public void update(Book book) {
        currentSession().saveOrUpdate(book);
    }

    public Book insert(Book	book) {
        return persist(book);
    }

}
