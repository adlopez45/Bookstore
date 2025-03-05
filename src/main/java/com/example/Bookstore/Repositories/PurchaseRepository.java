package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    
}