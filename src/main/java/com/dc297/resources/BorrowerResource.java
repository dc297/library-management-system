package com.dc297.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import com.dc297.core.Borrower;
import com.dc297.dao.BorrowerDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/borrower")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class BorrowerResource {

	BorrowerDAO borrowerDAO;
	
	public BorrowerResource(BorrowerDAO borrowerDAO) {
		this.borrowerDAO = borrowerDAO;
	}
	
	@GET
    @UnitOfWork
    public List<Borrower> getAll(){
        return borrowerDAO.getAll();
    }
	
	@GET
    @Path("/{id}")
    @UnitOfWork
    public Borrower get(@PathParam("id") Integer id){
        return borrowerDAO.findById(id);
    }
	
	@GET
	@Path("/search/{borrowerName}")
	@UnitOfWork
	public List<Borrower> search(@PathParam("borrowerName") String borrowerName){
		return borrowerDAO.search(borrowerName);
	}
	
	@POST
    @UnitOfWork
    public Borrower add(@Valid Borrower borrower) {
        try{
        	Borrower newBorrower = borrowerDAO.insert(borrower);
        	return newBorrower;
        }
        catch(HibernateException e) {
        	if(e instanceof ConstraintViolationException)
        		throw new WebApplicationException("Another borrower with the same SSN exists", 400);
        	
        	throw e;
        }
    }
}
