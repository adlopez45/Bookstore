package com.example.Bookstore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Bookstore.Models.Book;
import com.example.Bookstore.Repositories.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Integer id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElse(null);
    }


    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByTitleContainingIgnoreCase(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

     public List<Book> findByAuthorContainingIgnoreCase(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

}
