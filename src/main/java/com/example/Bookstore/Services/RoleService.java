package com.example.Bookstore.Services;

import com.example.Bookstore.Models.Role;
import com.example.Bookstore.Repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar la entidad Role.
 * Ofrece métodos CRUD básicos, haciendo uso de RoleRepository.
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Inyección del repositorio por constructor.
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Guarda (crea o actualiza) un Role en la base de datos.
     */
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Retorna todos los Roles disponibles.
     */
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * Busca un Role por su ID. Retorna null si no se encuentra.
     */
    public Role findById(Integer id) {
        Optional<Role> optional = roleRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * Elimina un Role por su ID.
     */
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }
}
