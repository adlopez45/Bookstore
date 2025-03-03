package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.User;
import com.example.Bookstore.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad User (usuarios).
 * Provee endpoints CRUD: listar, obtener por id, crear, actualizar y eliminar usuarios.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Se inyecta el servicio de User mediante el constructor.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /api/users
     * Lista todos los usuarios.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * GET /api/users/{id}
     * Retorna un usuario según su ID.
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    /**
     * POST /api/users
     * Crea un nuevo usuario.
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * PUT /api/users/{id}
     * Actualiza datos de un usuario existente.
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User request) {
        User existing = userService.findById(id);
        if (existing == null) {
            // Maneja el caso de usuario no encontrado (puedes lanzar excepción o similar)
            return null;
        }
        // Se actualizan los campos deseados
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        existing.setCity(request.getCity());
        existing.setCountry(request.getCountry());
        existing.setRole(request.getRole());
        existing.setProfession(request.getProfession());
        existing.setGender(request.getGender());
        existing.setAge(request.getAge());
        existing.setStatus(request.getStatus());
        return userService.save(existing);
    }

    /**
     * DELETE /api/users/{id}
     * Elimina un usuario por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
    }
}
