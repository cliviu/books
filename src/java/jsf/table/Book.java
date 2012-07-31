/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.table;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
        
    
    @Id
    private long id;
    
    private String title;
    private String isbn;
    private String author;
    private String year;
    
    public Book()
    {
        
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
    
}
