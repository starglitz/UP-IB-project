package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.rest.RecipeApi;
import com.example.Clinic.rest.support.converter.DtoToRecipe;
import com.example.Clinic.rest.support.dto.RecipeDto;
import com.example.Clinic.service.RecipeService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Component
public class RecipeApiImpl implements RecipeApi {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private DtoToRecipe dtoToRecipe;

    @Override
    public ResponseEntity<Recipe> addRecipe(@RequestBody @Valid RecipeDto dto) {
        Recipe newRecipe = dtoToRecipe.convert(dto);

        if (newRecipe != null) {
            recipeService.addRecipe(newRecipe);
            return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity approveRecipe(RecipeDto dto, Long recipe_id) {
        System.out.println(dto);
        Recipe newRecipe = dtoToRecipe.convert(dto);
        System.out.println(newRecipe);
        boolean valid = recipeService.updateRecipe(newRecipe, recipe_id);

        if(valid)
            return new ResponseEntity(newRecipe, HttpStatus.OK);

        return new ResponseEntity(newRecipe, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getAllRecipes() {
        System.out.println("recepti");
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity(recipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getNotApprovedRecipes() {
        System.out.println("ne odobreni recepti");
        List<Recipe> recipes = recipeService.getNotApprovedRecipes();
        return new ResponseEntity(recipes, HttpStatus.OK);
    }
}
