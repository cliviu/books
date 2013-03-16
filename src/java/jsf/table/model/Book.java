/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.table.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import jsf.controller.NewBookController;



@Entity
//@NamedQuery(name="Book.selectAllUserBooks", query = "select b from Book b  where b.owner=:user")
@NamedQueries({
   @NamedQuery(name="Book.selectAllUserBooks", query = "select b from Book b  where b.owner=:user"),
   @NamedQuery(name="Book.borrow", query = "update Book b set b.owner=:user, b.fromdate=:fromDate,  b.todate=:toDate where b=:book"),
   @NamedQuery(name="Book.return", query = "update Book b set b.owner=NULL where b=:book")
})
public class Book {
        
    @TableGenerator(name="BooksGen")   
    @Id @GeneratedValue(generator="BooksGen")
    private long id;
    
    private String title;
    private String isbn;
    private String author;
    private String year;
    
    @Temporal(TemporalType.DATE)
    private Date   fromdate;
    
    @Temporal(TemporalType.DATE)
    private Date   todate;
    
    
    @ManyToOne
    public User owner;
    
    public Book()
    {
        
    }

    public Book(NewBookController formBook) 
    {
        this.title = formBook.getTitle();
        this.isbn = formBook.getIsbn();
        this.author = formBook.getAuthor();
        this.year = formBook.getYear();        
    }
    
    public Book(String title, String isbn, String author, String year) 
    {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.year = year;        
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
    
    public long getId()
    {
        return id;
    }

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * @return the fromdate
     */
    public Date getFromdate() {
        return fromdate;
    }

    /**
     * @param fromdate the fromdate to set
     */
    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    /**
     * @return the todate
     */
    public Date getTodate() {
        return todate;
    }

    /**
     * @param todate the todate to set
     */
    public void setTodate(Date todate) {
        this.todate = todate;
    }
    
    public String getFormattedFromDate()
    {
        if(fromdate != null) 
        {
            Format format = new SimpleDateFormat("dd-MM-yyyy");
            return format.format(fromdate).toString();
        } 
        return "";
    }
}
