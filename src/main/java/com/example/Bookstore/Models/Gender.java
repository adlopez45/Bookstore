package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "genders")
public class Gender {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "gender_id")
 private Integer genderId;
 @Column(name = "gender_name", nullable = false, length = 20)
 private String genderName;
 @Column(name = "status")
 private Byte status;
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;
 @OneToMany(mappedBy = "gender", fetch = FetchType.LAZY)
 private List<User> users;
 public Gender() {
 }
 public Integer getGenderId() { return genderId; }
 public void setGenderId(Integer genderId) { this.genderId = genderId; }
 public String getGenderName() { return genderName; }
 public void setGenderName(String genderName) { this.genderName = genderName;
}
 public Byte getStatus() { return status; }
 public void setStatus(Byte status) { this.status = status; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
 public List<User> getUsers() { return users; }
 public void setUsers(List<User> users) { this.users = users; }
}