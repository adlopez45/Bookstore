package com.example.Bookstore.Services;

import com.example.Bookstore.Models.PurchaseDetails;
import com.example.Bookstore.Repositories.PurchaseDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseDetailsService {

    private final PurchaseDetailsRepository purchaseDetailsRepository;

    public PurchaseDetailsService(PurchaseDetailsRepository purchaseDetailsRepository) {
        this.purchaseDetailsRepository = purchaseDetailsRepository;
    }

    // CREATE/UPDATE
    public PurchaseDetails save(PurchaseDetails pd) {
        return purchaseDetailsRepository.save(pd);
    }

    // READ (todos)
    public List<PurchaseDetails> findAll() {
        return purchaseDetailsRepository.findAll();
    }

    // READ (uno por ID)
    public PurchaseDetails findById(Integer id) {
        Optional<PurchaseDetails> optional = purchaseDetailsRepository.findById(id);
        return optional.orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        purchaseDetailsRepository.deleteById(id);
    }
}
