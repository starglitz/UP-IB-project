package com.example.Clinic.service.impl;

import com.example.Clinic.dao.RecipeDao;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.security.salt.BCrypt;
import com.example.Clinic.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDao recipeDao;

    @Override
    public Recipe addRecipe(Recipe recipe) { return this.recipeDao.addRecipe(recipe); }

    @Override
    public List<Recipe> getAllRecipes() { return this.recipeDao.getAllRecipes(); }

    @Override
    public List<Recipe> getNotApprovedRecipes() { return this.recipeDao.getNotApprovedRecipes(); }

    @Override
    public boolean updateRecipe(Recipe recipe, Long id) {
        boolean valid = true;

        if (recipe.getDescription().isEmpty() || recipe.getDescription() == null) { valid = false; }
        if (recipe.getIssueDate() == null) { valid = false; }
        if (recipe.getNurse() == null) { valid = false; }
        if (recipe.getRecipe_id() == null) { valid = false; }
        if (recipe.getValidated() == null) { valid = false; }

        if (valid && recipe.getRecipe_id().equals(id)) {
            recipeDao.updateRecipe(recipe, id);
        }

        return valid;
    }
}
