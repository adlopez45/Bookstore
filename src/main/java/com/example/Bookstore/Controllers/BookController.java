package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.Book;
import com.example.Bookstore.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/title")
    public ResponseEntity<List<Book>> getBooksByTitle(@RequestParam String name) {
        List<Book> books = bookService.findByTitleContaining(name);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author) {
        List<Book> books = bookService.findByAuthorContaining(author);
        return ResponseEntity.ok(books);
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return ResponseEntity.ok(savedBook);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.findById(id)
                .map(existingBook -> {
                    book.setId(id);
                    return ResponseEntity.ok(bookService.save(book));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> {
                    bookService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}