package com.example.Clinic.service.impl;

import com.example.Clinic.model.*;
import com.example.Clinic.model.enumerations.RequestStatus;
import com.example.Clinic.repository.RegisterRequestRepository;
import com.example.Clinic.security.token.TokenUtils;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.RegisterRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class RegisterRequestServiceImpl implements RegisterRequestService {


    @Autowired
    private RegisterRequestRepository registerRequestRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public RegisterRequest addRegisterRequest(RegisterRequest request) {
        return registerRequestRepository.save(request);
    }

    @Override
    public List<RegisterRequest> getAll() {
        return  new ArrayList<RegisterRequest>(registerRequestRepository.findAll());
    }

    @Override
    public RegisterRequest findByPatientId(Long id) {
        return registerRequestRepository.findByPatientId(id);
    }


    @Override
    public RegisterRequest update(RegisterRequest request) {

        System.out.println("Request service layer called");
        RegisterRequest requestJpa = findById(request.getRegister_request_id()).get();
        System.out.println("Request update: " + request);
        System.out.println("Request in DB: " + requestJpa);

        User user = requestJpa.getPatient().getUser();

        if(requestJpa.getStatus().equals(RequestStatus.PENDING)) {
            System.out.println("guesssss");

            Patient patient = requestJpa.getPatient();
            patient.setVisitedMail(false);
            patientService.updatePatient(patient, patient.getId());

            if(request.getStatus().equals(RequestStatus.APPROVED)) {


                List<String> roles = new ArrayList<>();
                for (Authority one : user.getRoles()) {
                    roles.add(one.getName().toString());
                }

                System.out.println("USER: " + user);
                String jwt = tokenUtils.generateToken(user.getEmail(), roles.toString());
//                TokenExpiration tokenExpiration = new TokenExpiration();
//                tokenExpiration.setToken(jwt);
//                tokenExpiration.setTime(LocalDateTime.now());



                System.out.println("ITS APPROVED");
                String link = "http://localhost:3000/magicWithLink/?token=" + jwt;
                emailService.sendEmail(requestJpa.getPatient().getUser().getEmail(),
                        "Your register request has been approved",
                        "To log in, you need to visit the given link:" + link);
            }
            else if(request.getStatus().equals(RequestStatus.DECLINED)) {
                System.out.println("ITS DENIED");
                emailService.sendEmail(requestJpa.getPatient().getUser().getEmail(),
                        "Your register request is denied",
                        "We are sorry to inform you your registering request has been denied by clinic center admin");
            }
        }

        requestJpa.setStatus(request.getStatus());
        requestJpa.setVisitedMail(request.isVisitedMail());
        return registerRequestRepository.save(requestJpa);
    }

    @Override
    public Optional<RegisterRequest> findById(Long id) {
        return registerRequestRepository.findById(id);
    }


}
