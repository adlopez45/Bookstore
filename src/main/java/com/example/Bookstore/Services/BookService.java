package com.example.Bookstore.Services;

import com.example.Bookstore.Models.Book;
import com.example.Bookstore.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    
    public List<Book> findByTitleContaining(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Book> findByAuthorContaining(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}