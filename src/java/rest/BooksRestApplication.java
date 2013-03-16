/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import gbooks.BooksFinder;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import jsf.table.model.Book;
import jsf.table.stateless.BookService;


/**
 *
 * @author lcarausu
 */
@ApplicationPath("/rest")
public class BooksRestApplication extends Application{
    
    
}
