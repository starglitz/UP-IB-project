package com.example.Clinic.repository;

import com.example.Clinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findFirstByEmail(String email);
    @Query(value = "select * from user where email = ?1",
            nativeQuery = true)
    Optional<User> findUserByEmail(String email);

}
