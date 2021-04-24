package com.example.Clinic.service;

import com.example.Clinic.model.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface RecipeService {

    public Recipe addRecipe(Recipe recipe);
    public List<Recipe> getAllRecipes();
    public List<Recipe> getRecipesByDate(LocalDate date);
    public void updateRecipe(Recipe recipe);
}
