package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {
    // Profession findByProfessionName(String name);
}