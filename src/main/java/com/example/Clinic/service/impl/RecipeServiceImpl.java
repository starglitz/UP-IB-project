package com.example.Clinic.service.impl;

import com.example.Clinic.dao.RecipeDao;
import com.example.Clinic.model.Recipe;
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
    public List<Recipe> getRecipesByDate(LocalDate date) { return this.recipeDao.getRecipesByDate(date); }

    @Override
    public void updateRecipe(Recipe recipe) { recipeDao.updateRecipe(recipe); }
}
