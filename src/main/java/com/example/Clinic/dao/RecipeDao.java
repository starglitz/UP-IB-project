package com.example.Clinic.dao;

import com.example.Clinic.model.Recipe;

import java.time.LocalDate;
import java.util.List;

public interface RecipeDao {

    public Recipe addRecipe(Recipe recipe);
    public List<Recipe> getAllRecipes();
    public List<Recipe> getRecipesByDate(LocalDate date);
    public void updateRecipe(Recipe recipe);
}
