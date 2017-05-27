package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.biblioteca.online.config.SecurityUtils;
import ro.biblioteca.online.models.Book;
import ro.biblioteca.online.models.Category;
import ro.biblioteca.online.models.Library;
import ro.biblioteca.online.repositories.BookRepository;
import ro.biblioteca.online.repositories.LibraryRepository;

import java.io.File;
import java.io.IOException;
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

    private BookRepository bookRepository;
    private LibraryRepository libraryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findBooksByLibraryEmail(SecurityUtils.getCurrentLogin());

        books.forEach(book -> {
            book.setLibrary(null);
            book.setCategory(null);
        });

        return books;
    }


    public List<Book> getAllBooks(String title, String author, int categoryId) {
        List<Book> books;

        if (categoryId != 0) {
            books = bookRepository.findBooksByLibraryEmailAndTitleAndAuthorAndCategoryID(SecurityUtils.getCurrentLogin(), title, author, categoryId);
        } else {
            books = bookRepository.findBooksByLibraryEmailAndTitleAndAuthor(SecurityUtils.getCurrentLogin(), title, author);
        }

        books.forEach(book -> {
            book.setLibrary(null);
            book.setCategory(null);
        });

        return books;
    }

    public boolean addBook(Book book) {
        Library library = libraryRepository.findByEmail(SecurityUtils.getCurrentLogin());

        book.setLibrary(library);

        bookRepository.saveAndFlush(book);

        return true;
    }

    public boolean addPicture(MultipartFile picture) {
        String rootDirectory = System.getProperty("user.dir") + "/src/main/webapp/images/books/";
        File newFile = new File(rootDirectory + picture.getOriginalFilename());

        try {
            picture.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }


    public Book getBookById(int id) {
        Library library = libraryRepository.findByEmail(SecurityUtils.getCurrentLogin());

        Book book = bookRepository.findBookByIdAndLibraryEmail(id, library.getEmail());

        book.setLibrary(null);
        book.setCategory(null);

        return book;
    }


}
