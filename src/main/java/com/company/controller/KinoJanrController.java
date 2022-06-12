package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.KinoJanrDTO;
import com.company.dto.kino.KinoDTO;
import com.company.service.KinoJanrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kino-janr")
@Api(tags = "Kino Janr")
@RequiredArgsConstructor
@Slf4j
public class KinoJanrController {

    private final KinoJanrService kinoJanrService;


    @PostMapping("")
    @ApiOperation(value = "create", notes = "method for set janr in kino ")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<KinoJanrDTO> create(@RequestParam @RequestBody KinoJanrDTO dto) {
        log.info("set janr in kino with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoJanrService.create(dto));
    }



    @PutMapping("/{id}")
    @ApiOperation(value = "update", notes = "method for update kino-janr")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<KinoJanrDTO> update(@RequestParam @RequestBody KinoJanrDTO dto,
                                              @PathVariable("id") String id) {
        log.info("update janr in kino with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoJanrService.update(dto,id));
    }


}
