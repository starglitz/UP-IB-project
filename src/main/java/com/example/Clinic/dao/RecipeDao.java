package com.example.Clinic.dao;

import com.example.Clinic.model.Recipe;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecipeDao {

    public Recipe addRecipe(Recipe recipe);
    public List<Recipe> getAllRecipes();
    public List<Recipe> getRecipesByDate(LocalDate date);
    public void updateRecipe(Recipe recipe);
}
