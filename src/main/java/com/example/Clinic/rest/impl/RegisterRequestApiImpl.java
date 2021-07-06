package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.rest.RegisterRequestApi;
import com.example.Clinic.rest.support.converter.DtoToRegisterRequest;
import com.example.Clinic.rest.support.converter.PatientToListDto;
import com.example.Clinic.rest.support.converter.RegisterRequestToDto;
import com.example.Clinic.rest.support.dto.RegisterRequestDto;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class RegisterRequestApiImpl implements RegisterRequestApi {


    @Autowired
    private RegisterRequestService registerRequestService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private RegisterRequestToDto registerRequestToDto;

    @Autowired
    private DtoToRegisterRequest dtoToRegisterRequest;

    @Autowired
    private PatientToListDto patientToListDto;

    @Override
    public ResponseEntity getAllRequests() {

        List<RegisterRequest> requests = registerRequestService.getAll();
        List<RegisterRequestDto> dtos = registerRequestToDto.convert(requests);

        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateRequest(Long id,@RequestBody RegisterRequestDto request) {
        Patient patient = patientService.getPatientById(request.getPatient().getId()).orElse(null);
        if(patient == null) {
            return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
        }
        
        //request.setPatient(patientToListDto.convert(patient));
        //request.setRegister_request_id(request);

        System.out.println("API LAYER DTO REQUEST: " + request);
        RegisterRequest req = new RegisterRequest();
        req.setRegister_request_id(request.getRegister_request_id());
        req.setPatient(patient);
        req.setStatus(request.getStatus());
        req.setVisitedMail(request.isVisitedMail());

//        RegisterRequest req = dtoToRegisterRequest.convert(request);
        return new ResponseEntity(registerRequestService.update(req), HttpStatus.OK);
    }
}
