package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    // Ejemplo: buscar país por nombre
    // Country findByCountryName(String countryName);
}