package com.example.Clinic.repository;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r where r.validated = false")
    List<Recipe> findNotApproved();

    List<Recipe> findRecipeByNurseAndValidatedIsFalse(@NotNull Nurse nurse);
}