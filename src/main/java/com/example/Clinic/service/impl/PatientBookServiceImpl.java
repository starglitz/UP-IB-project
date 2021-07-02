package com.example.Clinic.service.impl;


import com.example.Clinic.model.Patient;
import com.example.Clinic.model.PatientBook;
import com.example.Clinic.repository.PatientBookRepository;
import com.example.Clinic.repository.PatientRepository;
import com.example.Clinic.security.xml.AsymmetricKeyDecryption;
import com.example.Clinic.security.xml.AsymmetricKeyEncryption;
import com.example.Clinic.service.PatientBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Optional;

@Service
public class PatientBookServiceImpl implements PatientBookService {

    @Autowired
    private PatientBookRepository patientBookRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public boolean addPatientBook(PatientBook patientBook) throws IOException, SAXException, ParserConfigurationException {

        System.out.println("!!!!!!!!");
        String encryptedXml = "";
        System.out.println(patientBook);
        Patient patient = patientRepository.findById(patientBook.getPatient().getId()).get();
        //Patient patient = patientDao.getPatientById(patientBook.getPatient().getId()).get();
        patientBook.setPatient(patient);

        boolean valid = checkValid(patientBook);

        if (valid)
            patientBook.setXml(toXML(patientBook));
            encryptedXml = AsymmetricKeyEncryption.encryptMain(patientBook);

        patientBook.setXml(encryptedXml);
        System.out.println(patientBook);

        patientBookRepository.save(patientBook);

        return valid;
    }

    @Override
    public PatientBook findById(Long id) throws IOException, SAXException, ParserConfigurationException {

        PatientBook book = patientBookRepository.findById(id).orElse(null);
        PatientBook decipheredBook = new PatientBook();
        if(book != null) {
            System.out.println("THE BOOK THAT I WILL BE DECRYPTING");
            System.out.println(book);
            String decipheredXml = AsymmetricKeyDecryption.decryptMain(book);
            decipheredBook = new PatientBook(book.getId(), book.getPatient(), decipheredXml);
            System.out.println("deciphered book: ");
            System.out.println(decipheredBook);
        }

        return decipheredBook; }

    @Override
    public boolean updatePatientBook(PatientBook patientBook, Long id) throws IOException, SAXException, ParserConfigurationException {
        boolean valid = checkValid(patientBook);

        PatientBook book = patientBookRepository.findById(id).orElse(null);
        PatientBook decipheredBook = new PatientBook();


        if (valid && patientBook.getId().equals(id)) {

            String decipheredXml = AsymmetricKeyDecryption.decryptMain(book);
            decipheredBook = new PatientBook(book.getId(), book.getPatient(), decipheredXml);
            System.out.println("DECIPHERED BOOK: ");
            System.out.println(decipheredBook);

            decipheredBook.setDrugs(patientBook.getDrugs());
            decipheredBook.setIllnessHistory(patientBook.getIllnessHistory());
            decipheredBook.setXml(toXML(decipheredBook));
            String encryptedXml = AsymmetricKeyEncryption.encryptMain(decipheredBook);
            decipheredBook.setXml(encryptedXml);

            System.out.println(decipheredBook);

            patientBookRepository.save(decipheredBook);
        }
        return valid;
    }

    private boolean checkValid(PatientBook patientBook) {
        boolean valid = true;
        if (patientBook.getPatient() == null) { valid = false; }
//        if (patientBook.getRecipes() == null) { valid = false; }
        return valid;
    }


    public String toXML(PatientBook book) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        xml += "<patientBook>" +
                "    <illnessHistory>";

        for(String illness : book.getIllnessHistory()) {
            xml += "<illness>" + illness + "</illness>";
        }
        xml += "</illnessHistory>";

        xml += "<drugs>";

        for(String drug : book.getDrugs()) {
            xml += "<drug>" + drug + "</drug>";
        }

        xml += "</drugs>" +
                "</patientBook>";

        return xml;
    }
}
