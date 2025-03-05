package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "membership_cards")
public class MembershipCard {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "card_id")
 private Integer cardId;

 @Column(name = "card_number", nullable = false, length = 16)
 private String cardNumber;

 @Column(name = "balance", nullable = false)
 private Double balance;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "user_id", nullable = false)
 private User user;

 @Column(name = "status")
 private Byte status;

 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;

 @OneToMany(mappedBy = "membershipCard", cascade = CascadeType.ALL, fetch
= FetchType.LAZY)
 private List<BalanceHistory> balanceHistory;
 public MembershipCard() {
 }
 
 public Integer getCardId() { return cardId; }
 public void setCardId(Integer cardId) { this.cardId = cardId; }
 public String getCardNumber() { return cardNumber; }
 public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
 public Double getBalance() { return balance; }
 public void setBalance(Double balance) { this.balance = balance; }
 public User getUser() { return user; }
 public void setUser(User user) { this.user = user; }
 public Byte getStatus() { return status; }
 public void setStatus(Byte status) { this.status = status; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
 public List<BalanceHistory> getBalanceHistory() { return balanceHistory; }
 public void setBalanceHistory(List<BalanceHistory> balanceHistory) {
this.balanceHistory = balanceHistory; }
}