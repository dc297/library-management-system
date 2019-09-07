package com.dc297.resources;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.dc297.core.BookLoan;
import com.dc297.core.BookLoanSearchResult;
import com.dc297.core.ResultResponse;
import com.dc297.dao.BookLoanDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/bookloan")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class BookLoanResource {

	BookLoanDAO bookLoanDAO;
	
	public BookLoanResource(BookLoanDAO bookLoanDAO) {
		this.bookLoanDAO = bookLoanDAO;
	}
	
//	@GET
//    @UnitOfWork
//    public List<BookLoan> getAll(){
//        return bookLoanDAO.getAll();
//    }
	
	@GET
    @Path("/{id}")
    @UnitOfWork
    public BookLoan get(@PathParam("id") Integer id){
        return bookLoanDAO.findById(id);
    }
	
	@GET
    @Path("/search/")
    @UnitOfWork
    public List<BookLoanSearchResult> search(@QueryParam("bookId") Integer bookId, @QueryParam("bookName") String bookName, @QueryParam("borrowerId") Integer borrowerId, @QueryParam("borrowerName") String borrowerName){
        return bookLoanDAO.search(bookId, borrowerId, borrowerName, bookName);
    }
	
	@POST
    @UnitOfWork
    public BookLoan add(@Valid BookLoan bookLoan) {
		
		ResultResponse result = bookLoanDAO.isInsertValid(bookLoan);
		if(!result.isSuccess) throw new WebApplicationException(result.message, 400);
		
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        bookLoan.setDateOut(new java.sql.Date(c.getTimeInMillis()));
        c.add(Calendar.DATE, 14);
        bookLoan.setDueDate(new java.sql.Date(c.getTimeInMillis()));
        BookLoan newBookLoan = bookLoanDAO.insert(bookLoan);
        return newBookLoan;
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public BookLoan update(@PathParam("id") Integer id, @Valid BookLoan bookLoan) {
    	Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    	bookLoan.setDateIn(new java.sql.Date(c.getTimeInMillis()));
        bookLoan.setId(id);
        bookLoanDAO.update(bookLoan);

        return bookLoan;
    }
}
