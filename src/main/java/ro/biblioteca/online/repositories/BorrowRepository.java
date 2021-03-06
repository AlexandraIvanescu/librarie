package ro.biblioteca.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.biblioteca.online.models.Borrow;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandra Ale on 12/06/2017.
 */

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    @Query("SELECT b from Borrow b " +
            "WHERE b.subscriber.id = ?1")
    List<Borrow> getAllSubscriberBorrow(int subscriberId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Borrow b SET b.endDate =:endDate WHERE b.id =:id")
    void updateEndDate(@Param("endDate") Date endDate, @Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Borrow b SET b.isBorrowed = false WHERE b.id =:id")
    void returnBorrowed(@Param("id") Integer id);

    @Query("SELECT br FROM Borrow br " +
            "WHERE br.startDate >= ?4 " +
            "AND br.endDate <= ?5 " +
            "AND br.subscriber.id = ?6 " +
            "AND br.bookId IN ( SELECT b.id FROM Book b " +
            "                   WHERE b.library.email = ?1 " +
            "                   AND LOWER(b.title) LIKE LOWER(CONCAT(?2, '%')) " +
            "                   AND LOWER(b.author) LIKE LOWER(CONCAT(?3, '%')))")
    List<Borrow> findBorrowsByLibraryEmailAndTitleAndAuthor(String email, String title, String author, Date startDate, Date endDate, Integer subscriberId);


    @Query("SELECT b from Borrow b " +
            "WHERE b.subscriber.id = ?1 " +
            "AND b.isBorrowed = true " +
            "AND b.endDate <= CURDATE()")
    List<Borrow> getAllBorrowBooksBySubscriber(int subscriberId);

    @Query("SELECT b FROM Borrow b JOIN FETCH b.subscriber " +
            "WHERE b.bookId = ?1")
    List<Borrow> findBorrowByBookId(int bookId);


    @Query("SELECT br FROM Borrow br JOIN FETCH br.subscriber " +
            "WHERE br.startDate >= ?3 " +
            "AND br.endDate <= ?4 " +
            "AND LOWER(br.subscriber.firstName) LIKE LOWER(CONCAT(?1, '%')) " +
            "AND LOWER(br.subscriber.lastName) LIKE LOWER(CONCAT(?2, '%')) " +
            "AND br.bookId = ?5")
    List<Borrow> findBorrowsByStartDateAndEndDateAndFirstNameAndLastName(String firstName, String lastName, Date startDate, Date endDate, int bookId);

}
