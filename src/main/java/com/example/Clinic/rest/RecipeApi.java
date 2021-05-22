package com.example.Clinic.rest;

import com.example.Clinic.model.Recipe;
import com.example.Clinic.rest.support.dto.RecipeDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/recipes")
public interface RecipeApi {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Recipe> addRecipe(@Valid @RequestBody RecipeDto recipe);

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllRecipes();

    @GetMapping(value = "/notApproved",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNotApprovedRecipes();

    @PutMapping(value = "/approve/{recipe_id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Recipe> approveRecipe(@Valid @RequestBody RecipeDto recipe,
                                         @PathVariable("recipe_id") Long recipe_id);
}
