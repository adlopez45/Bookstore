package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, Integer> {
    
}