package com.example.Clinic.rest;

import com.example.Clinic.model.Recipe;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface RecipeApi {

    @PostMapping(value = "/addRecipe",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity addRecipe(@RequestBody Recipe recipe);

    @GetMapping(value = "/recipes",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getRecipeByDate();

    @GetMapping(value = "/recipes/{date}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getRecipeByDate(@PathVariable("date") LocalDate date);
}
