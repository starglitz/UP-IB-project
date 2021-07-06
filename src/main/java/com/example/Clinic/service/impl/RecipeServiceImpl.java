package com.example.Clinic.service.impl;


import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.repository.RecipeRepository;
import com.example.Clinic.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {


    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public  boolean addRecipe(Recipe recipe) {
        boolean valid = checkValid(recipe);

        if (valid) {
            recipeRepository.save(recipe);
        }

        return valid;
    }

    @Override
    public boolean updateRecipe(Recipe recipe, Long id) {
        boolean valid = checkValid(recipe);

        if (valid && recipe.getRecipe_id().equals(id)) {
            recipeRepository.save(recipe);
        }

        return valid;
    }

    @Override
    public Optional<Recipe> findOne(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public List<Recipe> getNurseRecipes(Nurse nurse) {
        return recipeRepository.findRecipeByNurseAndValidatedIsFalse(nurse);
    }

    @Override
    public List<Recipe> getAllRecipes() { return this.recipeRepository.findAll(); }

    @Override
    public List<Recipe> getNotApprovedRecipes() { return this.recipeRepository.findNotApproved(); }

    private boolean checkValid(Recipe recipe) {
        boolean valid = true;

        if (recipe.getDescription().isEmpty() && recipe.getDescription() == null) { valid = false; }
        if (recipe.getIssueDate() == null) { valid = false; }
        if (recipe.getNurse() == null) { valid = false; }
        if (recipe.getValidated() == null) { valid = false; }

        return valid;
    }
}
