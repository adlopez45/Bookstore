package com.example.Bookstore.Models;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class SaleTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String userId;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;
    
    @Column(nullable = false)
    private Double amount;
    
    @Column(nullable = false)
    private String status;
    
    // Constructors
    public SaleTracking() {}
    
    public SaleTracking(String userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
        this.saleDate = new Date();
        this.status = "PENDING";
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Date getSaleDate() {
        return saleDate;
    }
    
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}