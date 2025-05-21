package com.example.Bookstore.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.DTO.PurchaseRequest;
import com.example.Bookstore.Models.Purchase;
import com.example.Bookstore.Services.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // GET /api/purchases : lista todas las compras
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.findAll();
    }

    // GET /api/purchases/{id} : consulta una compra por ID
    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Integer id) {
        return purchaseService.findById(id);
    }

    // POST /api/purchases : crear/actualizar compra manualmente
    @PostMapping
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

    // PUT /api/purchases/{id} : actualizar compra
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

    // DELETE /api/purchases/{id} : elimina compra
    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable Integer id) {
        purchaseService.deleteById(id);
    }

    // POST /api/purchases/make : compra de uno o más libros
    @PostMapping("/make")
    public Purchase makePurchase(@RequestBody PurchaseRequest request) {
        // AQUÍ se lanza "Libro no encontrado" si no existe en DB
        return purchaseService.makePurchase(request);
    }
}
