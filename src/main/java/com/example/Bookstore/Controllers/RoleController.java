package com.example.Bookstore.Controllers;

import com.example.Bookstore.Models.Role;
import com.example.Bookstore.Services.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la entidad Role (roles de usuario).
 * Permite CRUD sobre los roles.
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    /**
     * Se inyecta el servicio de Role.
     */
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * GET /api/roles
     * Lista todos los roles.
     */
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    /**
     * GET /api/roles/{id}
     * Obtiene un rol por ID.
     */
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Integer id) {
        return roleService.findById(id);
    }

    /**
     * POST /api/roles
     * Crea un nuevo rol.
     */
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    /**
     * PUT /api/roles/{id}
     * Actualiza un rol existente.
     */
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Integer id, @RequestBody Role request) {
        Role existing = roleService.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setRoleName(request.getRoleName());
        existing.setStatus(request.getStatus());
        return roleService.save(existing);
    }

    /**
     * DELETE /api/roles/{id}
     * Elimina un rol por su ID.
     */
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Integer id) {
        roleService.deleteById(id);
    }
}
