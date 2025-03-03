package com.example.Bookstore.Services;

import com.example.Bookstore.Models.Country;
import com.example.Bookstore.Repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // CREATE/UPDATE
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    // READ (todos)
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    // READ (uno por ID)
    public Country findById(Integer id) {
        Optional<Country> optional = countryRepository.findById(id);
        return optional.orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        countryRepository.deleteById(id);
    }
}
