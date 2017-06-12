package ro.biblioteca.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.biblioteca.online.models.Borrow;

import java.util.List;

/**
 * Created by Alexandra Ale on 12/06/2017.
 */

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    @Query("SELECT b from Borrow b " +
            "WHERE b.subscriber.id = ?1")
    List<Borrow> getAllSubscriberBorrow(int subscriberId);

}
