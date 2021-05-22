package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Recipe;
import com.example.Clinic.model.Service;
import com.example.Clinic.rest.support.dto.RecipeDto;
import com.example.Clinic.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToRecipe implements Converter<RecipeDto, Recipe> {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private DtoToNurse dtoToNurse;

    @Override
    public Recipe convert(RecipeDto source) {
        Recipe target = null;
        if (source.getRecipe_id() != null) {
            target = (Recipe) this.recipeService.findOne(source.getRecipe_id()).get();
        }

        if (target == null) {
            target = new Recipe();
        }

        target.setDescription(source.getDescription());
        target.setIssueDate(source.getIssueDate());
        target.setNurse(dtoToNurse.convert(source.getNurseDto()));
        target.setValidated(source.isValidated());
        target.setPatientBookId(source.getPatientBookId());

        return target;
    }
}
