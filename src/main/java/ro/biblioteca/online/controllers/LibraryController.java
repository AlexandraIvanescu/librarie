package ro.biblioteca.online.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.biblioteca.online.config.SecurityUtils;
import ro.biblioteca.online.models.*;
import ro.biblioteca.online.services.*;

import java.util.Date;
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
    private BorrowService borrowService;

    @Autowired
    public LibraryController(BookService bookService, LibraryService libraryService,
                             CategoryService categoryService, SubscriberService subscriberService,
                             BorrowService borrowService) {

        this.bookService = bookService;
        this.libraryService = libraryService;
        this.categoryService = categoryService;
        this.subscriberService = subscriberService;
        this.borrowService = borrowService;
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

    @RequestMapping("/library/get/books/not-borrowed")
    @ResponseBody
    public List<Book> getAllBooksNotBorrowed() {
        return bookService.getAllBooksNotBorrowed();
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

    @RequestMapping(value = "/library/delete/subscriber", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean delteSubscriber(@RequestBody Subscriber subscriber) {
        return subscriberService.deleteSubscriber(subscriber);
    }

    @RequestMapping(value = "/library/add/borrow", method = RequestMethod.POST)
    @ResponseBody
    public boolean addBorrow(@RequestBody Borrow borrow) {
        return borrowService.addBorrow(borrow);
    }

    @RequestMapping(value = "/library/get/borrow", params = {"subscriberId"})
    @ResponseBody
    public List<BookBorrow> getBorrow(@RequestParam(value = "subscriberId") int subscriberId) {
        return borrowService.getAllSubscriberBorrow(subscriberId);
    }

    @RequestMapping(value = "/library/extended/borrow", method = RequestMethod.POST)
    @ResponseBody
    public boolean extendedBorrow(@RequestBody Borrow borrow) {
        return borrowService.extendedBorrow(borrow);
    }

    @RequestMapping(value = "/library/delete/borrow", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteBorrow(@RequestBody Borrow borrow) {
        return borrowService.deleteBorrow(borrow);
    }


    @RequestMapping(path = "/library/search/borrow", params = {"title", "author", "startDate", "endDate"})
    @ResponseBody
    public List<BookBorrow> getBorrowSearch(@RequestParam(value = "title") String title, @RequestParam(value = "author") String author,
                                            @RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate) {

        return borrowService.searchBorrow(title, author, startDate, endDate);
    }

    @RequestMapping(path = "/library/has/borrow", params = {"subscriberId"})
    @ResponseBody
    public boolean hasBorrow(@RequestParam(value = "subscriberId") int subscriberId) {
        return borrowService.hasBorrow(subscriberId);
    }

    @RequestMapping(value = "/library/get/book/borrow", params = {"bookId"})
    @ResponseBody
    public List<Borrow> getBookBorrow(@RequestParam(value = "bookId") int bookId) {
        return borrowService.getBookBorrow(bookId);
    }

    @RequestMapping(path = "/library/search/book/borrow", params = {"firstName", "lastName", "startDate", "endDate", "bookId"})
    @ResponseBody
    public List<Borrow> getBookBorrowSearch(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
                                            @RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate,
                                            @RequestParam(value = "bookId") int bookId) {

        return borrowService.searchBookBorrow(firstName, lastName, startDate, endDate, bookId);
    }

}
