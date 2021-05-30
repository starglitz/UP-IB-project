package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Service;
import com.example.Clinic.rest.support.dto.ServiceDto;
import com.example.Clinic.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToService implements Converter<ServiceDto, Service> {

    @Autowired
    private DtoToClinic dtoToClinic;

    @Autowired
    private ServiceService serviceService;

    @Override
    public Service convert(ServiceDto source) {
        Service target = null;
        if (source.getService_id() != null) {
            target = (Service) this.serviceService.findOne(source.getService_id()).get();
        }

        if (target == null) {
            target = new Service();
        }

        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setClinic(dtoToClinic.convert(source.getClinicDto()));

        return target;
    }
}
