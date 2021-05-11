package com.example.Clinic.dao.impl;

import com.example.Clinic.dao.PatientBookDao;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.repository.PatientBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientBookDaoImpl implements PatientBookDao {

    @Autowired
    private PatientBookRepository patientBookRepo;

    @Override
    public PatientBook addPatientBook(PatientBook patientBook) { return patientBookRepo.save(patientBook); }

    @Override
    public Optional<PatientBook> findById(Long id) {
        System.out.println(id);
        System.out.println(patientBookRepo.findById(id));
        return patientBookRepo.findById(id);
    }

    @Override
    public PatientBook updatePatientBook(PatientBook patientBookParam, Long id) {
        PatientBook patientBook = patientBookRepo.findById(id).get();

        patientBook.setPatient(patientBookParam.getPatient());
        patientBook.setRecipes(patientBookParam.getRecipes());

        return patientBookRepo.save(patientBook);
    }
}
