package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.PatientBook;
import com.example.Clinic.rest.support.dto.PatientBookDto;
import com.example.Clinic.service.PatientBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToPatientBook implements Converter<PatientBookDto, PatientBook> {

    @Autowired
    private PatientBookService patientBookService;

    @Override
    public PatientBook convert(PatientBookDto source) {
        PatientBook target = null;
        if (source.getId() != null) {
            target = (PatientBook) this.patientBookService.findById(source.getId()).get();
        }

        if (target == null) {
            target = new PatientBook();
        }

//      target.setPatient(source.getPatientDto());

        return target;
    }
}
