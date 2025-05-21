package com.example.Bookstore.DTO;

import java.util.List;

public class PurchaseRequest {
    private Integer userId;    // Ya lo tienes como Integer
    private Integer cardId;    // Ya lo tienes como Integer
    private List<BookPurchase> books;

    // Getters y Setters (asumo que ya los tienes)
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getCardId() { return cardId; }
    public void setCardId(Integer cardId) { this.cardId = cardId; }
    public List<BookPurchase> getBooks() { return books; }
    public void setBooks(List<BookPurchase> books) { this.books = books; }

    public static class BookPurchase {
        private Integer bookId; // Ya lo tienes como Integer
        private int quantity; // Primitivo está bien aquí

        // Getters y Setters (asumo que ya los tienes)
        public Integer getBookId() { return bookId; }
        public void setBookId(Integer bookId) { this.bookId = bookId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}