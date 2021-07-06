package com.example.Clinic.rest;

import com.example.Clinic.model.Recipe;
import com.example.Clinic.rest.support.dto.RecipeDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/recipes")
public interface RecipeApi {

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'CLINIC_ADMIN', 'CLINIC_CENTRE_ADMIN')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Recipe> addRecipe(@Valid @RequestBody RecipeDto recipe);

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllRecipes();

    @PreAuthorize("hasAuthority('NURSE')")
    @GetMapping(value = "/nurse/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNurseRecipes(@PathVariable("id") Long id);

    @PreAuthorize("hasAuthority('NURSE')")
    @GetMapping(value = "/notApproved",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getNotApprovedRecipes();

    @PreAuthorize("hasAuthority('NURSE')")
    @PutMapping(value = "/approve/{recipe_id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Recipe> approveRecipe(@RequestBody RecipeDto recipe,
                                         @PathVariable("recipe_id") Long recipe_id) throws ParserConfigurationException, SAXException, IOException;
}
