package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Métodos adicionales si lo requieres (búsqueda por nombre, etc.)
    // List<Category> findByCategoryNameContainingIgnoreCase(String name);
}