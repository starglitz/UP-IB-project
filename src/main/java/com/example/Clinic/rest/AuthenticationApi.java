package com.example.Clinic.rest;


import com.example.Clinic.rest.support.dto.JwtAuthenticationRequest;
import com.example.Clinic.rest.support.dto.UserTokenState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public interface AuthenticationApi {

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest
                                                             authenticationRequest, HttpServletResponse response);

    @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request);
}
