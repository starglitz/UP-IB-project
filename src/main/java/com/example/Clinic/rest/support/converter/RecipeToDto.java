package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.rest.support.dto.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RecipeToDto implements Converter<Recipe, RecipeDto> {

    @Autowired
    private NurseToDto nurseToDto;

    @Override
    public RecipeDto convert(Recipe source) {

        RecipeDto retVal = new RecipeDto();
        retVal.setRecipe_id(source.getRecipe_id());
        retVal.setDescription(source.getDescription());
        retVal.setIssueDate(source.getIssueDate());
        retVal.setNurseId(source.getNurse().getId());
        retVal.setValidated(source.getValidated());
        retVal.setPatientBookId(source.getPatientBookId());

        return retVal;
    }

    public List<RecipeDto> convert(List<Recipe> source) {
        List<RecipeDto> ret = new ArrayList();
        Iterator var3 = source.iterator();

        while(var3.hasNext()) {
            Recipe z = (Recipe) var3.next();
            ret.add(this.convert(z));
        }

        return ret;
    }
}
