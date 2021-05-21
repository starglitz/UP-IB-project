package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.rest.support.dto.ClinicAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToClinicAdmin implements Converter<ClinicAdminDto, ClinicAdmin> {

//    @Autowired
//    private ClinicAdminService clinicAdminService; TODO: add service

    @Override
    public ClinicAdmin convert(ClinicAdminDto dto) {
        ClinicAdmin clinicAdmin = new ClinicAdmin();

        clinicAdmin.setId(dto.getId());
//      dto.setUser(); TODO: convert to entity
//      dto.setClinic(); TODO: convert to entity

//        if (clinicAdmin.getId() != null) {
//            clinicAdmin = (ClinicAdmin) this.clinicAdminService.findById(clinicAdmin.getId()); TODO: add service
//        }

        if (clinicAdmin == null) {
            clinicAdmin = new ClinicAdmin();
        }

        return clinicAdmin;
    }
}
