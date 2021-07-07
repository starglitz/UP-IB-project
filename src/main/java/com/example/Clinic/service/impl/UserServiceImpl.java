package com.example.Clinic.service.impl;


import com.example.Clinic.model.*;
import com.example.Clinic.repository.UserRepository;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
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
        System.out.println(userRepository.findById(id));
        return userRepository.findById(id);
    }

    @Override
    public boolean update(UserRegisterDto user, String validatePassword) {
        boolean ok = true;

        User userJpa = userRepository.findById(user.getId()).orElse(null);

        if (userJpa == null) {
            ok = false;
        } else {

            if(!user.isEnabled()) {
                userJpa.setEnabled(false);
                userRepository.save(userJpa);
                return ok;
            }

            else {


                User updated = new User(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getLastName(),
                        user.getAddress(), user.getCity(), user.getCountry(), user.getPhoneNumber());

                if (validatePassword != null && !validatePassword.equals("")) {
                    if (passwordEncoder.matches(validatePassword,
                            userJpa.getPassword())) {
                        updated.setPassword(passwordEncoder.encode(user.getPassword()));
                        updated.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
                        updated.setEnabled(userJpa.isEnabled());
                        updated.setRoles(userJpa.getRoles());
                        userRepository.save(updated);
                        return ok;
                    } else {
                        ok = false;
                        updated.setPassword(userJpa.getPassword());
                        updated.setLastPasswordResetDate(userJpa.getLastPasswordResetDate());
                        updated.setEnabled(userJpa.isEnabled());
                        updated.setRoles(userJpa.getRoles());
                        userRepository.save(updated);
                        return ok;
                    }
                }
                updated.setLastPasswordResetDate(userJpa.getLastPasswordResetDate());
                updated.setPassword(userJpa.getPassword());
                updated.setEnabled(true);
                updated.setRoles(userJpa.getRoles());
                userRepository.save(updated);
            }
        }

        return ok;
    }

    @Override
    public User getLoggedIn(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails)authentication.getPrincipal();
        System.out.println("TRENUTNI ULOGOVANI usernname =" + userPrincipal.getUsername());
        String username = userPrincipal.getUsername();

        User user = userRepository.findUserByEmail(userPrincipal.getUsername()).orElse(null);
        return user;

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User enable(User user) {
        User userJpa = userRepository.findById(user.getId()).orElse(null);
        userJpa.setEnabled(true);
        userRepository.save(userJpa);

        return userJpa;
    }

    @Override
    public Boolean changePassword(UserRegisterDto user, String validatePassword) {

        boolean ok = true;

        User userJpa = userRepository.findById(user.getId()).orElse(null);

        if (validatePassword != null && !validatePassword.equals("")) {
            System.out.println("Password is not null or empty");
            System.out.println("User DTO: " + user);
            System.out.println("User JPA: " + userJpa);
            if (passwordEncoder.matches(validatePassword,
                    userJpa.getPassword())) {
                System.out.println("Password matches & will be edited");
                userJpa.setPassword(passwordEncoder.encode(user.getPassword()));
                userJpa.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
                userRepository.save(userJpa);
            } else {
                ok = false;
            }
        }
        else {
            ok = false;
        }
        return ok;
    }

    @Override
    public User setFirstTime(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> user = userRepository.findFirstByEmail(email);
        return user.orElse(null);
    }

    @Override
    public User updateProfile(User user) {
        User jpa = userRepository.findById(user.getId()).orElse(null);
        jpa.setAddress(user.getAddress());
        jpa.setCity(user.getCity());
        jpa.setCountry(user.getCountry());
        jpa.setName(user.getName());
        jpa.setLastName(user.getLastName());
        jpa.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(jpa);
        return jpa;
    }
}
