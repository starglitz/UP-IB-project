package com.example.Clinic.service.impl;


import com.example.Clinic.model.Authority;
import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.User;
import com.example.Clinic.model.enumerations.UserRole;
import com.example.Clinic.repository.AuthorityRepository;
import com.example.Clinic.repository.DoctorRepository;
import com.example.Clinic.repository.NurseRepository;
import com.example.Clinic.repository.UserRepository;
import com.example.Clinic.rest.support.converter.DtoToNurse;
import com.example.Clinic.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DtoToNurse dtoToNurse;

    @Override
    public List<Nurse> findAll() {
        return nurseRepository.findAll();
    }

    @Override
    public Optional<Nurse> findById(Long id) {
        return nurseRepository.findById(id);
    }

    @Override
    public Nurse create(Nurse nurse) {

        Optional<User> user = userRepository.findFirstByEmail(nurse.getUser().getEmail());

        if(user.isPresent()){
            return null;
        }

        Set<Authority> authorities = new HashSet<>(){{
            add(authorityRepository.findByName(UserRole.NURSE.toString()));
        }};

        User nurseUser = nurse.getUser();
        nurseUser.setRoles(authorities);
        nurseUser.setPassword(passwordEncoder.encode(nurseUser.getPassword()));
        nurseUser.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));

        nurse.setUser(nurseUser);
        Nurse nurseJpa = nurseRepository.save(nurse);
        return nurseJpa;
    }

    @Override
    public List<Nurse> findByClinicId(Long id) {
        return nurseRepository.findByClinicId(id);
    }
}
