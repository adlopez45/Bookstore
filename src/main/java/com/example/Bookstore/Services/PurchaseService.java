package com.example.Bookstore.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Bookstore.DTO.PurchaseRequest;
import com.example.Bookstore.Models.Book;
import com.example.Bookstore.Models.MembershipCard;
import com.example.Bookstore.Models.Purchase;
import com.example.Bookstore.Models.PurchaseDetails;
import com.example.Bookstore.Models.User;
import com.example.Bookstore.Repositories.BookRepository;
import com.example.Bookstore.Repositories.MembershipCardRepository;
import com.example.Bookstore.Repositories.PurchaseDetailsRepository;
import com.example.Bookstore.Repositories.PurchaseRepository;
import com.example.Bookstore.Repositories.UserRepository;

@Service
public class PurchaseService {

    private final BookRepository bookRepository;
    private final MembershipCardRepository cardRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailsRepository purchaseDetailsRepository;
    private final UserRepository userRepository;

    @Autowired
    public PurchaseService(
            BookRepository bookRepository,
            MembershipCardRepository cardRepository,
            PurchaseRepository purchaseRepository,
            PurchaseDetailsRepository purchaseDetailsRepository,
            UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.cardRepository = cardRepository;
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailsRepository = purchaseDetailsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Purchase makePurchase(PurchaseRequest request) {
        // 1. Validate card and user
        MembershipCard card = cardRepository.findById(request.getCardId())
            .orElseThrow(() -> new IllegalArgumentException("Tarjeta no encontrada"));

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // 2. Create purchase
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setMembershipCard(card); // Changed from setCard to setMembershipCard
        purchase.setStatus((byte) 1);
        purchase.setCreatedAt(new Date());

        // Save purchase first to get ID
        purchase = purchaseRepository.save(purchase);

        double total = 0.0;

        // 3. Process each book
        for (PurchaseRequest.BookPurchase item : request.getBooks()) {
            Book book = bookRepository.findById(item.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));

            if (book.getStock() < item.getQuantity()) {
                throw new IllegalStateException("Stock insuficiente para: " + book.getTitle());
            }

            // Calculate subtotal
            double subtotal = book.getPrice() * item.getQuantity();
            total += subtotal;

            // Create purchase detail
            PurchaseDetails detail = new PurchaseDetails();
            detail.setPurchase(purchase);
            detail.setBook(book);
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(book.getPrice());
            detail.setSubtotal(subtotal);
            detail.setCreatedAt(new Date());

            // Save purchase detail
            purchaseDetailsRepository.save(detail);

            // Update book stock
            book.setStock(book.getStock() - item.getQuantity());
            bookRepository.save(book);
        }

        // 4. Validate and update card balance
        if (card.getBalance() < total) {
            throw new IllegalStateException("Saldo insuficiente en la tarjeta");
        }
        card.setBalance(card.getBalance() - total);
        cardRepository.save(card);

        // 5. Set total and update purchase
        purchase.setTotal(total);
        return purchaseRepository.save(purchase);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public Purchase findById(Integer id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        purchaseRepository.deleteById(id);
    }
}
