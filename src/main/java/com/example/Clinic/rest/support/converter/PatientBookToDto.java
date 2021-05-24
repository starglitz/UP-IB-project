package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.rest.support.dto.NurseDto;
import com.example.Clinic.rest.support.dto.PatientBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PatientBookToDto implements Converter<PatientBook, PatientBookDto> {

    @Autowired
    private PatientToDto patientToDto;

    @Override
    public PatientBookDto convert(PatientBook source) {

        PatientBookDto retVal = new PatientBookDto();
        retVal.setId(source.getId());
        retVal.setPatientDto(patientToDto.convert(source.getPatient()));

        return retVal;
    }

    public List<PatientBookDto> convert(List<PatientBook> source) {
        List<PatientBookDto> ret = new ArrayList();
        Iterator var3 = source.iterator();

        while(var3.hasNext()) {
            PatientBook z = (PatientBook) var3.next();
            ret.add(this.convert(z));
        }

        return ret;
    }
}
