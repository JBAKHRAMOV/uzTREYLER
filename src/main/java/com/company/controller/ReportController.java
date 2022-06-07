package com.company.controller;

import com.company.service.RepoetService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report")
@Api(tags = "Report")
@RequiredArgsConstructor
public class ReportController {
    private final RepoetService repoetService;
}
