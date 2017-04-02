package ro.librarie.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.librarie.online.models.User;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
