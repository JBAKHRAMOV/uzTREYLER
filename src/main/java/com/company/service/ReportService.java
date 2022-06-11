package com.company.service;

import com.company.config.details.CustomProfileDetails;
import com.company.dto.ReportDTO;
import com.company.dto.request.ReportRequestDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.ReportEntity;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportDTO create(ReportRequestDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomProfileDetails customProfileDetails = (CustomProfileDetails) authentication.getPrincipal();
        ProfileEntity profile = customProfileDetails.getProfile();
        ReportEntity entity = new ReportEntity();
        entity.setContent(dto.getContent());
        entity.setProfileId(profile.getId());
        reportRepository.save(entity);
        return toDTO(entity);
    }

    public PageImpl<ReportDTO> getListPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<ReportDTO> dtoList = new ArrayList<>();
        Page<ReportEntity> pageList = reportRepository.findAll(pageable);
        if (pageList.isEmpty()){
            return new PageImpl<>(new LinkedList<>());
        }
        for (ReportEntity reportEntity : pageList){
            dtoList.add(toDTO(reportEntity));
        }
        return new PageImpl<ReportDTO>(dtoList, pageable, pageList.getTotalElements());
    }

    public PageImpl<ReportDTO> getByProfileId(String profileId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<ReportDTO> dtoList = new ArrayList<>();
        Page<ReportEntity > pageList = reportRepository.findByProfileId(profileId, pageable);
        if (pageList.isEmpty()){
            return new PageImpl<>(new LinkedList<>());
        }
        for (ReportEntity reportEntity : pageList){
            dtoList.add(toDTO(reportEntity));
        }
        return new PageImpl<ReportDTO>(dtoList, pageable, pageList.getTotalElements());

    }

    public ReportDTO getByReportId(String reportId) {
        Optional<ReportEntity> optional = reportRepository.findById(reportId);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("Report Not Found");
        }
        return toDTO(optional.get());
    }

    public ReportDTO toDTO(ReportEntity entity){
        ReportDTO dto = new ReportDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setProfileId(entity.getProfileId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }



}
