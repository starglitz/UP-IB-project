package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.rest.support.dto.PatientBookDto;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.security.services.AsymmetricEncription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PatientToDto implements Converter<Patient, PatientDto> {

    @Autowired
    private UserToDto userToDto;

    @Override
    public PatientDto convert(Patient source) {
        if(source != null) {
            PatientDto retVal = new PatientDto();
            retVal.setId(source.getId());
            retVal.setApproved(source.isApproved());
            retVal.setEnabled(source.isEnabled());
            retVal.setPatient_book_id(source.getPatientBookId());

//            AsymmetricEncription encription = new AsymmetricEncription(source.getLbo());
//
//            retVal.setLbo(encription.decrypt());
            retVal.setLbo(source.getLbo());
            retVal.setUserDto(userToDto.convert(source.getUser()));


            return retVal;
        }
        return null;
    }

    public List<PatientDto> convert(List<Patient> source) {
        List<PatientDto> ret = new ArrayList();
        Iterator var3 = source.iterator();

        while(var3.hasNext()) {
            Patient z = (Patient) var3.next();
            ret.add(this.convert(z));
        }

        return ret;
    }
}
