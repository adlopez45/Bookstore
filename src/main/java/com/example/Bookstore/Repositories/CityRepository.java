package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    // Ejemplo: buscar ciudad por nombre
    // City findByCityName(String cityName);
}