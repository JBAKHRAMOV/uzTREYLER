package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.kino.KinoDTO;
import com.company.dto.kino.KinoSearchDTO;
import com.company.dto.kino.KinoUpdateDTO;
import com.company.dto.request.FindByNameDTO;
import com.company.service.KinoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kino")
@Api(tags = "Kino")
@RequiredArgsConstructor
@Slf4j
public class KinoController {

    private final KinoService kinoService;

    /**
     * ADMIN
     */

    @PostMapping("")
    @ApiOperation(value = "trailer", notes = "method for trailer upload")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<KinoDTO> trailerUpload(@org.springframework.web.bind.annotation.RequestBody KinoDTO dto) {
        log.info("kino upload with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.trailerUpload(dto));
    }


    @DeleteMapping("/{trailerId}")
    @ApiOperation(value = "delete trailer", notes = "method for trailer delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable("trailerId") String trailerId) {
        log.info("kino delete with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.delete(trailerId));
    }


    @PutMapping("/{trailerId}")
    @ApiOperation(value = "update trailer", notes = "method for trailer update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<KinoDTO> update(@PathVariable("trailerId") String trailerId,
                                          @org.springframework.web.bind.annotation.RequestBody
                                          @RequestBody KinoUpdateDTO dto) {
        log.info("kino update with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.update(dto, trailerId));
    }


    /**
     * PUBLIC
     */

    @GetMapping("/{trailerId}")
    @ApiOperation(value = "get Trailer ", notes = "method for Trailer get by Id")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<KinoDTO> get(@PathVariable("trailerId") String trailerId) {
        log.info("kino get with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.getById(trailerId));
    }


    @GetMapping("")
    @ApiOperation(value = "get Trailer ", notes = "method for Trailer get")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PageImpl<KinoDTO>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("kino get with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.getAll(page, size));
    }


    @GetMapping("/{categoryId}/category")
    @ApiOperation(value = "get Trailer ", notes = "method for Trailer get by categoryId")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PageImpl<KinoDTO>> getByCategotyId(@RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "size", defaultValue = "10") int size,
                                                             @PathVariable("categoryId") String categoryID) {
        log.info("kino get by  category with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.getByCategoryId(page, size, categoryID));
    }

    @GetMapping("/name")
    @ApiOperation(value = "get Trailer by name", notes = "method for Trailer get by name")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PageImpl<KinoDTO>> getByName(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @org.springframework.web.bind.annotation.RequestBody FindByNameDTO dto) {
        log.info("kino get by  category with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.getByName(page, size, dto));
    }


    @PostMapping("/filter")
    @ApiOperation(value = "get Trailer by filter", notes = "method for get Trailer by filter")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<KinoDTO>> filter(@org.springframework.web.bind.annotation.RequestBody
                                                KinoSearchDTO dto) {
        log.info("get Trailer by filter: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(kinoService.filterKino(dto));
    }


}

