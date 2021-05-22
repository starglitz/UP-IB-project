package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.rest.support.dto.ClinicAdminDto;
import com.example.Clinic.service.ClinicAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToClinicAdmin implements Converter<ClinicAdminDto, ClinicAdmin> {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private DtoToUser dtoToUser;

    @Autowired
    private DtoToClinic dtoToClinic;

    @Override
    public ClinicAdmin convert(ClinicAdminDto dto) {
        ClinicAdmin clinicAdmin = new ClinicAdmin();

        if (clinicAdmin.getId() != null) {
            clinicAdmin = (ClinicAdmin) this.clinicAdminService.findById(clinicAdmin.getId());
        }

        if (clinicAdmin == null) {
            clinicAdmin = new ClinicAdmin();
        }

        clinicAdmin.setId(dto.getId());
        clinicAdmin.setUser(dtoToUser.convert(dto.getUser()));
        clinicAdmin.setClinic(dtoToClinic.convert(dto.getClinic()));

        return clinicAdmin;
    }
}
