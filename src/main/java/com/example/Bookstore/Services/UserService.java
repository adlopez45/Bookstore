package com.example.Bookstore.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.Bookstore.Models.User;
import com.example.Bookstore.Repositories.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(Integer userId, User updatedUser) {
        return userRepository.findById(userId)
            .map(existingUser -> {
                // Actualiza solo los campos que se envían en la petición
                if (updatedUser.getFirstName() != null) {
                    existingUser.setFirstName(updatedUser.getFirstName());
                }
                if (updatedUser.getLastName() != null) {
                    existingUser.setLastName(updatedUser.getLastName());
                }
                if (updatedUser.getEmail() != null) {
                    existingUser.setEmail(updatedUser.getEmail());
                }
                if (updatedUser.getAge() != null) {
                    existingUser.setAge(updatedUser.getAge());
                }
                if (updatedUser.getStatus() != null) {
                    existingUser.setStatus(updatedUser.getStatus());
                }

                return userRepository.save(existingUser);
            })
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
    }

    public User registerUser(User user) {

        return userRepository.save(user);
    }


    public List<User> findAll() {
       System.out.println("entraste a repositorio de usuario");
        return userRepository.findAll();
    }

  
    public User findById(Integer id) {
        System.out.println("entraste a findbyId de usuario");
        return userRepository.findById(id).orElse(null);
    }


    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
