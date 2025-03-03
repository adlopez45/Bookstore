package com.example.Bookstore.Models;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "categories")
public class Category {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "category_id")
 private Integer categoryId;
 @Column(name = "category_name", nullable = false, length = 100)
 private String categoryName;
 @Column(name = "status")
 private Byte status;
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "created_at")
 private Date createdAt;
 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch =
FetchType.LAZY)
 private List<Book> books;
 public Category() {
 }
 public Integer getCategoryId() { return categoryId; }
 public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
 public String getCategoryName() { return categoryName; }
 public void setCategoryName(String categoryName) { this.categoryName =
categoryName; }
 public Byte getStatus() { return status; }
 public void setStatus(Byte status) { this.status = status; }
 public Date getCreatedAt() { return createdAt; }
 public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
 public List<Book> getBooks() { return books; }
 public void setBooks(List<Book> books) { this.books = books; }
}
