package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicCenterAdmin;
import com.example.Clinic.rest.support.dto.ClinicCentreAdminDto;
import com.example.Clinic.service.ClinicCentreAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToClinicCentreAdmin implements Converter<ClinicCentreAdminDto, ClinicCenterAdmin> {

    @Autowired
    private ClinicCentreAdminService clinicCentreAdminService;

    @Autowired
    private DtoToClinicCentre toEntityClinicCentre;

    @Autowired
    private DtoToUser toEntityUser;

    @Override
    public ClinicCenterAdmin convert(ClinicCentreAdminDto dto) {
        ClinicCenterAdmin clinicCenterAdmin = new ClinicCenterAdmin();

        if (dto.getId() != null) {
            clinicCenterAdmin = (ClinicCenterAdmin) this.clinicCentreAdminService.findById(dto.getId());
        }

        if (clinicCenterAdmin == null) {
            clinicCenterAdmin = new ClinicCenterAdmin();
        }

        clinicCenterAdmin.setId(dto.getId());
        clinicCenterAdmin.setClinicCenter(toEntityClinicCentre.convert(dto.getClinicCenter()));
        clinicCenterAdmin.setUser(toEntityUser.convert(dto.getUser()));

        return clinicCenterAdmin;
    }
}
