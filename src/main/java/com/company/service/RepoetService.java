package com.company.service;

import com.company.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepoetService {
    private final ReportRepository repository;
}
