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

    @Query("SELECT b from Book b " +
            "WHERE b.library.email = ?1 " +
            "AND LOWER(b.title) LIKE LOWER(CONCAT('%', ?2, '%')) " +
            "AND LOWER(b.author) LIKE LOWER(CONCAT('%', ?3, '%')) " +
            "AND b.category.id = ?4")
    List<Book> findBooksByLibraryEmailAndTitleAndAuthorAndCategoryID(String email, String title, String author, int categoryId);

    @Query("SELECT b FROM Book b " +
            "WHERE b.library.email = ?1 " +
            "AND LOWER(b.title) LIKE LOWER(CONCAT('%', ?2, '%')) " +
            "AND LOWER(b.author) LIKE LOWER(CONCAT('%', ?3, '%'))")
    List<Book> findBooksByLibraryEmailAndTitleAndAuthor(String email, String title, String author);

    @Query("SELECT b " +
            "FROM Book b JOIN FETCH b.category " +
            "WHERE b.id = ?1 " +
            "AND b.library.email = ?2")
    Book findBookByIdAndLibraryEmail(int id, String email);

}
