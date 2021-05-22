package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Patient;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.rest.support.dto.PatientListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PatientToListDto implements Converter<Patient, PatientListDto> {



    @Override
    public PatientListDto convert(Patient source) {

        PatientListDto retVal = new PatientListDto();
        retVal.setId(source.getId());
        retVal.setApproved(source.isApproved());
        retVal.setEnabled(source.isEnabled());
        retVal.setLbo(source.getLbo());
        retVal.setAddress(source.getUser().getAddress());
        retVal.setCity(source.getUser().getCity());
        retVal.setCountry(source.getUser().getCountry());
        retVal.setEmail(source.getUser().getEmail());
        retVal.setLastName(source.getUser().getName());
        retVal.setName(source.getUser().getName());
        retVal.setPhoneNumber(source.getUser().getPhoneNumber());

        return retVal;
    }

    public List<PatientListDto> convert(List<Patient> source) {
        List<PatientListDto> ret = new ArrayList();
        Iterator var3 = source.iterator();

        while(var3.hasNext()) {
            Patient z = (Patient) var3.next();
            ret.add(this.convert(z));
        }

        return ret;
    }

}
