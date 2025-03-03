package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.BalanceHistory;
import com.example.Bookstore.Services.BalanceHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad BalanceHistory (historial de balance).
 * Permite listar, crear, actualizar y eliminar registros de historial.
 */
@RestController
@RequestMapping("/api/balance-history")
public class BalanceHistoryController {

    private final BalanceHistoryService balanceHistoryService;

    /**
     * Se inyecta el servicio de BalanceHistory por constructor.
     */
    public BalanceHistoryController(BalanceHistoryService balanceHistoryService) {
        this.balanceHistoryService = balanceHistoryService;
    }

    /**
     * GET /api/balance-history
     * Lista todos los registros de historial de balance.
     */
    @GetMapping
    public List<BalanceHistory> getAllBalanceHistory() {
        return balanceHistoryService.findAll();
    }

    /**
     * GET /api/balance-history/{id}
     * Obtiene un historial por ID.
     */
    @GetMapping("/{id}")
    public BalanceHistory getBalanceHistoryById(@PathVariable Integer id) {
        return balanceHistoryService.findById(id);
    }

    /**
     * POST /api/balance-history
     * Crea un nuevo registro de historial de balance.
     */
    @PostMapping
    public BalanceHistory createBalanceHistory(@RequestBody BalanceHistory bh) {
        return balanceHistoryService.save(bh);
    }

    /**
     * PUT /api/balance-history/{id}
     * Actualiza un registro de historial existente.
     */
    @PutMapping("/{id}")
    public BalanceHistory updateBalanceHistory(@PathVariable Integer id, @RequestBody BalanceHistory request) {
        BalanceHistory existing = balanceHistoryService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setMembershipCard(request.getMembershipCard());
        existing.setAmount(request.getAmount());
        existing.setTransactionType(request.getTransactionType());
        existing.setStatus(request.getStatus());
        return balanceHistoryService.save(existing);
    }

    /**
     * DELETE /api/balance-history/{id}
     * Elimina un historial por ID.
     */
    @DeleteMapping("/{id}")
    public void deleteBalanceHistory(@PathVariable Integer id) {
        balanceHistoryService.deleteById(id);
    }
}
