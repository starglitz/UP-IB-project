package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.RecipeDao;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
public class RecipeDaoImpl implements RecipeDao {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe addRecipe(Recipe recipe) { return this.recipeRepository.save(recipe); }

    @Override
    public List<Recipe> getAllRecipes() { return this.recipeRepository.findAll(); }

    @Override
    public List<Recipe> getRecipesByDate(LocalDate date) { return this.recipeRepository.findAllById(Collections.singleton(date)); }

    @Override
    public void updateRecipe(Recipe recipe) { this.recipeRepository.save(recipe); }
}
