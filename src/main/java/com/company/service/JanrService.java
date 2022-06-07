package com.company.service;

import com.company.repository.JanrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JanrService {
    private final JanrRepository janrRepository;
}
