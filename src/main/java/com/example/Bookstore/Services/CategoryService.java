package com.example.Bookstore.Services;

import com.example.Bookstore.Models.Category;
import com.example.Bookstore.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // CREATE/UPDATE
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    // READ (todos)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // READ (uno por ID)
    public Category findById(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
