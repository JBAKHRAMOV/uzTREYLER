package com.company.controller;

import com.company.service.KinoJanrService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kino-janr")
@Api(tags = "Kino Janr")
@RequiredArgsConstructor
public class KinoJanrController {
    private final KinoJanrService kinoJanrService;
}
