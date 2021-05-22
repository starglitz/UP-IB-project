package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.User;
import com.example.Clinic.rest.support.dto.UserDto;
import com.example.Clinic.rest.support.dto.UserRegisterDto;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterDtoToUser implements Converter<UserRegisterDto, User> {
    @Autowired
    private UserService userService;

    @Override
    public User convert(UserRegisterDto source) {
        User target = null;
        if (source.getId() != null) {
            target = (User)this.userService.findOne(source.getId()).get();
        }

        if (target == null) {
            target = new User();
        }

        target.setEmail(source.getEmail());
        target.setName(source.getName());
        target.setLastName(source.getLastName());
        target.setAddress(source.getAddress());
        target.setCity(source.getCity());
        target.setPassword(source.getPassword());
        target.setCountry(source.getCountry());
        target.setPhoneNumber(source.getPhoneNumber());

        return target;
    }

}
