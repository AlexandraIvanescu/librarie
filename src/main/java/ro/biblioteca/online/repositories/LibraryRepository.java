package ro.biblioteca.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.biblioteca.online.models.Library;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    Library findByEmail(String email);

}
