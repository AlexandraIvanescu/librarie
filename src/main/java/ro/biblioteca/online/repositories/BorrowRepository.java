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

}
