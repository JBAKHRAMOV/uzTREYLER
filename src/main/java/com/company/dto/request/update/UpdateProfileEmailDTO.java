package com.company.dto.request.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileEmailDTO {
    private String newEmail;
    private String oldEmail;
}
