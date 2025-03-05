package com.example.Bookstore.Services;

import com.example.Bookstore.Models.MembershipCard;
import com.example.Bookstore.Repositories.MembershipCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.Bookstore.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipCardService {

    private final MembershipCardRepository membershipCardRepository;
    @Autowired
    private UserRepository userRepository;

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
   
        if (amount < 50000 || amount > 200000) {
            throw new RuntimeException("El valor a recargar debe estar entre 50.000 y 200.000");
        }
       
        Optional<MembershipCard> optionalCard = membershipCardRepository.findById(cardId);
        if (!optionalCard.isPresent()) {
            throw new RuntimeException("La tarjeta de membresía no existe.");
        }
        MembershipCard card = optionalCard.get();
        
        if (!userRepository.existsById(card.getUser().getUserId())) {
            throw new RuntimeException("El usuario asociado a la tarjeta no existe.");
        }
        
        card.setBalance(card.getBalance() + amount);
        membershipCardRepository.save(card);
    }
    

    // DELETE
    public void deleteById(Integer id) {
        membershipCardRepository.deleteById(id);
    }
}
