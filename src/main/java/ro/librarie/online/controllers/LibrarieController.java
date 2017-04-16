package ro.librarie.online.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.librarie.online.models.Book;
import ro.librarie.online.models.User;
import ro.librarie.online.services.LibrarieService;
import ro.librarie.online.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandra Ale on 25.02.2017.
 */

@RestController
public class LibrarieController {

    private LibrarieService librarieService;
    private UserService userService;

    @Autowired
    public LibrarieController(LibrarieService librarieService, UserService userService) {
        this.librarieService = librarieService;
        this.userService = userService;
    }

    @RequestMapping("/librarie/get/books")
    @ResponseBody
    public List<Book> getBooks() {

        return librarieService.getBooks();

    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/user")
    public Map<String, Object> registerNewEmployeeAccount(@RequestBody User user) {
        boolean isCreated = userService.registerNewUserAccount(user);

        Map<String, Object> model = new HashMap<>();
        model.put("isCreated", isCreated);

        return model;
    }

}
