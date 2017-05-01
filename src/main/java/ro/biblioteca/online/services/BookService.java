package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.biblioteca.online.config.SecurityUtils;
import ro.biblioteca.online.models.Book;
import ro.biblioteca.online.models.Category;
import ro.biblioteca.online.repositories.BookRepository;

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

    private  BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findBooksByLibraryEmail(SecurityUtils.getCurrentLogin());

        books.forEach(book -> {
            book.setLibrary(null);
            book.setCategory(null);
        });

        return books;
    }

}
