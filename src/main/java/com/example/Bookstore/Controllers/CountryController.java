package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.Country;
import com.example.Bookstore.Services.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Country (países).
 * Permite CRUD sobre los países.
 */
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    /**
     * Se inyecta el servicio de Country.
     */
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * GET /api/countries
     * Lista todos los países.
     */
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }

    /**
     * GET /api/countries/{id}
     * Obtiene un país por ID.
     */
    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Integer id) {
        return countryService.findById(id);
    }

    /**
     * POST /api/countries
     * Crea un nuevo país.
     */
    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryService.save(country);
    }

    /**
     * PUT /api/countries/{id}
     * Actualiza un país existente.
     */
    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Integer id, @RequestBody Country request) {
        Country existing = countryService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setCountryName(request.getCountryName());
        existing.setStatus(request.getStatus());
        return countryService.save(existing);
    }

    /**
     * DELETE /api/countries/{id}
     * Elimina un país por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Integer id) {
        countryService.deleteById(id);
    }
}
