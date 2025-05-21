package com.example.Bookstore.Services;

import com.example.Bookstore.DTO.UserDTO;
import com.example.Bookstore.Models.User;
import com.example.Bookstore.Repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserDTO findUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        // Forzar la carga de relaciones si usas Lazy loading
        Hibernate.initialize(user.getCity());
        Hibernate.initialize(user.getCountry());
        Hibernate.initialize(user.getRole());
        Hibernate.initialize(user.getProfession());
        Hibernate.initialize(user.getGender());
        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            Hibernate.initialize(user.getCity());
            Hibernate.initialize(user.getCountry());
            Hibernate.initialize(user.getRole());
            Hibernate.initialize(user.getProfession());
            Hibernate.initialize(user.getGender());
        });
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO updateUser(Integer userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

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
        // Si se desean actualizar relaciones, se puede incluir código similar:
        // if(updatedUser.getCity() != null && updatedUser.getCity().getCityId() != null) {
        //     existingUser.setCity(updatedUser.getCity());
        // }
        userRepository.save(existingUser);

        // Forzar la carga de relaciones
        Hibernate.initialize(existingUser.getCity());
        Hibernate.initialize(existingUser.getCountry());
        Hibernate.initialize(existingUser.getRole());
        Hibernate.initialize(existingUser.getProfession());
        Hibernate.initialize(existingUser.getGender());

        return new UserDTO(existingUser);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
