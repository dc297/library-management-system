package com.dc297.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.dc297.core.BookLoan;
import com.dc297.core.BookLoanSearchResult;
import com.dc297.core.ResultResponse;

import io.dropwizard.hibernate.AbstractDAO;

public class BookLoanDAO extends AbstractDAO<BookLoan> {

	public BookLoanDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public BookLoan findById(int id) {
		return (BookLoan) currentSession().get(BookLoan.class, id);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<BookLoanSearchResult> search(Integer bookId, Integer borrowerId, String borrowerName, String bookName){
		return (List<BookLoanSearchResult>) (currentSession()
				.createNamedQuery("SEARCH_BOOK_LOAN")
				.setParameter("bookId", bookId!=null?bookId:0)
				.setParameter("borrowerId", borrowerId!=null?borrowerId:0)
				.setParameter("borrowerName", borrowerName!=null?'%' + borrowerName + '%':"")
				.setParameter("bookName", bookName!=null?'%' + bookName + '%':"")
				.setResultTransformer(Transformers.aliasToBean(BookLoanSearchResult.class))
				.list());
	}
	
	public ResultResponse isInsertValid(BookLoan bookLoan) {
		ResultResponse rr = new ResultResponse();
		rr.isSuccess = true;
		List<BookLoanSearchResult> borrowersBooks = search(null, bookLoan.getBorrowerId(), null, null);
		if(borrowersBooks.size()>=3) {
			rr.isSuccess = false;
			rr.message = "Borrower has reached the allowed limit of 3 books";
			return rr;
		}
		
		List<BookLoanSearchResult> bookLoans = search(bookLoan.getBookId(), null, null, null);
		
		if(bookLoans.size()>0) {
			rr.isSuccess = false;
			rr.message = "Book already checked out";
			return rr;
		}
		
		return rr;
	}

    public void update(BookLoan bookLoan) {
        currentSession().saveOrUpdate(bookLoan);
    }

    public BookLoan insert(BookLoan	bookLoan) {
        return persist(bookLoan);
    }

}
