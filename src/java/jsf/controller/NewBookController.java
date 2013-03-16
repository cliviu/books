/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.controller;

import jsf.table.model.Book;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.VolumeInfo;
import com.google.api.services.books.model.Volume.VolumeInfo.ImageLinks;
import com.google.api.services.books.model.Volumes;
import gbooks.BooksFinder;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import jsf.table.stateless.BookService;


@ManagedBean
@ApplicationScoped
public class NewBookController {
    
    @EJB BookService bookService;
    
    private String title  = "";
    private String isbn =  "";
    private String author = "" ;
    private String year = "";
    
    public NewBookController()
    {
        
    }
    
    public NewBookController(String title, String isbn, String author, String year) 
    {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.year = year;        
    }

    public NewBookController(Book book) 
    {
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.author = book.getAuthor();
        this.year = book.getYear(); 
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        System.out.println("Set title " + title);
        this.title = title;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        System.out.println("Set Isbn " + isbn);
        this.isbn = isbn;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }
    
    public void isbnChanged(ValueChangeEvent event) 
    {
        System.out.println("*** isbn changed " + event.getNewValue());
        try 
        {
            Volumes volumes = BooksFinder.queryGoogleBooksByIsbn((String)event.getNewValue());
            List<Volume> items = volumes.getItems();
            if(items.size() > 0) 
            {
                Volume volume =  items.get(0);
                VolumeInfo volumeInfo = volume.getVolumeInfo();     
                String infoLink = volumeInfo.getInfoLink();
                System.out.println("infoLink " + infoLink);
                ImageLinks imageLinks = volumeInfo.getImageLinks();
                System.out.println("volumelinks " + imageLinks);
                setTitle(volumeInfo.getTitle());
                setAuthor(volumeInfo.getAuthors().toString());
                setIsbn((String)event.getNewValue());
                setYear(volumeInfo.getPublishedDate());
             }
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }    
    }
    
    public void searchIsbn(ActionEvent event) 
    {
        System.out.println("*** isbn changed Action event ");
        try 
        {
            Volumes volumes = BooksFinder.queryGoogleBooksByIsbn(getIsbn());
            List<Volume> items = volumes.getItems();
            if(items.size() > 0) 
            {
                Volume volume =  items.get(0);
                VolumeInfo volumeInfo = volume.getVolumeInfo();                
                setTitle(volumeInfo.getTitle());
                setAuthor(volumeInfo.getAuthors().toString());
                //setIsbn((String)event.get);
                setYear(volumeInfo.getPublishedDate());                
                
                
                /*FacesContext facesContext = FacesContext.getCurrentInstance();
                UIViewRoot uiViewRoot = facesContext.getViewRoot();
                HtmlOutputText titleText = null;
                titleText = (HtmlOutputText) uiViewRoot.findComponent("form:tabView:form1:title");
                if(titleText != null)
                    titleText.setValue(getTitle());
                else 
                   System.out.println("Cannot find component title" );*/
                
            }
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }    
    }    
    
    public void persist()
    {
        System.out.println("New amazing Persist called");     
        bookService.createBook(getTitle(), getAuthor(), 
                               getIsbn(),getYear());
        setTitle("");
        setIsbn("");
        setAuthor("");
        setYear("");
      }
}
