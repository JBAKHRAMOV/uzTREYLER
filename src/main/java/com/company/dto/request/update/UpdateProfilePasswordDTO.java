package com.company.dto.request.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfilePasswordDTO {
    private String oldPassword;
    private String newPassword;
}
