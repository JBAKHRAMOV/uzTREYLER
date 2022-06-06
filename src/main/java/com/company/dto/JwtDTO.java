package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {

    private String email;

    public JwtDTO(String email) {
        this.email = email;
    }

}
