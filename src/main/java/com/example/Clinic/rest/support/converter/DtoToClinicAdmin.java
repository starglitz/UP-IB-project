package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.User;
import com.example.Clinic.rest.support.dto.ClinicAdminDto;
import com.example.Clinic.service.ClinicAdminService;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DtoToClinicAdmin implements Converter<ClinicAdminDto, ClinicAdmin> {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private DtoToUser dtoToUser;

    @Autowired
    private ClinicService clinicService;

    @Override
    public ClinicAdmin convert(ClinicAdminDto dto) {
        ClinicAdmin clinicAdmin = new ClinicAdmin();

        if (clinicAdmin.getId() != null) {
            clinicAdmin = (ClinicAdmin) this.clinicAdminService.findById(clinicAdmin.getId());
        }

        if (clinicAdmin == null) {
            clinicAdmin = new ClinicAdmin();
        }

        Clinic clinic = clinicService.findById(dto.getClinic().getClinic_id());

        User user = dtoToUser.convert(dto.getUser());

        clinicAdmin.setId(user.getId());
        clinicAdmin.setClinic(clinic);
        clinicAdmin.setUser(user);

        return clinicAdmin;
    }
}
