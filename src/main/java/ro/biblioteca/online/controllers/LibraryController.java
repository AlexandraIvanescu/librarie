package ro.biblioteca.online.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.biblioteca.online.config.SecurityUtils;
import ro.biblioteca.online.models.Book;
import ro.biblioteca.online.models.Category;
import ro.biblioteca.online.models.Library;
import ro.biblioteca.online.models.Subscriber;
import ro.biblioteca.online.services.BookService;
import ro.biblioteca.online.services.CategoryService;
import ro.biblioteca.online.services.LibraryService;
import ro.biblioteca.online.services.SubscriberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandra Ale on 25.02.2017.
 */

@RestController
public class LibraryController {

    private BookService bookService;
    private LibraryService libraryService;
    private CategoryService categoryService;
    private SubscriberService subscriberService;

    @Autowired
    public LibraryController(BookService bookService, LibraryService libraryService, CategoryService categoryService, SubscriberService subscriberService) {
        this.bookService = bookService;
        this.libraryService = libraryService;
        this.categoryService = categoryService;
        this.subscriberService = subscriberService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Map<String, Object> registerNewEmployeeAccount(@RequestBody Library library) {
        boolean isCreated = libraryService.registerNewLibraryAccount(library);

        Map<String, Object> model = new HashMap<>();
        model.put("isCreated", isCreated);

        return model;
    }

    @RequestMapping("/library/get/books")
    @ResponseBody
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(path = "/library/get/book/details", params = {"bookId"})
    @ResponseBody
    public Book getBooksDetails(@RequestParam(value = "bookId") int categoryId) {
        return bookService.getBookById(categoryId);
    }

    @RequestMapping(path = "/library/get/books/search", params = {"title", "author", "categoryId"})
    @ResponseBody
    public List<Book> getBooksSearch(@RequestParam(value = "title") String title, @RequestParam(value = "author") String author, @RequestParam(value = "categoryId") int categoryId) {
        return bookService.getAllBooks(title, author, categoryId);
    }

    @RequestMapping(value = "/library/get/account", method = RequestMethod.GET)
    @ResponseBody
    public Library getLibraryAccount() {
        return libraryService.findByEmail(SecurityUtils.getCurrentLogin());
    }

    @RequestMapping(value = "/library/get/category", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getCategory() {
        return categoryService.getAllCategory();
    }

    @RequestMapping(value = "/library/add/book", method = RequestMethod.POST)
    @ResponseBody
    public boolean addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/library/delete/book", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean delteBook(@RequestBody Book book) {
        return bookService.deleteBook(book);
    }

    @RequestMapping(value = "/library/add/picture", method = RequestMethod.POST)
    @ResponseBody
    public boolean addPicture(@RequestParam(value = "picture") MultipartFile picture) {
        return bookService.addPicture(picture);
    }

    @RequestMapping("/library/get/subscribers")
    @ResponseBody
    public List<Subscriber> getSubscribers() {
        return subscriberService.getAllSubscribers();
    }

    @RequestMapping(path = "/library/get/subscriber/details", params = {"subscriberId"})
    @ResponseBody
    public Subscriber getSubscribersDetails(@RequestParam(value = "subscriberId") int subscriberId) {
        return subscriberService.getSubscriberById(subscriberId);
    }

    @RequestMapping(path = "/library/get/subscribers/search", params = {"lastName", "firstName"})
    @ResponseBody
    public List<Subscriber> getSubscriberSearch(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName) {
        return subscriberService.getAllSubscribers(firstName, lastName);
    }

    @RequestMapping(value = "/library/add/subscriber", method = RequestMethod.POST)
    @ResponseBody
    public boolean addSubscriber(@RequestBody Subscriber subscriber) {
        return subscriberService.addSubscriber(subscriber);
    }

    @RequestMapping(value = "/library/add/subscriber/picture", method = RequestMethod.POST)
    @ResponseBody
    public boolean addSubscriberPicture(@RequestParam(value = "picture") MultipartFile picture) {
        return subscriberService.addPicture(picture);
    }

}
