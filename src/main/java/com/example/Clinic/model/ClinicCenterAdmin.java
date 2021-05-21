package com.example.Clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ClinicCenterAdmin{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="clinic_center_id")
    private ClinicCenter clinicCenter;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
