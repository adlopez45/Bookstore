package com.example.Bookstore.Services;

import com.example.Bookstore.Models.BalanceHistory;
import com.example.Bookstore.Repositories.BalanceHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceHistoryService {

    private final BalanceHistoryRepository balanceHistoryRepository;

    public BalanceHistoryService(BalanceHistoryRepository balanceHistoryRepository) {
        this.balanceHistoryRepository = balanceHistoryRepository;
    }

    // CREATE/UPDATE
    public BalanceHistory save(BalanceHistory bh) {
        return balanceHistoryRepository.save(bh);
    }

    // READ (todos)
    public List<BalanceHistory> findAll() {
        return balanceHistoryRepository.findAll();
    }

    // READ (uno por ID)
    public BalanceHistory findById(Integer id) {
        Optional<BalanceHistory> optional = balanceHistoryRepository.findById(id);
        return optional.orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        balanceHistoryRepository.deleteById(id);
    }
}
