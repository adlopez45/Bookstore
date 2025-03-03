package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.Book;
import com.example.Bookstore.Services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Book (libros).
 * Provee endpoints CRUD: listar, obtener por id, crear, actualizar y eliminar libros.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    /**
     * Se inyecta el servicio de Book mediante el constructor.
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * GET /api/books
     * Lista todos los libros.
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    /**
     * GET /api/books/{id}
     * Retorna un libro según su ID.
     */
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.findById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }

    @GetMapping("/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    /**
     * POST /api/books
     * Crea un nuevo libro.
     */
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    /**
     * PUT /api/books/{id}
     * Actualiza datos de un libro existente.
     */
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book request) {
        Book existing = bookService.findById(id);
        if (existing == null) {
            // Maneja libro no encontrado
            return null;
        }
        existing.setTitle(request.getTitle());
        existing.setIsbn(request.getIsbn());
        existing.setPrice(request.getPrice());
        existing.setImageUrl(request.getImageUrl());
        existing.setStock(request.getStock());
        existing.setCategory(request.getCategory());
        existing.setPublicationDate(request.getPublicationDate());
        existing.setStatus(request.getStatus());
        return bookService.save(existing);
    }

    /**
     * DELETE /api/books/{id}
     * Elimina un libro por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteById(id);
    }
}
