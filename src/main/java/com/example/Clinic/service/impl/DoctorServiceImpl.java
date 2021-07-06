package com.example.Clinic.service.impl;


import com.example.Clinic.model.*;
import com.example.Clinic.model.enumerations.UserRole;
import com.example.Clinic.repository.*;
import com.example.Clinic.service.DoctorService;
import com.example.Clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private DoctorRatingRepository doctorRatingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Doctor> findAll() {
        return setAverageRating(doctorRepository.findAll());
    }

    @Override
    public Doctor findById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if(doctor != null) {
            float count = 0;
            for(DoctorRating rating : doctor.getRatings()) {
                count += rating.getRating();
            }
            doctor.setAverageRating(count/doctor.getRatings().size());
        }
        return doctor;
    }

    @Override
    public List<Doctor> findByClinicId(Long id) {
        return setAverageRating(doctorRepository.findByClinicId(id));
    }

    @Override
    public List<Doctor> findByClinicAndDate(Long id, LocalDate date) {
        return setAverageRating(doctorRepository.findByClinicAndDate(id, date));
    }

    @Override
    public boolean create(Doctor doctor, Authentication authentication) {
        Optional<User> user = userRepository.findFirstByEmail(doctor.getUser().getEmail());

        if(user.isPresent()){
            return false;
        }

        Set<Authority> authorities = new HashSet<>(){{
            add(authorityRepository.findByName(UserRole.DOCTOR.toString()));
        }};

        User doctorUser = doctor.getUser();
        doctorUser.setRoles(authorities);
        doctorUser.setPassword(passwordEncoder.encode(doctorUser.getPassword()));
        doctorUser.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
        doctorUser.setFirstTime(true);

        User loggedInAdmin = userService.getLoggedIn(authentication);
        ClinicAdmin admin = clinicAdminRepository.findById(loggedInAdmin.getId()).orElse(null);

        doctor.setClinic(admin.getClinic());
        doctor.setUser(doctorUser);

        Doctor doctorJpa = doctorRepository.save(doctor);

        System.out.println("Im called");
        return true;
    }

    @Override
    public List<Doctor> getNotRatedByPatientId(Long id) {
        return setAverageRating(doctorRepository.findNotRatedByPatientId(id));
    }

    @Override
    public Doctor rate(Long id, DoctorRating rating) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        rating = doctorRatingRepository.save(rating);
        doctor.getRatings().add(rating);
        return doctorRepository.save(doctor);
    }


    public List<Doctor> setAverageRating(List<Doctor> doctors) {
        for(Doctor doctor : doctors) {
            float count = 0;
            for(DoctorRating rating : doctor.getRatings()) {
                count += rating.getRating();
            }
            doctor.setAverageRating(count/doctor.getRatings().size());
        }
        return doctors;
    }
}
