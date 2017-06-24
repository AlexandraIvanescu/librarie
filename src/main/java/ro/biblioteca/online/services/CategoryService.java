package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.biblioteca.online.models.Category;
import ro.biblioteca.online.repositories.CategoryRepository;

import java.util.List;

/**
 * Created by Alexandra Ale on 01/05/2017.
 */

@Service
public class CategoryService {

    private CategoryRepository repository;


    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategory() {

        List<Category> categories = repository.findAll();

        categories.forEach(category -> category.setBooks(null));

        return categories;

    }

    public boolean saveCategory(Category category) {
        repository.saveAndFlush(category);

        return true;
    }

}
