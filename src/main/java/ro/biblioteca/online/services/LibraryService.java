package ro.biblioteca.online.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.biblioteca.online.repositories.LibraryRepository;
import ro.biblioteca.online.models.Library;

/**
 * Created by Alexandra Ale on 16.04.2017.
 */

@Service
public class LibraryService {

    private LibraryRepository libraryRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public LibraryService(LibraryRepository libraryRepository, PasswordEncoder passwordEncoder) {
        this.libraryRepository = libraryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerNewLibraryAccount(Library library) {

        try {
            library.setPassword(passwordEncoder.encode(library.getPassword()));

            libraryRepository.saveAndFlush(library);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Library findByEmail(String email) {
        Library library = libraryRepository.findByEmail(email);
        library.setBooks(null);

        return library;
    }

}
