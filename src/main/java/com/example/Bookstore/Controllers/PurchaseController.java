package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.Purchase;
import com.example.Bookstore.Services.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Purchase (compras).
 * Permite listar, crear, actualizar y eliminar compras.
 */
@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    /**
     * Se inyecta el servicio de Purchase por constructor.
     */
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * GET /api/purchases
     * Lista todas las compras.
     */
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.findAll();
    }

    /**
     * GET /api/purchases/{id}
     * Retorna una compra específica según su ID.
     */
    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Integer id) {
        return purchaseService.findById(id);
    }

    /**
     * POST /api/purchases
     * Crea una nueva compra.
     */
    @PostMapping
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

    /**
     * PUT /api/purchases/{id}
     * Actualiza una compra existente.
     */
    @PutMapping("/{id}")
    public Purchase updatePurchase(@PathVariable Integer id, @RequestBody Purchase request) {
        Purchase existing = purchaseService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setUser(request.getUser());
        existing.setMembershipCard(request.getMembershipCard());
        existing.setTotal(request.getTotal());
        existing.setStatus(request.getStatus());
        return purchaseService.save(existing);
    }

    /**
     * DELETE /api/purchases/{id}
     * Elimina una compra por su ID.
     */
    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable Integer id) {
        purchaseService.deleteById(id);
    }
}
