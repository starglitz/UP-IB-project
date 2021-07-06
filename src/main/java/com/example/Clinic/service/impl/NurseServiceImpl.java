package com.example.Clinic.service.impl;


import com.example.Clinic.model.*;
import com.example.Clinic.model.enumerations.UserRole;
import com.example.Clinic.repository.AuthorityRepository;
import com.example.Clinic.repository.ClinicAdminRepository;
import com.example.Clinic.repository.NurseRepository;
import com.example.Clinic.repository.UserRepository;
import com.example.Clinic.rest.support.converter.DtoToNurse;
import com.example.Clinic.service.NurseService;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;


    @Autowired
    private DtoToNurse dtoToNurse;

    @Override
    public List<Nurse> findAll() {
        return nurseRepository.findAll();
    }

    @Override
    public Nurse findById(Long id) { return nurseRepository.findById(id).orElse(null); }

    @Override
    public boolean create(Nurse nurse, Authentication authentication) {

        Optional<User> user = userRepository.findFirstByEmail(nurse.getUser().getEmail());

        if(user.isPresent()){
            return false;
        }

        Set<Authority> authorities = new HashSet<>(){{
            add(authorityRepository.findByName(UserRole.NURSE.toString()));
        }};

        User nurseUser = nurse.getUser();
        nurseUser.setRoles(authorities);
        nurseUser.setPassword(passwordEncoder.encode(nurseUser.getPassword()));
        nurseUser.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
        nurseUser.setFirstTime(true);
        User loggedInAdmin = userService.getLoggedIn(authentication);
        ClinicAdmin admin = clinicAdminRepository.findById(loggedInAdmin.getId()).orElse(null);

        nurse.setClinic(admin.getClinic());
        nurse.setUser(nurseUser);

        Nurse nurseJpa = nurseRepository.save(nurse);

        return true;
    }

    @Override
    public List<Nurse> findByClinicId(Long id) {
        return nurseRepository.findByClinicId(id);
    }
}
