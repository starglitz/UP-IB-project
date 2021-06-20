package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.model.User;
import com.example.Clinic.rest.support.dto.ClinicAdminDto;
import com.example.Clinic.rest.support.dto.RegisterClinicAdminDto;
import com.example.Clinic.service.ClinicAdminService;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ReqisterDtoToClinicAdmin implements Converter<RegisterClinicAdminDto, ClinicAdmin> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private RegisterDtoToUser dtoToUser;

    @Autowired
    private ClinicService clinicService;

    @Override
    public ClinicAdmin convert(RegisterClinicAdminDto dto) {
        ClinicAdmin clinicAdmin = new ClinicAdmin();

        Clinic clinic = clinicService.findById(dto.getClinic().getClinic_id());

        User user = dtoToUser.convert(dto.getUser());
        user.setPassword(passwordEncoder.encode(dto.getUser().getPassword()));
        user.setEnabled(true);

        clinicAdmin.setId(user.getId());
        clinicAdmin.setClinic(clinic);
        clinicAdmin.setUser(user);

        return clinicAdmin;
    }
}
