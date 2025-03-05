package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "purchases")
public class Purchase {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "purchase_id")
 private Integer purchaseId;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "user_id", nullable = false)
 private User user;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "card_id")
 private MembershipCard membershipCard;

 @Column(name = "total", nullable = false)
 private Double total;

 @Column(name = "status")
 private Byte status;

 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;

 @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch =
FetchType.LAZY)
 private List<PurchaseDetails> purchaseDetails;
 public Purchase() {
 }

 public void setCard(MembershipCard card) {
    this.membershipCard = card;
}

public MembershipCard getCard() {
    return membershipCard;
}

 public Integer getPurchaseId() { return purchaseId; }
 public void setPurchaseId(Integer purchaseId) { this.purchaseId = purchaseId; }
 public User getUser() { return user; }
 public void setUser(User user) { this.user = user; }
 public MembershipCard getMembershipCard() { return membershipCard; }
 public void setMembershipCard(MembershipCard membershipCard) {
this.membershipCard = membershipCard; }
 public Double getTotal() { return total; }
 public void setTotal(Double total) { this.total = total; }
 public Byte getStatus() { return status; }
 public void setStatus(Byte status) { this.status = status; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
 public List<PurchaseDetails> getPurchaseDetails() { return purchaseDetails; }
 public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
this.purchaseDetails = purchaseDetails; }
}