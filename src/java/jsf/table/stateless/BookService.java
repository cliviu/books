package jsf.table.stateless;

import java.util.Collection;
import jsf.table.model.Book;
import jsf.table.model.User;


public interface BookService 
{
    public Book createBook(String title, String author, String isbn,String year);
    public Collection<Book> findAllBooks();
    public void deleteBook(Book book);
    public Collection<Book> findAllUserBooks(User user);
    public boolean borrowBook(Book book,User user);
    public boolean returnBook(Book book,User user);
}
