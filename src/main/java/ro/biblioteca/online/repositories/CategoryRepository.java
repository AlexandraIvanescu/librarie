package ro.biblioteca.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.biblioteca.online.models.Category;

/**
 * Created by daniellungu on 01/05/2017.
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
