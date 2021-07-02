package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.rest.RecipeApi;
import com.example.Clinic.rest.support.converter.DtoToRecipe;
import com.example.Clinic.rest.support.dto.RecipeDto;
import com.example.Clinic.service.PatientBookService;
import com.example.Clinic.service.RecipeService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeApiImpl implements RecipeApi {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private DtoToRecipe dtoToRecipe;

    @Autowired
    private PatientBookService patientBookService;

    @Override
    public ResponseEntity<Recipe> addRecipe(@RequestBody @Valid RecipeDto dto) {
        Recipe newRecipe = dtoToRecipe.convert(dto);

        if (newRecipe != null) {
            recipeService.addRecipe(newRecipe);
            return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity approveRecipe(RecipeDto dto, Long recipe_id) throws ParserConfigurationException, SAXException, IOException {
        Recipe newRecipe = dtoToRecipe.convert(dto);
        boolean valid = recipeService.updateRecipe(newRecipe, recipe_id);

        if(valid) {
            assert newRecipe != null;
            PatientBook patientBook = patientBookService.findById(newRecipe.getPatientBookId());
            if (patientBook != null) {
                System.out.println("PATIENT BOOK: " + patientBook.getIllnessHistory());
                System.out.println("NEW DRUG: " + newRecipe.getDescription());
                List<String> drugs =  new ArrayList<String>((patientBook.getDrugs()));
                String newDrug = newRecipe.getDescription();
                drugs.add(newDrug);
                patientBook.setDrugs(drugs);
                patientBookService.updatePatientBook(patientBook, patientBook.getId());
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
            return new ResponseEntity<>(newRecipe, HttpStatus.OK);
        }
        return new ResponseEntity<>(newRecipe, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getAllRecipes() {
        System.out.println("recepti");
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity(recipes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getNotApprovedRecipes() {
        System.out.println("ne odobreni recepti");
        List<Recipe> recipes = recipeService.getNotApprovedRecipes();
        return new ResponseEntity(recipes, HttpStatus.OK);
    }
}
