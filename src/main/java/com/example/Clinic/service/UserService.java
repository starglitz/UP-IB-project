package com.example.Clinic.service;

import com.example.Clinic.model.User;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findUserByEmail(String email);

    Optional<User> findOne(Long id);

    boolean update(UserRegisterDto user, String validatePassword);

    User getLoggedIn(Authentication authentication);

    List<User> getAll();

    User enable(User user);

    Boolean changePassword(UserRegisterDto user, String passwordValidate);

    User setFirstTime(User user);

    User getByEmail(String email);

    User updateProfile(User user);
}
