package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.SaleTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTrackingRepository extends JpaRepository<SaleTracking, Long> {
    // Custom query methods can be added here
}