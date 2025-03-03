package com.example.Bookstore.Services;

import com.example.Bookstore.Models.Book;
import com.example.Bookstore.Repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CREATE/UPDATE
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // READ (todos)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // READ (uno por ID)
    public Book findById(Integer id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElse(null);
    }

    // Búsqueda por ISBN
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    // DELETE
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }
}
