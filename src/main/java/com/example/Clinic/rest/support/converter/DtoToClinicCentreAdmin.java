package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicCenterAdmin;
import com.example.Clinic.rest.support.dto.ClinicCentreAdminDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToClinicCentreAdmin implements Converter<ClinicCentreAdminDto, ClinicCenterAdmin> {

//    @Autowired
//    private ClinicCentreAdminService clinicCentreAdminService; TODO: add service

    @Override
    public ClinicCenterAdmin convert(ClinicCentreAdminDto dto) {
        ClinicCenterAdmin clinicCenterAdmin = new ClinicCenterAdmin();

        clinicCenterAdmin.setId(dto.getId());

//        dto.setClinicCenter();TODO: convert to entity
//        dto.setUser();TODO: convert to entity

//        if (clinicCenterAdmin.getId() != null) {
//            clinicCenterAdmin = (ClinicCenterAdmin) this.clinicCentreAdminService.findById(clinicCenterAdmin.getId()); TODO: add service
//        }

        if (clinicCenterAdmin == null) {
            clinicCenterAdmin = new ClinicCenterAdmin();
        }

        return clinicCenterAdmin;
    }
}
