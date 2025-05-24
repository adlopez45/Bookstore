package com.example.Bookstore.Services;

import com.example.Bookstore.Models.SaleTracking;
import com.example.Bookstore.Repositories.SaleTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleTrackingService {
    
    private final SaleTrackingRepository saleTrackingRepository;
    
    @Autowired
    public SaleTrackingService(SaleTrackingRepository saleTrackingRepository) {
        this.saleTrackingRepository = saleTrackingRepository;
    }
    
    public SaleTracking createSale(String userId, Double amount) {
        SaleTracking sale = new SaleTracking(userId, amount);
        return saleTrackingRepository.save(sale);
    }
    
    public Optional<SaleTracking> getSale(Long id) {
        return saleTrackingRepository.findById(id);
    }
    
    public List<SaleTracking> getAllSales() {
        return saleTrackingRepository.findAll();
    }
    
    public SaleTracking updateSaleStatus(Long id, String status) {
        Optional<SaleTracking> sale = saleTrackingRepository.findById(id);
        if (sale.isPresent()) {
            SaleTracking updatedSale = sale.get();
            updatedSale.setStatus(status);
            return saleTrackingRepository.save(updatedSale);
        }
        throw new RuntimeException("Sale not found with id: " + id);
    }
    
    public void deleteSale(Long id) {
        saleTrackingRepository.deleteById(id);
    }
}