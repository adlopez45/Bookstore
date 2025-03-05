package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);
    List<User> findAll();
}