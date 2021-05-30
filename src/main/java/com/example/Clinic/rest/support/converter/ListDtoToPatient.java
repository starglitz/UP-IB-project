package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.User;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.rest.support.dto.PatientListDto;
import com.example.Clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ListDtoToPatient implements Converter<PatientListDto, Patient> {

    @Autowired
    private PatientService patientService;


    @Override
    public Patient convert(PatientListDto source) {
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

        User user = new User();
        user.setAddress(source.getAddress());
        user.setCity(source.getCity());
        user.setCountry(source.getCountry());
        user.setEmail(source.getEmail());
        user.setLastName(source.getLastName());
        user.setName(source.getName());
        user.setPhoneNumber(source.getPhoneNumber());
        //target.setPatientBookId(source.getPatientBookId());
        target.setUser(user);

        return target;
    }
}
