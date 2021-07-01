package com.example.Clinic.rest.impl;

import com.example.Clinic.model.User;
import com.example.Clinic.rest.UserApi;
import com.example.Clinic.rest.support.converter.UserToDto;
import com.example.Clinic.rest.support.dto.UserDto;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import com.example.Clinic.service.UserService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserApiImpl implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserToDto userToDto;


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
            UserDto userDto = userToDto.convert(user);
            return new ResponseEntity(userDto, HttpStatus.OK);
        }
        return new ResponseEntity("no user in database", HttpStatus.NOT_FOUND);
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
//        Jwt token =
//
//
//        User user = userService.getLoggedIn(authentication);
//        user.setEnabled(true);
//        User update = userService.enable(user);
        return new ResponseEntity("test", HttpStatus.OK);
    }


}
