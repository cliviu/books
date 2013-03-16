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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jsf.table.model.Book;
import jsf.table.stateless.BookService;

/**
 *
 * @author lcarausu
 */
@Path("/book")
public class BooksResource {
    @Inject
    private BookService bookService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(@QueryParam("bookIsbn") String bookIsbn) {        
         System.out.println("Add book called " + bookIsbn);
         Book book = null;
         try {       
         Volumes volumes = BooksFinder.queryGoogleBooksByIsbn(bookIsbn);
            List<Volume> items = volumes.getItems();
            if(items.size() > 0) 
            {
                Volume volume =  items.get(0);
                Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();     
                String infoLink = volumeInfo.getInfoLink();
                System.out.println("infoLink " + infoLink);
                Volume.VolumeInfo.ImageLinks imageLinks = volumeInfo.getImageLinks();
                System.out.println("volumelinks " + imageLinks);
                book = new Book(volumeInfo.getTitle(),bookIsbn,volumeInfo.getAuthors().toString(), volumeInfo.getPublishedDate());       
                bookService.createBook(book.getTitle(), book.getIsbn(), book.getAuthor(), book.getYear());
             }        
         } catch (Exception e) {
             e.printStackTrace();
             return Response.status(Response.Status.BAD_REQUEST).build();
         }   
         return Response.ok(book).build();
    }
}
