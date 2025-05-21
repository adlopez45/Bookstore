package com.example.Bookstore.Services;

import com.example.Bookstore.Models.MembershipCard;
import com.example.Bookstore.Repositories.MembershipCardRepository;
import com.example.Bookstore.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipCardService {

    private final MembershipCardRepository membershipCardRepository;
    private final UserRepository userRepository;

    public MembershipCardService(MembershipCardRepository membershipCardRepository,
                                 UserRepository userRepository) {
        this.membershipCardRepository = membershipCardRepository;
        this.userRepository = userRepository;
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

    // DELETE
    public void deleteById(Integer id) {
        membershipCardRepository.deleteById(id);
    }

    /**
     * Método para recargar la tarjeta de membresía.
     * @param cardId ID de la tarjeta.
     * @param amount Valor a recargar (entre 50,000 y 200,000).
     * @return Un mensaje indicando el saldo previo y el saldo actual.
     */
    @Transactional
    public String recargar(Integer cardId, Double amount) {
        // Validar rango de recarga
        if (amount < 50000 || amount > 200000) {
            throw new RuntimeException("El valor a recargar debe estar entre $50,000 y $200,000");
        }

        // Verificar que la tarjeta exista
        MembershipCard card = membershipCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Tarjeta de membresía no encontrada"));

        // Verificar que el usuario asociado a la tarjeta exista
        if (!userRepository.existsById(card.getUser().getUserId())) {
            throw new RuntimeException("El usuario asociado a la tarjeta no existe");
        }

        // Guardar el saldo previo
        double previousBalance = card.getBalance();

        // Actualizar el balance
        card.setBalance(previousBalance + amount);
        membershipCardRepository.save(card);

        // Obtener el nuevo saldo
        double newBalance = card.getBalance();

        return "Saldo previo: $" + previousBalance + " - Saldo actual: $" + newBalance;
    }
}
