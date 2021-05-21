package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Patient;
import com.example.Clinic.rest.support.dto.NurseDto;
import com.example.Clinic.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToNurse implements Converter<NurseDto, Nurse> {


    @Autowired
    private NurseService nurseService;

    @Override
    public Nurse convert(NurseDto source) {
        Nurse target = null;
        if (source.getId() != null) {
            target = (Nurse) this.nurseService.findById(source.getId()).get();
        }

        if (target == null) {
            target = new Nurse();
        }

//        target.setClinic(source.getClinicDto());
//        target.setUser(source.getUserDto());

        return target;
    }
}
