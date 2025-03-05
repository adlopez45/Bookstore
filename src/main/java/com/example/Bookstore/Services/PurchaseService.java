package com.example.Bookstore.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.example.Bookstore.DTO.PurchaseRequest;
import com.example.Bookstore.Models.Book;
import com.example.Bookstore.Models.MembershipCard;
import com.example.Bookstore.Models.Purchase;
import com.example.Bookstore.Models.PurchaseDetails;
import com.example.Bookstore.Repositories.BookRepository;
import com.example.Bookstore.Repositories.MembershipCardRepository;
import com.example.Bookstore.Repositories.PurchaseDetailsRepository;
import com.example.Bookstore.Repositories.PurchaseRepository;
import com.example.Bookstore.Models.User;
import com.example.Bookstore.Repositories.UserRepository;

@Service
public class PurchaseService {

    private final BookRepository bookRepository;
    private final MembershipCardRepository cardRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailsRepository purchaseDetailRepository;
    private final UserRepository userRepository;

    public PurchaseService(BookRepository bookRepository, MembershipCardRepository cardRepository, 
                           PurchaseRepository purchaseRepository, PurchaseDetailsRepository purchaseDetailRepository, UserRepository userRepository ) {
        this.bookRepository = bookRepository;
        this.cardRepository = cardRepository;
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailRepository = purchaseDetailRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String makePurchase(PurchaseRequest request) {
        // Verificar que la tarjeta existe
        MembershipCard card = cardRepository.findById(request.getCardId().intValue())
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        double totalPrice = 0.0;

        for (PurchaseRequest.BookPurchase item : request.getBooks()) {
            Book book = bookRepository.findById(item.getBookId().intValue())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            if (book.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el libro: " + book.getTitle());
            }

            totalPrice += book.getPrice() * item.getQuantity();
        }

        // Verificar si el saldo de la tarjeta es suficiente
        if (card.getBalance() < totalPrice) {
            throw new RuntimeException("Saldo insuficiente en la tarjeta");
        }

        // Descontar saldo de la tarjeta
        card.setBalance(card.getBalance() - totalPrice);
        cardRepository.save(card);

        // Crear compra
        Purchase purchase = new Purchase();
        User user = userRepository.findById(request.getUserId().intValue())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        purchase.setUser(user);
        purchase.setCard(card);
        purchase.setTotal(totalPrice);
        purchase.setStatus((byte) 1); // Compra activa
        purchaseRepository.save(purchase);

        // Registrar detalles de la compra y actualizar stock
        for (PurchaseRequest.BookPurchase item : request.getBooks()) {
            Book book = bookRepository.findById(item.getBookId().intValue()).get();
            book.setStock(book.getStock() - item.getQuantity());
            bookRepository.save(book);

            PurchaseDetails detail = new PurchaseDetails();
            detail.setPurchase(purchase);
            detail.setBook(book);
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(book.getPrice());
            detail.setSubtotal(book.getPrice() * item.getQuantity());
            purchaseDetailRepository.save(detail);
        }

        return "Compra realizada con éxito";
    }

    // CREATE/UPDATE
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    // READ (todos)
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    // READ (uno por ID)
    public Purchase findById(Integer id) {
        Optional<Purchase> optional = purchaseRepository.findById(id);
        return optional.orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        purchaseRepository.deleteById(id);
    }
}