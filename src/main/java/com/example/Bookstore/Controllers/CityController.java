package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.City;
import com.example.Bookstore.Services.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad City (ciudades).
 * Permite CRUD sobre las ciudades.
 */
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    /**
     * Se inyecta el servicio de City.
     */
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * GET /api/cities
     * Lista todas las ciudades.
     */
    @GetMapping
    public List<City> getAllCities() {
        return cityService.findAll();
    }

    /**
     * GET /api/cities/{id}
     * Obtiene una ciudad por ID.
     */
    @GetMapping("/{id}")
    public City getCityById(@PathVariable Integer id) {
        return cityService.findById(id);
    }

    /**
     * POST /api/cities
     * Crea una nueva ciudad.
     */
    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.save(city);
    }

    /**
     * PUT /api/cities/{id}
     * Actualiza una ciudad existente.
     */
    @PutMapping("/{id}")
    public City updateCity(@PathVariable Integer id, @RequestBody City request) {
        City existing = cityService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setCityName(request.getCityName());
        existing.setCountry(request.getCountry());
        existing.setStatus(request.getStatus());
        return cityService.save(existing);
    }

    /**
     * DELETE /api/cities/{id}
     * Elimina una ciudad por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Integer id) {
        cityService.deleteById(id);
    }
}
