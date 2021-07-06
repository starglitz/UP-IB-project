package com.example.Clinic.service;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RecipeService {

    boolean addRecipe(Recipe recipe);
    List<Recipe> getAllRecipes();
    List<Recipe> getNotApprovedRecipes();
    boolean updateRecipe(Recipe recipe, Long recipe_id);
    Optional<Recipe> findOne(Long id);
    List<Recipe> getNurseRecipes(Nurse nurse);
}
