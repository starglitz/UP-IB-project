package com.example.Clinic.service.impl;

import com.example.Clinic.dao.RecipeDao;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDao recipeDao;

    @Override
    public  boolean addRecipe(Recipe recipe) {
        boolean valid = checkValid(recipe);

        if (valid) {
            recipeDao.addRecipe(recipe);
        }

        return valid;
    }

    @Override
    public boolean updateRecipe(Recipe recipe, Long id) {
        boolean valid = checkValid(recipe);

        if (valid && recipe.getRecipe_id().equals(id)) {
            recipeDao.updateRecipe(recipe, id);
        }

        return valid;
    }

    @Override
    public List<Recipe> getAllRecipes() { return this.recipeDao.getAllRecipes(); }

    @Override
    public List<Recipe> getNotApprovedRecipes() { return this.recipeDao.getNotApprovedRecipes(); }

    private boolean checkValid(Recipe recipe) {
        boolean valid = true;

        if (recipe.getDescription().isEmpty() || recipe.getDescription() == null) { valid = false; }
        if (recipe.getIssueDate() == null) { valid = false; }
        if (recipe.getNurse() == null) { valid = false; }
        if (recipe.getRecipe_id() == null) { valid = false; }
        if (recipe.getValidated() == null) { valid = false; }

        return valid;
    }
}
