package com.example.Clinic.rest.impl;

import com.example.Clinic.model.*;
import com.example.Clinic.model.enumerations.RequestStatus;
import com.example.Clinic.model.enumerations.UserRole;
import com.example.Clinic.repository.AuthorityRepository;
import com.example.Clinic.rest.PatientApi;
import com.example.Clinic.rest.support.converter.DtoToPatient;
import com.example.Clinic.rest.support.converter.PatientToDto;
import com.example.Clinic.rest.support.converter.RegisterDtoToPatient;
import com.example.Clinic.rest.support.dto.PatientDto;
import com.example.Clinic.rest.support.dto.PatientRegisterDto;
import com.example.Clinic.service.PatientBookService;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.RegisterRequestService;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.SAXException;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PatientApiImpl implements PatientApi {

    @Autowired
    private PatientService patientService;

    @Autowired
    private RegisterRequestService registerRequestService;

    @Autowired
    private PatientBookService patientBookService;

    @Autowired
    private PatientToDto patientToDto;

    @Autowired
    private DtoToPatient dtoToPatient;

    @Autowired
    private RegisterDtoToPatient registerDtoToPatient;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity registerUser(@RequestBody @Valid PatientRegisterDto patientDto) throws ParserConfigurationException, SAXException, IOException {
        System.out.println(patientDto);

        User user = new User(patientDto.getUserDto().getEmail(), passwordEncoder.encode(patientDto.getUserDto().getPassword()),
                patientDto.getUserDto().getName(), patientDto.getUserDto().getLastName(),
                patientDto.getUserDto().getAddress(), patientDto.getUserDto().getCity(), patientDto.getUserDto().getCountry(),
                patientDto.getUserDto().getPhoneNumber());

        user.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
        user.setEnabled(false);
        user.setFirstTime(true);

        Set<Authority> authorities = new HashSet<>(){{
            add(authorityRepository.findByName(UserRole.PATIENT.toString()));
        }};

        user.setRoles(authorities);
      //  Patient patient = registerDtoToPatient.convert(patientDto);
//        @NotEmpty(message = "last name is mandatory")
//        @NotBlank(message = "cant be blank")
//        @NotNull(message = "cant be null") String lbo,
//        boolean enabled, boolean approved, User user)
        Patient patient = new Patient(patientDto.getLbo(), true, false, user);


        Patient patientJpa = patientService.addPatient(patient);


        RegisterRequest request = new RegisterRequest(patientJpa, RequestStatus.PENDING, false);
        PatientBook book = new PatientBook();
        book.setPatient(patientJpa);
        patientBookService.addPatientBook(book);
        registerRequestService.addRegisterRequest(request);

        patientJpa.setPatientBookId(book.getId());
        patientService.updatePatient(patientJpa, patientJpa.getId());
        System.out.println(patientJpa);
        return new ResponseEntity<>(patient, HttpStatus.OK);

    }



    @Override
    public ResponseEntity getAllPatients() {
        List<Patient> patients = patientService.getAll();
        return new ResponseEntity(patients, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getLogged(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        Patient patient = patientService.getPatientById(user.getId()).orElse(null);
        return new ResponseEntity(patientToDto.convert(patient) , HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPatient(Long id) {

        Optional<Patient> patient = patientService.getPatientById(id);
        return  new ResponseEntity(patientToDto.convert(patient.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientDto> updatePatient(Long id,@Valid PatientDto patientDto) {
        Patient patientJpa = patientService.updatePatient(dtoToPatient.convert(patientDto), id);

        return new ResponseEntity<>(patientToDto.convert(patientJpa), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPatientsByDoctorId(Authentication authentication) {
        User user = userService.getLoggedIn(authentication);
        List<Patient> patients = patientService.getByDoctorId(user.getId());
        List<PatientDto> dtos = patientToDto.convert(patients);
        return new ResponseEntity(dtos, HttpStatus.OK);
    }


//    public void initializeTestData() {
//        Patient patient1= new Patient("email@gmail.com", "pass123", "Clark", "Johnson",
//                "Address Street 16a", "Novi Sad", "Serbia", "012345678", "095540");
//
//        Patient patient2= new Patient("email2@yahoo.com", "654321", "Eve", "Hamilton",
//                "Address Street 2", "Belgrade", "Serbia", "098765432", "196547");
//        patientService.addPatient(patient1);
//        patientService.addPatient(patient2);
//
//    }



}
