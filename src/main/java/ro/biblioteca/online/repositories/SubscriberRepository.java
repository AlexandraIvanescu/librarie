package ro.biblioteca.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.biblioteca.online.models.Subscriber;

import java.util.List;

/**
 * Created by Alexandra Ale on 17/05/2017.
 */

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

    @Query("SELECT s from Subscriber s WHERE s.library.email = ?1")
    List<Subscriber> findSubscribersByLibraryEmail(String email);

}
