package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.model.ClinicCenter;
import com.example.Clinic.rest.support.dto.ClinicCentreDto;
import com.example.Clinic.service.ClinicCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToClinicCentre implements Converter<ClinicCentreDto, ClinicCenter> {

    @Autowired
    private ClinicCentreService clinicCentreService;

    @Autowired
    private DtoToClinic dtoToClinic;

    @Override
    public ClinicCenter convert(ClinicCentreDto dto) {
        ClinicCenter clinicCenter = new ClinicCenter();

        if (clinicCenter.getClinic_center_id() != null) {
            clinicCenter = (ClinicCenter) this.clinicCentreService.findById(clinicCenter.getClinic_center_id()).get();
        }

        if (clinicCenter == null) {
            clinicCenter = new ClinicCenter();
        }

        clinicCenter.setClinic_center_id(dto.getClinic_center_id());
        clinicCenter.setName(dto.getName());
        clinicCenter.setClinics(dtoToClinic.convertList(dto.getClinics()));
        clinicCenter.setAddress(dto.getAddress());

        return clinicCenter;
    }
}
