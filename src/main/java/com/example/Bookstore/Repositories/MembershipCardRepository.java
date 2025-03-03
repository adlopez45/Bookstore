package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.MembershipCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MembershipCardRepository extends JpaRepository<MembershipCard, Integer> {

    // Buscar tarjeta por número
    MembershipCard findByCardNumber(String cardNumber);

    // (Opcional) método para recargar saldo directamente
    @Modifying
    @Query("UPDATE MembershipCard m SET m.balance = m.balance + :amount WHERE m.cardId = :cardId")
    int recargarBalance(@Param("cardId") Integer cardId, @Param("amount") Double amount);

    // (Opcional) método para descontar saldo al comprar
    @Modifying
    @Query("UPDATE MembershipCard m SET m.balance = m.balance - :amount WHERE m.cardId = :cardId AND m.balance >= :amount")
    int descontarBalance(@Param("cardId") Integer cardId, @Param("amount") Double amount);
}