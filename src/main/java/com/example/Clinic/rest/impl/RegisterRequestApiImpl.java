package com.example.Clinic.rest.impl;

import com.example.Clinic.model.Patient;
import com.example.Clinic.model.RegisterRequest;
import com.example.Clinic.model.RequestStatus;
import com.example.Clinic.rest.RegisterRequestApi;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class RegisterRequestApiImpl implements RegisterRequestApi {


    @Autowired
    private RegisterRequestService registerRequestService;

    @Autowired
    private PatientService patientService;

    @Override
    public ResponseEntity getAllRequests() {


        // <test data>

//        Patient patient = new Patient("email first patient", "pass first patient", "name first",
//                "lastname first",
//                "address first", "city first", "country first", "pn first",
//                "lbo first");
//
//        Patient patient2 = new Patient("email 2 patient", "pass 2 patient", "name 2",
//                "lastname 2",
//                "address 2", "city 2", "country f2", "pn 2",
//                "lbo 2");
//
//        patientService.addPatient(patient);
//        patientService.addPatient(patient2);
//
//        RegisterRequest req = new RegisterRequest(patient, RequestStatus.PENDING, false);
//        RegisterRequest req2 = new RegisterRequest(patient2, RequestStatus.PENDING, false);
//
//        registerRequestService.addRegisterRequest(req);
//        registerRequestService.addRegisterRequest(req2);

        // </test data>

        return new ResponseEntity(registerRequestService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RegisterRequest> updateRequest(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(registerRequestService.update(request), HttpStatus.OK);
    }
}
