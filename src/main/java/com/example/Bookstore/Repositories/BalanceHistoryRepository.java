package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, Integer> {
    // Métodos custom si los necesitas
}
