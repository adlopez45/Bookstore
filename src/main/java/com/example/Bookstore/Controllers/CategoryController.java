package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.Category;
import com.example.Bookstore.Services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Category (categorías de libros).
 * Permite listar, crear, actualizar y eliminar categorías.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Se inyecta el servicio de Category mediante el constructor.
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * GET /api/categories
     * Lista todas las categorías.
     */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    /**
     * GET /api/categories/{id}
     * Retorna una categoría según su ID.
     */
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    /**
     * POST /api/categories
     * Crea una nueva categoría.
     */
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    /**
     * PUT /api/categories/{id}
     * Actualiza datos de una categoría existente.
     */
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Integer id, @RequestBody Category request) {
        Category existing = categoryService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setCategoryName(request.getCategoryName());
        existing.setStatus(request.getStatus());
        return categoryService.save(existing);
    }

    /**
     * DELETE /api/categories/{id}
     * Elimina una categoría por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteById(id);
    }
}
