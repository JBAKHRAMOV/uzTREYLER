package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDTO {

    @NotBlank(message = "name not valid!")
    @Size(min = 4,max = 30,message = "name min size = 4,max size = 30")
    private String name;

    @NotBlank(message = "surname not valid!")
    @Size(min = 4,max = 30,message = "surname min size = 4,max size = 30")
    private String surname;

    @NotBlank(message = "email not valid!")
    private String email;

    @NotBlank(message = "password not valid!")
    @Size(min = 8,max = 15,message = "password min size = 4,max size = 30")
    private String password;
}
