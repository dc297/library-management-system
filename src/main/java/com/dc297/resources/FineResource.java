package com.dc297.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dc297.core.FineSearchResult;
import com.dc297.dao.FineDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/fine")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class FineResource {

	FineDAO fineDAO;
	
	public FineResource(FineDAO fineDAO) {
		this.fineDAO = fineDAO;
	}
	
	@POST()
    @UnitOfWork
    public void calculate() {
        this.fineDAO.calculateFines();
        
    }
	
	@GET()
	@Path("/list")
	@UnitOfWork
	public List<FineSearchResult> getAll(){
		return this.fineDAO.getAll();
	}
	
	@PUT
	@UnitOfWork
	@Path("/{borrowerId}")
	public void payFine(@PathParam("borrowerId") Integer borrowerId) {
		this.fineDAO.payFine(borrowerId);
	}
}
