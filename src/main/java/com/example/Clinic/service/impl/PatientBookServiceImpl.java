package com.example.Clinic.service.impl;

import com.example.Clinic.dao.PatientBookDao;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.service.PatientBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientBookServiceImpl implements PatientBookService {
    @Autowired
    private PatientBookDao patientBookDao;

    @Override
    public boolean addPatientBook(PatientBook patientBook) {
        boolean valid = checkValid(patientBook);

        if (valid)
            patientBookDao.addPatientBook(patientBook);

        return valid;
    }

    @Override
    public Optional<PatientBook> findById(Long id) { return patientBookDao.findById(id); }

    @Override
    public boolean updatePatientBook(PatientBook patientBook, Long id) {
        boolean valid = checkValid(patientBook);

        if (valid && patientBook.getId().equals(id))
            patientBookDao.updatePatientBook(patientBook, id);

        return valid;
    }

    private boolean checkValid(PatientBook patientBook) {
        boolean valid = true;
        if (patientBook.getPatient() == null) { valid = false; }
        if (patientBook.getRecipes() == null) { valid = false; }
        return valid;
    }
}
