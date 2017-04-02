package ro.librarie.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.librarie.online.models.Admin;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Integer> {

    Admin findByEmail(String email);

}
