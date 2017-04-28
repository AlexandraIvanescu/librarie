package ro.biblioteca.online.services;

import org.springframework.stereotype.Service;
import ro.biblioteca.online.models.Book;
import ro.biblioteca.online.models.Category;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra Ale on 25.02.2017.
 */
@Service
public class BookService {

    public List<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {

            Book book1 = new Book("Adultery", "Paulo Coehlo", formatter.parse("04/10/2014"), 272, Category.ROMANCE);
            Book book2 = new Book("The Jungle Book", "Rudyard Kipling", formatter.parse("05/22/1894"), 250, Category.CHILDRENS);
            Book book3 = new Book("The Girl on the Train", "Paula Hawkins", formatter.parse("01/13/2015"), 395, Category.MYSTERY);

            books.add(book1);
            books.add(book2);
            books.add(book3);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return books;

    }

}
