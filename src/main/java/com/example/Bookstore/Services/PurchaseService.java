package com.example.Bookstore.Services;

import com.example.Bookstore.Models.Purchase;
import com.example.Bookstore.Repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
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
