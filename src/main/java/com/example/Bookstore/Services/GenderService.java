package com.example.Bookstore.Services;

import com.example.Bookstore.Models.Gender;
import com.example.Bookstore.Repositories.GenderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {

    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    // CREATE/UPDATE
    public Gender save(Gender gender) {
        return genderRepository.save(gender);
    }

    // READ (todos)
    public List<Gender> findAll() {
        return genderRepository.findAll();
    }

    // READ (uno por ID)
    public Gender findById(Integer id) {
        Optional<Gender> optional = genderRepository.findById(id);
        return optional.orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        genderRepository.deleteById(id);
    }
}
