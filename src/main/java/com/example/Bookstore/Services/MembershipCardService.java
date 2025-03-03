package com.example.Bookstore.Services;

import com.example.Bookstore.Models.MembershipCard;
import com.example.Bookstore.Repositories.MembershipCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipCardService {

    private final MembershipCardRepository membershipCardRepository;

    public MembershipCardService(MembershipCardRepository membershipCardRepository) {
        this.membershipCardRepository = membershipCardRepository;
    }

    // CREATE/UPDATE
    public MembershipCard save(MembershipCard card) {
        return membershipCardRepository.save(card);
    }

    // READ (todos)
    public List<MembershipCard> findAll() {
        return membershipCardRepository.findAll();
    }

    // READ (uno por ID)
    public MembershipCard findById(Integer id) {
        Optional<MembershipCard> optional = membershipCardRepository.findById(id);
        return optional.orElse(null);
    }

    // Recarga de saldo (ejemplo con validación mínima/máxima)
    @Transactional
    public void recargar(Integer cardId, Double amount) {
        // Validaciones (mínimo 50,000 y máximo 200,000)
        if (amount < 50000 || amount > 200000) {
            throw new RuntimeException("El valor a recargar debe estar entre 50.000 y 200.000");
        }
        // Llamada al método del repositorio
        int rows = membershipCardRepository.recargarBalance(cardId, amount);
        if (rows == 0) {
            throw new RuntimeException("No se pudo recargar la tarjeta. Verifica que exista o revisa el saldo.");
        }
    }

    // DELETE
    public void deleteById(Integer id) {
        membershipCardRepository.deleteById(id);
    }
}
