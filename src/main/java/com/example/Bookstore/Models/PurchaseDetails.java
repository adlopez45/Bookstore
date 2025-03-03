package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "purchase_details")
public class PurchaseDetails {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "detail_id")
 private Integer detailId;
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "purchase_id", nullable = false)
 private Purchase purchase;
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "book_id", nullable = false)
 private Book book;
 @Column(name = "quantity", nullable = false)
 private Integer quantity;
 @Column(name = "unit_price", nullable = false)
 private Double unitPrice;
 @Column(name = "subtotal", nullable = false)
 private Double subtotal;
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;
 
 public PurchaseDetails() {
 }
 public Integer getDetailId() { return detailId; }
 public void setDetailId(Integer detailId) { this.detailId = detailId; }
 public Purchase getPurchase() { return purchase; }
 public void setPurchase(Purchase purchase) { this.purchase = purchase; }
 public Book getBook() { return book; }
 public void setBook(Book book) { this.book = book; }
 public Integer getQuantity() { return quantity; }
 public void setQuantity(Integer quantity) { this.quantity = quantity; }
 public Double getUnitPrice() { return unitPrice; }
 public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
 public Double getSubtotal() { return subtotal; }
 public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}