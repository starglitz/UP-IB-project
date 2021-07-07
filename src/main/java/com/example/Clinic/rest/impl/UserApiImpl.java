package com.example.Clinic.rest.impl;

import com.example.Clinic.model.User;
import com.example.Clinic.rest.UserApi;
import com.example.Clinic.rest.support.converter.DtoToUser;
import com.example.Clinic.rest.support.converter.UserToDto;
import com.example.Clinic.rest.support.dto.FirstTimeDto;
import com.example.Clinic.rest.support.dto.UserDto;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import com.example.Clinic.service.UserService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class UserApiImpl implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserToDto userToDto;

    @Autowired
    private DtoToUser dtoToUser;



    @Override
    public ResponseEntity<UserRegisterDto> update(Long id, UserRegisterDto userDto) {
        if(userService.update(userDto, userDto.getPasswordValidate())) {
            return new ResponseEntity("updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("bad data", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getLoggedIn(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        if(user != null) {
            UserRegisterDto userDto = new UserRegisterDto();
            userDto.setAddress(user.getAddress());
            userDto.setEnabled(user.isEnabled());
            userDto.setCity(user.getCity());
            userDto.setId(user.getId());
            userDto.setPassword("");
            userDto.setCountry(user.getCountry());
            userDto.setEmail(user.getEmail());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setName(user.getName());
            userDto.setLastName(user.getLastName());
            userDto.setPasswordValidate("");
            return new ResponseEntity(userDto, HttpStatus.OK);
        }
        return new ResponseEntity("no user in database", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity getLoggedInUserId(String email) {
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        System.out.println(email);
        return new ResponseEntity<>(userToDto.convert(userService.getByEmail(email)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAll() {
       return new ResponseEntity(userToDto.convert(userService.getAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getUser(Long id) {
        User user = userService.findOne(id).orElse(null);
        System.out.println(user);
        if(user == null) {
            return new ResponseEntity("no user in database", HttpStatus.NOT_FOUND);
        }
        UserDto dto = userToDto.convert(user);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity enable(String token) {

        return new ResponseEntity("test", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserRegisterDto> changePassword(Long id, @Valid UserRegisterDto user) {
        System.out.println(user);
        if(userService.changePassword(user, user.getPasswordValidate())) {
            return new ResponseEntity("updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("bad data", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity firstTime(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        FirstTimeDto dto = new FirstTimeDto(user.isFirstTime());

        if(user.isFirstTime()) {
            user.setFirstTime(false);
            userService.setFirstTime(user);
        }

        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> updateProfile(Long id, @Valid UserDto user) {
        User update = userService.updateProfile(dtoToUser.convert(user));
        return new ResponseEntity(user, HttpStatus.OK);
    }


}
