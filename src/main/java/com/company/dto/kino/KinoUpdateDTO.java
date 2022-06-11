package com.company.dto.kino;

import com.company.enums.KInoType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KinoUpdateDTO {

    private String name;

    private String country;

    private KInoType type;

    private String categoryId;
}
