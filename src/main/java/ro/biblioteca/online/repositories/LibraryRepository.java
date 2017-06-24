package ro.biblioteca.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.biblioteca.online.models.Library;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    Library findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Library l SET l.name =:name, l.email =:email WHERE l.id =:id")
    void updateLibrary(@Param("name") String name, @Param("email") String email, @Param("id") Integer id);

}
