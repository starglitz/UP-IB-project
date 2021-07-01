package com.example.Clinic.rest;


import com.example.Clinic.model.TokenPair;
import com.example.Clinic.rest.support.dto.JwtAuthenticationRequest;
import com.example.Clinic.rest.support.dto.PasswordLessDto;
import com.example.Clinic.rest.support.dto.UserTokenState;
import net.minidev.json.parser.ParseException;
import org.json.JSONException;
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
    @PostMapping("/passwordless")
    public ResponseEntity<String> passwordLessRequest(@RequestBody PasswordLessDto dto, HttpServletResponse response);

    @GetMapping("/linkLogin/{token}")
    public ResponseEntity loginViaLink(@PathVariable("token") String token) throws ParseException, JSONException;

    @GetMapping("/magic/{token}")
    public ResponseEntity<TokenPair> tokenCheck(@PathVariable("token") String token);

    @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request);
}
