package com.example.Clinic.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    private Long id;

    @NotEmpty(message = "last name is mandatory")
    @NotBlank(message = "cant be blank")
    @NotNull(message = "cant be null")
    private String lbo;
    private boolean enabled = true;
    private boolean approved = false;

    private boolean visitedMail = false;

    @Nullable
    private long patientBookId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private User user;


//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "register_request_id")
//    private RegisterRequest register_request;

    public Patient(String lbo) {

        this.lbo = lbo;
        this.enabled = true;
    }

    public Patient(@NotEmpty(message = "last name is mandatory")
                   @NotBlank(message = "cant be blank")
                   @NotNull(message = "cant be null") String lbo,
                   boolean enabled, boolean approved, User user) {
        this.lbo = lbo;
        this.enabled = enabled;
        this.approved = approved;
        this.user = user;
    }
}
