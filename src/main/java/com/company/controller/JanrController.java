package com.company.controller;

import com.company.service.JanrService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/janr")
@Api(tags = "Janr")
@RequiredArgsConstructor
public class JanrController {
    private final JanrService janrService;
}
