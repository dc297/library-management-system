package com.dc297;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.dc297.core.Book;
import com.dc297.core.BookLoan;
import com.dc297.core.BookLoanSearchResult;
import com.dc297.core.BookSearchResult;
import com.dc297.core.Borrower;
import com.dc297.core.FineSearchResult;
import com.dc297.dao.BookDAO;
import com.dc297.dao.BookLoanDAO;
import com.dc297.dao.BookSearchDAO;
import com.dc297.dao.BorrowerDAO;
import com.dc297.dao.FineDAO;
import com.dc297.resources.BookLoanResource;
import com.dc297.resources.BookResource;
import com.dc297.resources.BorrowerResource;
import com.dc297.resources.FineResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class LibraryManagementSystemApplication extends Application<LibraryManagementSystemConfiguration> {

    public static void main(final String[] args) throws Exception {
        new LibraryManagementSystemApplication().run(args);
    }

    private final HibernateBundle<LibraryManagementSystemConfiguration> hibernate = 
    		new HibernateBundle<LibraryManagementSystemConfiguration>(
    				Book.class, 
    				BookSearchResult.class, 
    				Borrower.class,
    				BookLoan.class,
    				BookLoanSearchResult.class,
    				FineSearchResult.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(LibraryManagementSystemConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
    
    
    @Override
    public String getName() {
        return "LibraryManagementSystem";
    }

    @Override
    public void initialize(final Bootstrap<LibraryManagementSystemConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final LibraryManagementSystemConfiguration configuration,
                    final Environment environment) {
        final BookDAO bookDAO = new BookDAO(hibernate.getSessionFactory());
        final BookSearchDAO bookSearchDAO = new BookSearchDAO(hibernate.getSessionFactory());
        final BorrowerDAO borrowerDAO = new BorrowerDAO(hibernate.getSessionFactory());
        final BookLoanDAO bookLoanDAO = new BookLoanDAO(hibernate.getSessionFactory());
        final FineDAO fineDAO = new FineDAO(hibernate.getSessionFactory());
        
        final BookResource bookResource = new BookResource(bookDAO, bookSearchDAO);
        final BorrowerResource borrowerResource = new BorrowerResource(borrowerDAO);
        final BookLoanResource bookLoanResource = new BookLoanResource(bookLoanDAO);
        final FineResource fineResource = new FineResource(fineDAO);
        
        environment.jersey().register(bookResource);
        environment.jersey().register(borrowerResource);
        environment.jersey().register(bookLoanResource);
        environment.jersey().register(fineResource);
        
        configureCors(environment);
    }
    
    private void configureCors(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }

}
