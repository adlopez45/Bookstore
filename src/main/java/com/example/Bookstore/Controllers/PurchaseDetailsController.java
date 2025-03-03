package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.PurchaseDetails;
import com.example.Bookstore.Services.PurchaseDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad PurchaseDetails (detalles de compra).
 * Permite listar, crear, actualizar y eliminar detalles.
 */
@RestController
@RequestMapping("/api/purchase-details")
public class PurchaseDetailsController {

    private final PurchaseDetailsService purchaseDetailsService;

    /**
     * Se inyecta el servicio de PurchaseDetails.
     */
    public PurchaseDetailsController(PurchaseDetailsService purchaseDetailsService) {
        this.purchaseDetailsService = purchaseDetailsService;
    }

    /**
     * GET /api/purchase-details
     * Lista todos los detalles de compra.
     */
    @GetMapping
    public List<PurchaseDetails> getAllPurchaseDetails() {
        return purchaseDetailsService.findAll();
    }

    /**
     * GET /api/purchase-details/{id}
     * Retorna un detalle específico según su ID.
     */
    @GetMapping("/{id}")
    public PurchaseDetails getPurchaseDetailById(@PathVariable Integer id) {
        return purchaseDetailsService.findById(id);
    }

    /**
     * POST /api/purchase-details
     * Crea un nuevo detalle de compra.
     */
    @PostMapping
    public PurchaseDetails createPurchaseDetail(@RequestBody PurchaseDetails pd) {
        return purchaseDetailsService.save(pd);
    }

    /**
     * PUT /api/purchase-details/{id}
     * Actualiza un detalle de compra existente.
     */
    @PutMapping("/{id}")
    public PurchaseDetails updatePurchaseDetail(@PathVariable Integer id, @RequestBody PurchaseDetails request) {
        PurchaseDetails existing = purchaseDetailsService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setPurchase(request.getPurchase());
        existing.setBook(request.getBook());
        existing.setQuantity(request.getQuantity());
        existing.setUnitPrice(request.getUnitPrice());
        existing.setSubtotal(request.getSubtotal());
        return purchaseDetailsService.save(existing);
    }

    /**
     * DELETE /api/purchase-details/{id}
     * Elimina un detalle de compra por ID.
     */
    @DeleteMapping("/{id}")
    public void deletePurchaseDetail(@PathVariable Integer id) {
        purchaseDetailsService.deleteById(id);
    }
}
