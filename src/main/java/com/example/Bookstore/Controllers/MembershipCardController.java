package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.MembershipCard;
import com.example.Bookstore.Services.MembershipCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership-cards")
public class MembershipCardController {

    private final MembershipCardService cardService;

    public MembershipCardController(MembershipCardService cardService) {
        this.cardService = cardService;
    }

    // GET /api/membership-cards : Lista todas las tarjetas
    @GetMapping
    public List<MembershipCard> getAllCards() {
        return cardService.findAll();
    }

    // GET /api/membership-cards/{id} : Obtiene una tarjeta por ID
    @GetMapping("/{id}")
    public MembershipCard getCardById(@PathVariable Integer id) {
        return cardService.findById(id);
    }

    // POST /api/membership-cards : Crea una nueva tarjeta
    @PostMapping
    public MembershipCard createCard(@RequestBody MembershipCard card) {
        return cardService.save(card);
    }

    // PUT /api/membership-cards/{id} : Actualiza una tarjeta existente
    @PutMapping("/{id}")
    public MembershipCard updateCard(@PathVariable Integer id, @RequestBody MembershipCard request) {
        MembershipCard existing = cardService.findById(id);
        if (existing == null) {
            return null; // o lanzar excepción
        }
        existing.setCardNumber(request.getCardNumber());
        existing.setBalance(request.getBalance());
        existing.setUser(request.getUser());
        existing.setStatus(request.getStatus());
        return cardService.save(existing);
    }

    // DELETE /api/membership-cards/{id} : Elimina una tarjeta por su ID
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Integer id) {
        cardService.deleteById(id);
    }

    /**
     * PUT /api/membership-cards/{id}/recharge?amount=XXXX
     * Recarga la tarjeta con un valor entre 50,000 y 200,000, e imprime el saldo previo y actual.
     */
    @PutMapping("/{id}/recharge")
    public ResponseEntity<?> rechargeCard(@PathVariable("id") Integer cardId,
                                          @RequestParam("amount") Double amount) {
        try {
            String message = cardService.recargar(cardId, amount);
            return ResponseEntity.ok("{\"message\": \"" + message + "\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
