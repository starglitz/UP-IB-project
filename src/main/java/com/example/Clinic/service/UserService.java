package com.example.Clinic.service;

import com.example.Clinic.model.User;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findUserByEmail(String email);

    Optional<User> findOne(Long id);

    boolean update(UserRegisterDto user, String validatePassword);
}
