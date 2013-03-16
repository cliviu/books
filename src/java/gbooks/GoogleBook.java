/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gbooks;

/**
 *
 * @author lcarausu
 */
public class GoogleBook {

    private String title;
    private String isbn;
    private String author;
    private String year;

    public GoogleBook() {
    }

    public GoogleBook(String title, String isbn, String author, String year) {
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
}
