package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.model.Service;
import com.example.Clinic.rest.support.dto.RegisterRequestDto;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToRegisterRequest implements Converter<RegisterRequestDto, RegisterRequest> {

    @Autowired
    private RegisterRequestService registerRequestService;

    @Autowired
    private DtoToPatient dtoToPatient;

    @Override
    public RegisterRequest convert(RegisterRequestDto source) {
        RegisterRequest target = null;
        if (source.getRegister_request_id() != null) {
            target = (RegisterRequest) this.registerRequestService.findById(source.getRegister_request_id()).get();
        }

        if (target == null) {
            target = new RegisterRequest();
        }

        target.setPatient(dtoToPatient.convert(source.getPatientDto()));
        target.setStatus(source.getStatus());
        target.setVisitedMail(source.isVisitedMail());
        return target;
    }
}
