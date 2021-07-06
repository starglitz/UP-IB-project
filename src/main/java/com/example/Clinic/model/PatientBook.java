package com.example.Clinic.model;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@XmlRootElement(name = "patientBook")
public class PatientBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_book_id")
    private Long id;

    @Nullable
    @ManyToOne
    private Patient patient;


    @Transient
    List<String> illnessHistory = new ArrayList<>();

    @Transient
    List<String> drugs = new ArrayList<>();


    private String xml;

    public String toXML() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        xml += "<patientBook>" +
                "    <illnessHistory>";

        for(String illness : illnessHistory) {
            xml += "<illness>" + illness + "</illness>";
        }
        xml += "</illnessHistory>";

        xml += "<drugs>";

        for(String drug : drugs) {
            xml += "<drug>" + drug + "</drug>";
        }

        xml += "</drugs>" +
                "</patientBook>";

        return xml;
    }

    public PatientBook(Long id, @NotNull Patient patient, String xml) {
        this.id = id;
        this.patient = patient;
        this.xml = xml;

        String drugs = StringUtils.substringBetween(xml, "<drugs>", "</drugs>");

        String[] drugsList = StringUtils.substringsBetween(drugs, "<drug>", "</drug>");
        List<String> drugsArray = new ArrayList<>();
        if(drugsList != null) {
            drugsArray = Arrays.asList(drugsList);
        }
        String illnesses = StringUtils.substringBetween(xml, "<illnessHistory", "</illnessHistory>");

        String[] illnessList = StringUtils.substringsBetween(illnesses, "<illness>", "</illness>");
        List<String> illnessArray = new ArrayList<>();
        if(illnessList != null) {
            illnessArray = Arrays.asList(illnessList);
        }


        this.drugs = drugsArray;
        this.illnessHistory = illnessArray;



    }
}
