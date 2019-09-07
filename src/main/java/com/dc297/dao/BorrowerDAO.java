package com.dc297.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.dc297.core.Borrower;

import io.dropwizard.hibernate.AbstractDAO;

public class BorrowerDAO extends AbstractDAO<Borrower>  {

	public BorrowerDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<Borrower> getAll(){
		return (List<Borrower>) currentSession().createCriteria(Borrower.class).list();
	}
	
	public List<Borrower> search(String filterQuery){
		int borrowerId = 0;
		try {
			borrowerId = Integer.parseInt(filterQuery);
		}
		catch(NumberFormatException e) {}
		return (List<Borrower>) currentSession()
				.createCriteria(Borrower.class)
				.add(Restrictions.or(
						Restrictions.ilike("firstName", "%" + filterQuery + "%"), 
						Restrictions.ilike("lastName", "%" + filterQuery + "%"),
						Restrictions.eq("id",borrowerId)
						)
					)
				.list();
	}
	
	public Borrower findById(int id) {
		return (Borrower) currentSession().get(Borrower.class, id);
	}

    public Borrower insert(Borrower	borrower) {
        return persist(borrower);
    }

}
