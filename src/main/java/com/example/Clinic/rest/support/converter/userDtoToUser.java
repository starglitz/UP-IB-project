package com.example.Clinic.rest.support.converter;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class userDtoToUser {

    private Long id;

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "last name is mandatory")
    private String lastName;

    @NotBlank(message = "address is mandatory")
    private String address;

    @NotBlank(message = "city is mandatory")
    private String city;

    @NotBlank(message = "country is mandatory")
    private String country;

    @NotBlank(message = "phone number is mandatory")
    private String phoneNumber;


}
