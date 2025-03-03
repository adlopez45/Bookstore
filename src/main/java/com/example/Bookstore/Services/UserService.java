package com.example.Bookstore.Services;

import com.example.Bookstore.Models.User;
import com.example.Bookstore.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE/UPDATE
    public User save(User user) {
        return userRepository.save(user);
    }

    // READ (todos)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // READ (uno por ID)
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // DELETE
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
