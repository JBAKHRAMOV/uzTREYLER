package com.company.dto.request;

import com.company.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDTO {
    private String name;

    private String surname;

    private String password;

    private String email;

    private ProfileRole role;
}
