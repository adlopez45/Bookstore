package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Ejemplo: buscar un usuario por email
    // User findByEmail(String email);
}