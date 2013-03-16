package jsf.table.stateless;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jsf.table.model.Book;
import jsf.table.model.User;


@Startup
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookServiceBean implements BookService, Serializable {
    
    @PersistenceContext(unitName="zntBooksPU")
    protected EntityManager em;
    
    public BookServiceBean()
    {
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Book createBook(String title, String author, String isbn,String year) {
        Book book = new Book();
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setYear(year);
        em.persist(book);        
        return book;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Book> findAllBooks() {       
        Query query = em.createQuery("SELECT b FROM Book b");
        return (Collection<Book>) query.getResultList();
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteBook(Book book)
    {
        Book foundBook = em.find(Book.class, book.getId());
        System.out.println("Em found book " + foundBook);
        em.remove(foundBook);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean borrowBook(Book book, User user) {
        System.out.println("BookService " + book.getTitle() + " for user " + user.getFirstName());
        Book foundBook = em.find(Book.class, book.getId());
        if(book.getOwner() != null) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Book already borrowed by " + book.getOwner().getFirstName() + " " + book.getOwner().getLastName(), "Book already borrowed by " + book.getOwner()));  
            return false;
        }
        em.createNamedQuery("Book.borrow").setParameter("book",book).setParameter("user", user).setParameter("fromDate",new Date()).setParameter("toDate", new Date()).executeUpdate();
        
        System.out.println("Em found book " + foundBook);
        return true;
       
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean returnBook(Book book, User user) {
        System.out.println("BookService:returnBook " + book.getTitle() + " for user " + user.getFirstName());
        Book foundBook = em.find(Book.class, book.getId());
        if(book.getOwner().getPersId() != user.getPersId()) 
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Book not borrowed by " + book.getOwner().getFirstName() + " " + book.getOwner().getLastName(), "Book already borrowed by " + book.getOwner()));  
            return false;
        }
        em.createNamedQuery("Book.return").setParameter("book",book).executeUpdate();
        
        System.out.println("Book service:returnedBook " + foundBook);
        return true;
       
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Collection<Book> findAllUserBooks(User user) {
        TypedQuery<Book> allUserBooks = em.createNamedQuery("Book.selectAllUserBooks",Book.class).setParameter("user", user);
        return allUserBooks.getResultList();
    }
}
