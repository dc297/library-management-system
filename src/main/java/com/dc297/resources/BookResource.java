package com.dc297.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.dc297.core.Book;
import com.dc297.core.BookSearchResult;
import com.dc297.dao.BookDAO;
import com.dc297.dao.BookSearchDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/book")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class BookResource {
	
	BookDAO bookDAO;
	BookSearchDAO bookSearchDAO;
	
	public BookResource(BookDAO bookDAO, BookSearchDAO bookSearchDAO) {
		this.bookDAO = bookDAO;
		this.bookSearchDAO = bookSearchDAO;
	}
	
	@GET
    @UnitOfWork
    public List<Book> getAll(){
        return bookDAO.getAll();
    }
	
	@GET
    @Path("/{id}")
    @UnitOfWork
    public Book get(@PathParam("id") Integer id){
        return bookDAO.findById(id);
    }
	
	@GET
    @Path("/search/{searchQuery}")
    @UnitOfWork
    public List<BookSearchResult> search(@PathParam("searchQuery") String searchQuery){
        return bookSearchDAO.getAll(searchQuery);
    }
	
	@POST
    @UnitOfWork
    public Book add(@Valid Book book) {
        Book newBook = bookDAO.insert(book);

        return newBook;
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Book update(@PathParam("id") Integer id, @Valid Book book) {
        book.setId(id);
        bookDAO.update(book);

        return book;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Integer id) {
        bookDAO.delete(bookDAO.findById(id));
    }

}
