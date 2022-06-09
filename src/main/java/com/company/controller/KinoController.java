package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.KinoDTO;
import com.company.service.KinoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/kino")
@Api(tags = "Kino")
@RequiredArgsConstructor
@Slf4j
public class KinoController {

    private final KinoService kinoService;



    @PostMapping("")
    @ApiOperation(value = "trailer", notes = "method for trailer upload")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> trailerUpload(@RequestParam KinoDTO dto){
        log.info("kino upload with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.trailerUpload(dto));
    }



}

