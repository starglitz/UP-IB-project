package com.example.Clinic.rest.impl;

import com.example.Clinic.model.LoginForm;
import com.example.Clinic.rest.UserApi;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserApiImpl implements UserApi {

    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<UserRegisterDto> update(Long id, UserRegisterDto userDto) {
        if(userService.update(userDto, userDto.getPasswordValidate())) {
            return new ResponseEntity("updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("bad data", HttpStatus.BAD_REQUEST);
        }
    }
}
