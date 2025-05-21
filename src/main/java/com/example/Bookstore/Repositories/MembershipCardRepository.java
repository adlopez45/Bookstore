package com.example.Bookstore.Repositories;

import com.example.Bookstore.Models.MembershipCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipCardRepository extends JpaRepository<MembershipCard, Integer> {
    // Al usar Integer como clave primaria, evitas "No property 'id' found"
}
