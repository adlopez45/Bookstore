package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
    // Gender findByGenderName(String name);
}