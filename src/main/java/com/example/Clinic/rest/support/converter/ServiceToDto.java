package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.model.Service;
import com.example.Clinic.rest.support.dto.RegisterRequestDto;
import com.example.Clinic.rest.support.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ServiceToDto implements Converter<Service, ServiceDto> {

    @Autowired
    private ClinicToDto clinicToDto;

    @Override
    public ServiceDto convert(Service source) {
        ServiceDto retVal = new ServiceDto();
        retVal.setService_id(source.getService_id());
        retVal.setName(source.getName());
        retVal.setPrice(source.getPrice());
        retVal.setClinicDto(clinicToDto.convert(source.getClinic()));

        return retVal;
    }

    public List<ServiceDto> convert(List<Service> source) {
        List<ServiceDto> ret = new ArrayList();
        Iterator var3 = source.iterator();

        while(var3.hasNext()) {
            Service z = (Service) var3.next();
            ret.add(this.convert(z));
        }

        return ret;
    }
}
