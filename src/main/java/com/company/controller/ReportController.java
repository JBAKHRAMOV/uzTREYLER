package com.company.controller;

import com.company.dto.ReportDTO;
import com.company.dto.request.ReportRequestDTO;
import com.company.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@Api(tags = "Report")
@RequiredArgsConstructor
@Slf4j
public class ReportController {
    private final ReportService repoetService;

    @ApiOperation(value = "Create report", notes = "Method used for create report", nickname = "Bilol")
    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ReportDTO> create(@RequestBody ReportRequestDTO dto){
        return ResponseEntity.ok(repoetService.create(dto));
    }

    @ApiOperation(value = "Get report list", notes = "Method used for get report list", nickname = "Bilol")
    @GetMapping("/list-pagination")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<ReportDTO>> getListPagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(repoetService.getListPagination(page, size));
    }

    @ApiOperation(value = "Get by reportId", notes = "Method used for get by reportId", nickname = "Bilol")
    @GetMapping("/{reportId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ReportDTO> getById(@PathVariable("reportId") String reportId){
        return ResponseEntity.ok(repoetService.getByReportId(reportId));
    }

    @ApiOperation(value = "Get by profileId", notes = "Method used for get report by profile id", nickname = "Bilol")
    @GetMapping("/profile/{profileId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<ReportDTO>> getByProfileId(@PathVariable("profileId") String profileId,
                                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(repoetService.getByProfileId(profileId, page, size));
    }

}