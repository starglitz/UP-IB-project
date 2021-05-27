package com.example.Clinic.service.impl;


import com.example.Clinic.model.*;
import com.example.Clinic.repository.UserRepository;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;


@Service
public class UserServiceImpl  implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean update(UserRegisterDto user, String validatePassword) {
        boolean ok = true;

        User userJpa = userRepository.findById(user.getId()).orElse(null);

        if (userJpa == null) {
            ok = false;
        } else {
            User updated = new User(user.getId(),user.getEmail(), user.getPassword(), user.getName(), user.getLastName(),
                    user.getAddress(), user.getCity(), user.getCountry(), user.getPhoneNumber());

            if (validatePassword != null && !validatePassword.equals("")) {
                if (passwordEncoder.matches(validatePassword,
                        userJpa.getPassword())) {
                    updated.setPassword(passwordEncoder.encode(user.getPassword()));
                    updated.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
                }
                else {
                    ok = false;
                }
            }
            updated.setLastPasswordResetDate(userJpa.getLastPasswordResetDate());
            userRepository.save(updated);
        }

        return ok;
    }
}
