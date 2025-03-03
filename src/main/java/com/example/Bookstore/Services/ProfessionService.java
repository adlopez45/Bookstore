package com.example.Bookstore.Services;

import com.example.Bookstore.Models.Profession;
import com.example.Bookstore.Repositories.ProfessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionService {

    private final ProfessionRepository professionRepository;

    public ProfessionService(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    // CREATE/UPDATE
    public Profession save(Profession profession) {
        return professionRepository.save(profession);
    }

    // READ (todos)
    public List<Profession> findAll() {
        return professionRepository.findAll();
    }

    // READ (uno por ID)
    public Profession findById(Integer id) {
        Optional<Profession> optional = professionRepository.findById(id);
        return optional.orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        professionRepository.deleteById(id);
    }
}
