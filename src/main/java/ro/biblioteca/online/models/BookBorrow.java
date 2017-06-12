package ro.biblioteca.online.models;

/**
 * Created by Alexandra Ale on 13/06/2017.
 */
public class BookBorrow {

    private Book book;
    private Borrow borrow;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }
}
