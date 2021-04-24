package com.example.Clinic.repository;

import com.example.Clinic.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RecipeRepository extends JpaRepository<Recipe, LocalDate> {

}