package com.example.Clinic.rest.impl;

import com.example.Clinic.model.User;
import com.example.Clinic.rest.AuthenticationApi;
import com.example.Clinic.rest.support.dto.JwtAuthenticationRequest;
import com.example.Clinic.rest.support.dto.UserTokenState;
import com.example.Clinic.security.services.UserDetailsImpl;
import com.example.Clinic.security.token.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationApiImpl implements AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public ResponseEntity createAuthenticationToken(JwtAuthenticationRequest authenticationRequest,
                                                                    HttpServletResponse response) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String jwt = tokenUtils.generateToken(user.getUsername(), roles.toString());

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(jwt);
    }
}
