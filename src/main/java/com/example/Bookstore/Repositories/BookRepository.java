package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    Book findByIsbn(String isbn);

    List<Book> findByTitle(String title);
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
