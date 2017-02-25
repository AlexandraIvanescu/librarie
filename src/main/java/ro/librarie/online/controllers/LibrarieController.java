package ro.librarie.online.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ro.librarie.online.models.Book;
import ro.librarie.online.services.LibrarieService;

import java.util.List;

/**
 * Created by Alexandra Ale on 25.02.2017.
 */

@RestController
@RequestMapping("/librarie")
public class LibrarieController {

    private LibrarieService librarieService;

    @Autowired
    public LibrarieController(LibrarieService librarieService) {
        this.librarieService = librarieService;
    }

    @RequestMapping("/get/books")
    @ResponseBody
    public List<Book> getBooks(){

        return librarieService.getBooks();

    }

}
