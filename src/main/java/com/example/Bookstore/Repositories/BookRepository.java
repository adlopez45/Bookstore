package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // (a) Buscar libros cuyo título contenga un texto (ignora mayúsculas/minúsculas)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // (a) Buscar un libro por ISBN exacto
    Book findByIsbn(String isbn);

    // (b) Para procesar compras, podrías necesitar cargar varios libros por su lista de IDs de una sola vez:
    @Query("SELECT b FROM Book b WHERE b.bookId IN :ids")
    List<Book> findAllByBookIdsIn(@Param("ids") List<Integer> bookIds);

    // Opcionalmente, si quisieras una actualización de stock directa (poco común en JPA, se suele hacer en Service):
    // @Modifying
    // @Query("UPDATE Book b SET b.stock = b.stock - :quantity WHERE b.bookId = :bookId AND b.stock >= :quantity")
    // int discountStock(@Param("bookId") Integer bookId, @Param("quantity") Integer quantity);
    List<Book> findByTitle(String title);
}