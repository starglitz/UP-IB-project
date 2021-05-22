package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Recipe;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.rest.support.dto.RecipeDto;
import com.example.Clinic.rest.support.dto.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RegisterRequestToDto implements Converter<RegisterRequest, RegisterRequestDto> {



    @Autowired
    private PatientToListDto patientToDto;

    @Override
    public RegisterRequestDto convert(RegisterRequest source) {

        RegisterRequestDto retVal = new RegisterRequestDto();
        retVal.setRegister_request_id(source.getRegister_request_id());
        retVal.setStatus(source.getStatus());
        retVal.setVisitedMail(source.isVisitedMail());
        retVal.setPatient(patientToDto.convert(source.getPatient()));

        return retVal;
    }

    public List<RegisterRequestDto> convert(List<RegisterRequest> source) {
        List<RegisterRequestDto> ret = new ArrayList();
        Iterator var3 = source.iterator();

        while(var3.hasNext()) {
            RegisterRequest z = (RegisterRequest) var3.next();
            ret.add(this.convert(z));
        }

        return ret;
    }
}
