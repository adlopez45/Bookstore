package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.Gender;
import com.example.Bookstore.Services.GenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Gender (género de usuario).
 * Permite CRUD sobre los géneros.
 */
@RestController
@RequestMapping("/api/genders")
public class GenderController {

    private final GenderService genderService;

    /**
     * Se inyecta el servicio de Gender.
     */
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    /**
     * GET /api/genders
     * Lista todos los géneros.
     */
    @GetMapping
    public List<Gender> getAllGenders() {
        return genderService.findAll();
    }

    /**
     * GET /api/genders/{id}
     * Obtiene un género por su ID.
     */
    @GetMapping("/{id}")
    public Gender getGenderById(@PathVariable Integer id) {
        return genderService.findById(id);
    }

    /**
     * POST /api/genders
     * Crea un nuevo género.
     */
    @PostMapping
    public Gender createGender(@RequestBody Gender gender) {
        return genderService.save(gender);
    }

    /**
     * PUT /api/genders/{id}
     * Actualiza un género existente.
     */
    @PutMapping("/{id}")
    public Gender updateGender(@PathVariable Integer id, @RequestBody Gender request) {
        Gender existing = genderService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setGenderName(request.getGenderName());
        existing.setStatus(request.getStatus());
        return genderService.save(existing);
    }

    /**
     * DELETE /api/genders/{id}
     * Elimina un género por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteGender(@PathVariable Integer id) {
        genderService.deleteById(id);
    }
}
