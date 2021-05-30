package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Authority;
import com.example.Clinic.model.User;
import com.example.Clinic.rest.support.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserToDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        UserDto retVal = new UserDto();
        retVal.setId(source.getId());
        retVal.setAddress(source.getAddress());
        retVal.setEmail(source.getEmail());
        retVal.setName(source.getName());
        retVal.setLastName(source.getLastName());
        retVal.setCity(source.getCity());
        retVal.setCountry(source.getCountry());
        retVal.setPhoneNumber(source.getPhoneNumber());
        retVal.setEnabled(source.isEnabled());

        List<String> roles = new ArrayList<>();

        for(Authority a : source.getRoles()) {
            roles.add(a.getName().toString());
        }

        retVal.setRoles(roles);

        return retVal;
    }

    public List<UserDto> convert(List<User> source) {
        List<UserDto> ret = new ArrayList();
        Iterator var3 = source.iterator();

        while(var3.hasNext()) {
            User z = (User) var3.next();
            ret.add(this.convert(z));
        }

        return ret;
    }
}
