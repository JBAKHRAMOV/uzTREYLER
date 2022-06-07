package com.company.service;

import com.company.repository.KinoJanrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KinoJanrService {
    private final KinoJanrRepository kinoJanrRepository;
}
