package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "balance_history")
public class BalanceHistory {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "history_id")
 private Integer historyId;
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "card_id", nullable = false)
 private MembershipCard membershipCard;
 @Column(name = "amount", nullable = false)
 private Double amount;
 @Column(name = "transaction_type", nullable = false, length = 10)
 private String transactionType;
 @Column(name = "status")
 private Byte status;
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;
 public BalanceHistory() {
 }
 public Integer getHistoryId() { return historyId; }
 public void setHistoryId(Integer historyId) { this.historyId = historyId; }
 public MembershipCard getMembershipCard() { return membershipCard; }
 public void setMembershipCard(MembershipCard membershipCard) {
this.membershipCard = membershipCard; }
 public Double getAmount() { return amount; }
 public void setAmount(Double amount) { this.amount = amount; }
 public String getTransactionType() { return transactionType; }
 public void setTransactionType(String transactionType) { this.transactionType =
transactionType; }
 public Byte getStatus() { return status; }
 public void setStatus(Byte status) { this.status = status; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
