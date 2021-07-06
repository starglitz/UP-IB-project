package com.example.Clinic.service.impl;


import com.example.Clinic.model.Appointment;
import com.example.Clinic.model.Clinic;
import com.example.Clinic.model.ClinicRating;
import com.example.Clinic.repository.ClinicRatingRepository;
import com.example.Clinic.repository.ClinicRepository;
import com.example.Clinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicRatingRepository clinicRatingRepository;

    @Override
    public Clinic create(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    @Override
    public List<Clinic> findAll() {
        List<Clinic> clinics = clinicRepository.findAll();
        return setAverageRatingToClinics(clinics);
    }

    @Override
    public Clinic findById(Long id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        if (clinic.getRatings().size() != 0) {
            float count = 0;
            for (ClinicRating rating : clinic.getRatings()) {
                count += rating.getRating();
            }
            System.out.println("AVERAGE RATING: " + count / clinic.getRatings().size());
            clinic.setAverageRating(count / clinic.getRatings().size());
        }
        return clinic;

    }

    @Override
    public List<Clinic> findClinicsByDate(LocalDate date) {
        List<Clinic> clinics = clinicRepository.findClinicsByDate(date);



        if(date.equals(LocalDate.of(2000, 01, 01))){
            return setAverageRatingToClinics(clinicRepository.findAllCurrentDates());
        }
        return setAverageRatingToClinics(clinics);

    }

    @Override
    public Clinic update(Clinic clinic) {

        return clinicRepository.save(clinic);
    }

    @Override
    public List<Clinic> getNotRatedByPatientId(Long id) {
        List<Clinic> clinics = clinicRepository.findNotRatedByPatientId(id);
        clinics = setAverageRatingToClinics(clinics);
        return clinics;
    }

    @Override
    public Clinic rate(Long id, ClinicRating rating) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        rating = clinicRatingRepository.save(rating);
        clinic.getRatings().add(rating);
        return clinicRepository.save(clinic);
    }


    public List<Clinic> setAverageRatingToClinics(List<Clinic> clinics) {
        for(Clinic clinic : clinics) {
            if (clinic.getRatings().size() != 0) {
                float count = 0;
                for (ClinicRating rating : clinic.getRatings()) {
                    count += rating.getRating();
                }
                System.out.println("AVERAGE RATING: " + count / clinic.getRatings().size());
                clinic.setAverageRating(count / clinic.getRatings().size());
            }
        }
        return clinics;
    }
}
