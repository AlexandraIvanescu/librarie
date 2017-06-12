package ro.biblioteca.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.biblioteca.online.models.Borrow;

/**
 * Created by Alexandra Ale on 12/06/2017.
 */

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
