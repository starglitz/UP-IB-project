package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.rest.RecipeApi;
import com.example.Clinic.service.RecipeService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RecipeApiImpl implements RecipeApi {

    @Autowired
    private RecipeService recipeService;

    @Override
    public ResponseEntity addRecipe(Recipe recipe) {
        recipeService.addRecipe(recipe);
        return new ResponseEntity(new Response(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllRecipes() {
        initializeTestData();
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity(recipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getRecipeByDate(LocalDate date) {
        initializeTestData();
        List<Recipe> recipes = recipeService.getRecipesByDate(date);
        return new ResponseEntity(recipes, HttpStatus.OK);
    }

    public void initializeTestData() {
        Nurse nurse =  new Nurse("email@gmail.com", "pass123", "Clark", "Johnson",
                "Address Street 16a", "Novi Sad", "Serbia", "012345678");

        Recipe recipe1 = new Recipe(1L, false, nurse, "Opatanol x2", LocalDate.now());
        Recipe recipe2 = new Recipe(2L, false, nurse, "Opatanol x2", LocalDate.now());
        Recipe recipe3 = new Recipe(3L, false, nurse, "Opatanol x2", LocalDate.now());

        recipeService.addRecipe(recipe1);
        recipeService.addRecipe(recipe2);
        recipeService.addRecipe(recipe3);
    }
}
