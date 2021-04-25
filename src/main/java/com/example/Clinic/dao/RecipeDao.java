package com.example.Clinic.dao;

import com.example.Clinic.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeDao {

    Recipe addRecipe(Recipe recipe);
    List<Recipe> getAllRecipes();
    List<Recipe> getNotApprovedRecipes();
    void updateRecipe(Recipe recipe, Long id);
}
