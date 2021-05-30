package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.PatientBook;
import com.example.Clinic.rest.support.dto.PatientBookDto;
import com.example.Clinic.service.PatientBookService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToPatientBook implements Converter<PatientBookDto, PatientBook> {

    @Autowired
    private PatientBookService patientBookService;

    @Autowired
    private DtoToPatient dtoToPatient;

    @SneakyThrows
    @Override
    public PatientBook convert(PatientBookDto source) {
        PatientBook target = null;
        if (source.getId() != null) {
            target = this.patientBookService.findById(source.getId());
        }

        if (target == null) {
            target = new PatientBook();
        }

      target.setPatient(dtoToPatient.convert(source.getPatientDto()));

        return target;
    }
}
