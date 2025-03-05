package com.example.Bookstore.DTO;
import java.util.List;

public class PurchaseRequest {
    private Long userId;
    private Long cardId;
    private List<BookPurchase> books;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCardId() { return cardId; }
    public void setCardId(Long cardId) { this.cardId = cardId; }

    public List<BookPurchase> getBooks() { return books; }
    public void setBooks(List<BookPurchase> books) { this.books = books; }

    public static class BookPurchase {
        private Long bookId;
        private int quantity;

        public Long getBookId() { return bookId; }
        public void setBookId(Long bookId) { this.bookId = bookId; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}

