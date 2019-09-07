package com.dc297.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.dc297.core.FineSearchResult;

import io.dropwizard.hibernate.AbstractDAO;

public class FineDAO extends AbstractDAO<FineSearchResult> {

	public FineDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<FineSearchResult> getAll(){
		return currentSession()
				.createNamedQuery("SEARCH_FINES")
				.setResultTransformer(Transformers.aliasToBean(FineSearchResult.class))
				.getResultList();
	}
	
	public void calculateFines() {
		currentSession()
		 .createNamedQuery("CALCULATE_FINES")
		 .list();
	}
	
	public void payFine(Integer borrowerId) {
		currentSession()
		 .createNamedQuery("PAY_FINES")
		 .setParameter("borrowerId", borrowerId)
		 .executeUpdate();
	}

}
