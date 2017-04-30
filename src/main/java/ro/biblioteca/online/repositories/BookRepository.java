package ro.biblioteca.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.biblioteca.online.models.Book;

import java.util.List;

/**
 * Created by Alexandra Ale on 01/05/2017.
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b from Book b WHERE b.library.email = ?1")
    List<Book> findBooksByLibraryEmail(String email);

}
