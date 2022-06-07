package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthDTO {

    @NotBlank(message = "email not valid!")
    private String email;

    @NotBlank(message = "password not valid!")
    @Size(min = 8,max = 15,message = "password min size = 4,max size = 30")
    private String password;
}
