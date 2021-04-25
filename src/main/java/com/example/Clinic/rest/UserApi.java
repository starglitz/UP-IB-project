package com.example.Clinic.rest;

import com.example.Clinic.model.LoginForm;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface UserApi {

    @PostMapping(value = "/loginData",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity checkUserLogin(@RequestBody LoginForm loginForm);


}