package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Role findByRoleName(String roleName);
}