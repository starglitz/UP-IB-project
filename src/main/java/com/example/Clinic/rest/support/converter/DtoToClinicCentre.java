package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.ClinicAdmin;
import com.example.Clinic.model.ClinicCenter;
import com.example.Clinic.rest.support.dto.ClinicCentreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToClinicCentre implements Converter<ClinicCentreDto, ClinicCenter> {

//    @Autowired
//    private ClinicCentreService clinicCentreService; TODO: add service

    @Override
    public ClinicCenter convert(ClinicCentreDto dto) {
        ClinicCenter clinicCenter = new ClinicCenter();

        clinicCenter.setClinic_center_id(dto.getClinic_center_id());
        clinicCenter.setName(dto.getName());
//        dto.setClinics(); TODO: convert to entity
        clinicCenter.setAddress(dto.getAddress());

//        if (clinicCenter.getClinic_center_id() != null) {
//            clinicCenter = (ClinicCenter) this.clinicCentreService.findById(clinicCenter.getId()); TODO: add service
//        }

        if (clinicCenter == null) {
            clinicCenter = new ClinicCenter();
        }

        return clinicCenter;
    }
}
