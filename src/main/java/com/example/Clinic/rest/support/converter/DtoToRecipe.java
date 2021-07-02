package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.model.Service;
import com.example.Clinic.rest.support.dto.RecipeDto;
import com.example.Clinic.service.NurseService;
import com.example.Clinic.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToRecipe implements Converter<RecipeDto, Recipe> {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private DtoToNurse dtoToNurse;

    @Override
    public Recipe convert(RecipeDto source) {
        Recipe target = new Recipe();

        if (target == null) {
            target = new Recipe();
        }
        System.out.println(source);

        Nurse nurse = nurseService.findById(source.getNurseId());
        target.setNurse(nurse);

        target.setRecipe_id(source.getRecipe_id());
        target.setDescription(source.getDescription());
        target.setIssueDate(source.getIssueDate());
        target.setValidated(source.isValidated());
        target.setPatientBookId(source.getPatientBookId());
        System.out.println(target);

        return target;
    }
}
