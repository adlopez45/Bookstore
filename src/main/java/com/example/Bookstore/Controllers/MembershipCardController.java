package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.MembershipCard;
import com.example.Bookstore.Services.MembershipCardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad MembershipCard (tarjeta de membresía).
 * Permite CRUD sobre las tarjetas.
 */
@RestController
@RequestMapping("/api/membership-cards")
public class MembershipCardController {

    private final MembershipCardService cardService;

    /**
     * Se inyecta el servicio de MembershipCard por constructor.
     */
    public MembershipCardController(MembershipCardService cardService) {
        this.cardService = cardService;
    }

    /**
     * GET /api/membership-cards
     * Lista todas las tarjetas de membresía.
     */
    @GetMapping
    public List<MembershipCard> getAllCards() {
        return cardService.findAll();
    }

    /**
     * GET /api/membership-cards/{id}
     * Obtiene una tarjeta por ID.
     */
    @GetMapping("/{id}")
    public MembershipCard getCardById(@PathVariable Integer id) {
        return cardService.findById(id);
    }

    /**
     * POST /api/membership-cards
     * Crea una nueva tarjeta de membresía.
     */
    @PostMapping
    public MembershipCard createCard(@RequestBody MembershipCard card) {
        return cardService.save(card);
    }

    /**
     * PUT /api/membership-cards/{id}
     * Actualiza datos de una tarjeta existente.
     */
    @PutMapping("/{id}")
    public MembershipCard updateCard(@PathVariable Integer id, @RequestBody MembershipCard request) {
        MembershipCard existing = cardService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setCardNumber(request.getCardNumber());
        existing.setBalance(request.getBalance());
        existing.setUser(request.getUser());
        existing.setStatus(request.getStatus());
        return cardService.save(existing);
    }

    /**
     * DELETE /api/membership-cards/{id}
     * Elimina una tarjeta por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Integer id) {
        cardService.deleteById(id);
    }
}
