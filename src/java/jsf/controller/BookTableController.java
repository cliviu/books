/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.controller;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import jsf.table.model.Book;
import jsf.table.stateless.BookService;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author lcarausu
 */
@ManagedBean
@ApplicationScoped
//@DependsOn("BookService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookTableController 
{
    @EJB BookService bookService;
    
    private Collection<Book> books;
    
    private Book selectedBook;
    
    private Book[] selectedBooks;
    
    public BookTableController()
    {                      
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Book> getBooks()
    {
        books = bookService.findAllBooks();
        return books;
    }

    /**
     * @return the selectedBook
     */
    public Book getSelectedBook() {
        return selectedBook;
    }

    /**
     * @param selectedBook the selectedBook to set
     */
    public void setSelectedBook(Book selectedBook) 
    {
       
        if(selectedBook != null) 
        {
            System.out.println("SetSelectedBook " + selectedBook.getTitle());
            this.selectedBook = selectedBook;
        } else System.out.println("SetSelectedBook " + selectedBook);    
    }

    /**
     * @return the selectedBooks
     */
    public Book[] getSelectedBooks() {
        return selectedBooks;
    }
    
    public void addBook(Book book)     
    {   
        books.add(book);
        bookService.createBook(book.getTitle(), book.getAuthor(), 
                               book.getIsbn(), book.getYear());
    }
    
    public void remove()
    {
        if(selectedBook != null) 
        {
            System.out.println("Remove selected book " + selectedBook);
            bookService.deleteBook(selectedBook);
            books.remove(selectedBook);
        } 
        else 
        {
            addError("No user selected");
        }    
    }
    
     public void borrow()
    {
        if(selectedBook != null) 
        {
            System.out.println("Borrow selected book " + selectedBook);
            //bookService.borrowBook(selectedBook,null);
            //books.remove(selectedBook);
        } 
        else 
        {
            addError("No user selected");
        }    
    }
    
    public void refreshBooks()
    {
        books = bookService.findAllBooks();
    }
            
    public void addError(String errorDetail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"error message", errorDetail));  
    }  
    
    public void onRowSelect(SelectEvent event) {
        System.out.println("OnRowSelect" + event.getObject());
        setSelectedBook((Book)event.getObject());
    }

}
