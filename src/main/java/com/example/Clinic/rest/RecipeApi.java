package com.example.Clinic.rest;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.Recipe;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public interface RecipeApi {

    @PostMapping(value = "/addRecipe",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity addRecipe(@RequestBody Recipe recipe);

    @GetMapping(value = "/allRecipes",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllRecipes();

    @GetMapping(value = "/notApprovedRecipes",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNotApprovedRecipes();

    @PutMapping(value = "/updateRecipe/{recipe_id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Recipe> approveRecipe(@Valid @RequestBody Recipe recipe, @PathVariable("recipe_id") Long recipe_id);
}
