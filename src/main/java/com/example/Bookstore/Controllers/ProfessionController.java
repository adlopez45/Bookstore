package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.Profession;
import com.example.Bookstore.Services.ProfessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Profession (profesiones de usuario).
 * Permite CRUD sobre las profesiones.
 */
@RestController
@RequestMapping("/api/professions")
public class ProfessionController {

    private final ProfessionService professionService;

    /**
     * Se inyecta el servicio de Profession.
     */
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    /**
     * GET /api/professions
     * Lista todas las profesiones.
     */
    @GetMapping
    public List<Profession> getAllProfessions() {
        return professionService.findAll();
    }

    /**
     * GET /api/professions/{id}
     * Obtiene una profesión por ID.
     */
    @GetMapping("/{id}")
    public Profession getProfessionById(@PathVariable Integer id) {
        return professionService.findById(id);
    }

    /**
     * POST /api/professions
     * Crea una nueva profesión.
     */
    @PostMapping
    public Profession createProfession(@RequestBody Profession profession) {
        return professionService.save(profession);
    }

    /**
     * PUT /api/professions/{id}
     * Actualiza una profesión existente.
     */
    @PutMapping("/{id}")
    public Profession updateProfession(@PathVariable Integer id, @RequestBody Profession request) {
        Profession existing = professionService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setProfessionName(request.getProfessionName());
        existing.setStatus(request.getStatus());
        return professionService.save(existing);
    }

    /**
     * DELETE /api/professions/{id}
     * Elimina una profesión por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteProfession(@PathVariable Integer id) {
        professionService.deleteById(id);
    }
}
