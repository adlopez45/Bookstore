package com.example.Bookstore.Services;

import com.example.Bookstore.Models.City;
import com.example.Bookstore.Repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // CREATE/UPDATE
    public City save(City city) {
        return cityRepository.save(city);
    }

    // READ (todos)
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    // READ (uno por ID)
    public City findById(Integer id) {
        Optional<City> optional = cityRepository.findById(id);
        return optional.orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        cityRepository.deleteById(id);
    }
}
