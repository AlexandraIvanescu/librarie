package ro.biblioteca.online.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.biblioteca.online.config.SecurityUtils;
import ro.biblioteca.online.models.Book;
import ro.biblioteca.online.models.Category;
import ro.biblioteca.online.models.Library;
import ro.biblioteca.online.services.BookService;
import ro.biblioteca.online.services.CategoryService;
import ro.biblioteca.online.services.LibraryService;

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

    @Autowired
    public LibraryController(BookService bookService, LibraryService libraryService, CategoryService categoryService) {
        this.bookService = bookService;
        this.libraryService = libraryService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/library/get/books")
    @ResponseBody
    public List<Book> getBooks() {
        return bookService.getAllBooks();
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

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Map<String, Object> registerNewEmployeeAccount(@RequestBody Library library) {
        boolean isCreated = libraryService.registerNewLibraryAccount(library);

        Map<String, Object> model = new HashMap<>();
        model.put("isCreated", isCreated);

        return model;
    }

}
