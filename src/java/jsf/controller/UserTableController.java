/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.controller;

import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import jsf.table.model.Book;
import jsf.table.model.User;
import jsf.table.stateless.BookService;
import jsf.table.stateless.UserService;

/**
 *
 * @author lcarausu
 */
@ManagedBean
@ApplicationScoped
public class UserTableController 
{
    @EJB UserService userService;

    @EJB BookService bookService;
    
    private Collection<User> users;
    
    private User selectedUser;
    
    public Book selectedBook;
    
    public Book selectedNewBook;
    
    private User[] selectedUsers;
    
    private Collection<Book> allBooks;
    public Collection<Book> userBooks;
    
    public Collection<Book> getAllBooks()
    {
        allBooks = bookService.findAllBooks();
        return allBooks;
    }

    
    
    public UserTableController()
    {                     
      
    }
       
    public Collection<User> getUsers()
    {
        users = userService.findAllUsers();
        return users;
    }

    /**
     * @return the selectedBook
     */
    public User getSelectedUser() {
        return selectedUser;
    }

    /**
     * @param selectedUser the selectedUser to set
     */
    public void setSelectedUser(User selectedUser) {
        System.out.println("SetSelectedUser " + selectedUser);
        if(selectedUser != null) 
        {
            System.out.println("SetSelectedUser " + selectedUser.getLastName());
            this.selectedUser = selectedUser;
        }    
    }

    /**
     * @return the selectedBooks
     */
    public User[] getSelectedUsers() {
        return selectedUsers;
    }
    
    public void addUser(User user)     
    {   
        users.add(user);
        userService.addUser(user);
    }
    
    public void remove()
    {
        if(selectedUser != null) 
        {
            System.out.println("Remove selected user " + selectedUser);
            userService.deleteUser(selectedUser);
            users.remove(selectedUser);
        } 
        else // show dialog no user selected
        {
            addError("No user selected");
        }    
    }
    
    public void refreshUsers()
    {
        users = userService.findAllUsers();
    }
    
    public void addError(String errorDetail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"error message", errorDetail));  
    }  
    
    public String viewAccount()
    {
        if(selectedUser != null) 
        {
            System.out.println("SelectedUser " + selectedUser.getLastName());
            setUserBooks(bookService.findAllUserBooks(selectedUser));
            System.out.println("AllUserBooks " + getUserBooks().size());
        } 
        //it will load the page account.xhtml
        return "account";
    }

    /**
     * @return the userBooks
     */
    public Collection<Book> getUserBooks() {
        if(selectedUser != null) 
        {
            setUserBooks(bookService.findAllUserBooks(selectedUser));
        }    
        return userBooks;
    }

    /**
     * @param userBooks the userBooks to set
     */
    public void setUserBooks(Collection<Book> userBooks) {
        this.userBooks = userBooks;
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
    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }
    
    /**
     * @return the selectedBook
     */
    public Book getSelectedNewBook() {
        return selectedNewBook;
    }

    /**
     * @param selectedBook the selectedBook to set
     */
    public void setSelectedNewBook(Book selectedBook) {
        this.selectedNewBook = selectedBook;
    }
    
    public void borrow()
    {
        System.out.println("Borrow selected book " + selectedBook);
        if(selectedBook != null) 
        {
            System.out.println("Borrow selected book " + selectedBook.getTitle());
            bookService.borrowBook(selectedBook,getSelectedUser());
            //books.remove(selectedBook);
        } 
        else 
        {
            addError("No user selected");
        }    
    }
    
    public void returnBook()
    {
        System.out.println("Return selected book " + selectedNewBook);
        if(selectedNewBook != null) 
        {
            System.out.println("Return selected book " + selectedNewBook.getTitle());
            bookService.returnBook(selectedNewBook,getSelectedUser());
        } 
        else 
        {
            addError("No book selected");
        }    
    }
    
    public void deleteUser(){
        System.out.println("Delete user called" );
    }
    
    public String home()
    {
        //it will load the page account.xhtml
        return "books";
    }
}
