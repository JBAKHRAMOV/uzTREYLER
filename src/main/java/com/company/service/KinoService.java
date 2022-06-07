package com.company.service;

import com.company.repository.KinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KinoService {
    private final KinoRepository kinoRepository;
}
