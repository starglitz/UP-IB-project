package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.Patient;
import com.example.Clinic.rest.support.dto.NurseDto;
import com.example.Clinic.service.ClinicService;
import com.example.Clinic.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToNurse implements Converter<NurseDto, Nurse> {


    @Autowired
    private NurseService nurseService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DtoToUser dtoToUser;

    @Autowired
    private DtoToClinic dtoToClinic;

    @Override
    public Nurse convert(NurseDto source) {
        Nurse target = null;
        if (source.getId() != null) {
            target = (Nurse) this.nurseService.findById(source.getId());
        }

        if (target == null) {
            target = new Nurse();
        }
        if(source.getClinic() != null) {
            Clinic clinic = clinicService.findById(source.getClinic().getClinic_id());
            target.setClinic(clinic);
            target.setClinic(dtoToClinic.convert(source.getClinic()));
        }

        target.setUser(dtoToUser.convert(source.getUser()));

        return target;
    }
}
