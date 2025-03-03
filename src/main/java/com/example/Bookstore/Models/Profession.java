package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "professions")
public class Profession {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "profession_id")
 private Integer professionId;
 @Column(name = "profession_name", nullable = false, length = 100)
 private String professionName;
 @Column(name = "status")
 private Byte status;
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;
 @OneToMany(mappedBy = "profession", fetch = FetchType.LAZY)
 private List<User> users;
 public Profession() {
 }
 public Integer getProfessionId() { return professionId; }
 public void setProfessionId(Integer professionId) { this.professionId = professionId; }
 public String getProfessionName() { return professionName; }
 public void setProfessionName(String professionName) { this.professionName =
professionName; }
 public Byte getStatus() { return status; }
 public void setStatus(Byte status) { this.status = status; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
 public List<User> getUsers() { return users; }
 public void setUsers(List<User> users) { this.users = users; }
}