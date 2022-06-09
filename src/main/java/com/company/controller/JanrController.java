package com.company.controller;

import com.company.dto.JanrDTO;
import com.company.service.JanrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/janr")
@Api(tags = "Janr")
@Slf4j
@RequiredArgsConstructor
public class JanrController {

    private final JanrService janrService;


    /**
     * ADMIN
     */


    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "create", notes = "method for create janr")
    public ResponseEntity<JanrDTO> create(@RequestBody @Valid JanrDTO dto) {
        log.info("create janr: {}", dto);
        return ResponseEntity.ok(janrService.create(dto));
    }


    @DeleteMapping("/{janrId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Delete", notes = "method for delete janr ")
    public ResponseEntity<Boolean> delete(@PathVariable("janrId") String id) {
        log.info("delete janr: {}", id);
        return ResponseEntity.ok(janrService.delete(id));
    }


    @PutMapping("/{janrId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Delete", notes = "method for delete janr ")
    public ResponseEntity<JanrDTO> update(@PathVariable("janrId") String id,
                                          @RequestBody JanrDTO dto) {
        log.info("update janr: {}", dto);
        return ResponseEntity.ok(janrService.update(dto, id));
    }


    /**
     * Public
     */


    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIIN','USER')")
    @ApiOperation(value = "Get", notes = "method for get janr  list")
    public ResponseEntity<PageImpl<JanrDTO>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("get janr list : page {} ,size {}", page, size);
        return ResponseEntity.ok(janrService.get(page, size));
    }


}

