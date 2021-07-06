package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.Recipe;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.security.services.AsymmetricEncription;
import com.example.Clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToPatient implements Converter<PatientDto, Patient> {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DtoToUser dtoToUser;

    @Override
    public Patient convert(PatientDto source) {
        Patient target = null;
        if (source.getId() != null) {
            target = (Patient) this.patientService.getPatientById(source.getId()).get();
        }

        if (target == null) {
            target = new Patient();
        }

        target.setApproved(source.isApproved());
        target.setEnabled(source.isEnabled());

        target.setLbo(source.getLbo());
        //target.setPatientBookId(source.getPatientBookId());
        target.setUser(dtoToUser.convert(source.getUserDto()));

        return target;
    }
}
