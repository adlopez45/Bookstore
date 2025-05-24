package com.example.Bookstore.Models;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false, unique = true)
    private String isbn;
    
    @Column(nullable = false)
    private Double price;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(nullable = false)
    private Integer stock;
    
    @Column(nullable = false)
    private String description;
    
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "publication_date")
    @Temporal(TemporalType.DATE)
    private Date publicationDate;
    
    @Column(nullable = false)
    private Integer status = 1;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    public Date getPublicationDate() { return publicationDate; }
    public void setPublicationDate(Date publicationDate) { this.publicationDate = publicationDate; }
    
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}