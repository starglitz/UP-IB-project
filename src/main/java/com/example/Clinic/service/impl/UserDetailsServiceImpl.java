package com.example.Clinic.service.impl;

import com.example.Clinic.model.Authority;
import com.example.Clinic.model.enumerations.UserRole;
import com.example.Clinic.model.User;
import com.example.Clinic.security.services.UserDetailsImpl;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = (User)userService.findUserByEmail(email).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        }

        else if(user.isEnabled() == false) {
            throw new DisabledException("This user is disabled");
        }

            return UserDetailsImpl.build(user);
//                return new org.springframework.security.core.userdetails.User(
//                    user.getEmail().trim(),
//                    user.getPassword().trim(),
//                    grantedAuthorities);
        }
}

