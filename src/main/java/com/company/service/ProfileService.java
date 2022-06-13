package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.ProfileDTO;
import com.company.dto.request.ProfileRequestDTO;
import com.company.dto.request.update.UpdateProfileDTO;
import com.company.dto.request.update.UpdateProfileEmailDTO;
import com.company.dto.request.update.UpdateProfilePasswordDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import com.company.exception.AppBadRequestException;
import com.company.exception.EmailAlreadyExistsException;
import com.company.exception.ItemAlreadyExistsException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileDTO create(ProfileRequestDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndDeletedDateIsNull(dto.getEmail());
        if (optional.isPresent()) {
            log.error("Email Already Exits : {}" , dto);
            throw new ItemAlreadyExistsException("Email Already Exits");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setRole(dto.getRole());
        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean update(UpdateProfileDTO dto) {
        ProfileEntity entity = EntityDetails.getProfile();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUpdatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        return true;
    }

    public Boolean changePassword(UpdateProfilePasswordDTO dto){
        ProfileEntity entity = EntityDetails.getProfile();
        if (!passwordEncoder.matches(dto.getOldPassword(), entity.getPassword())){
            throw new BadCredentialsException("Wrong Cretion");
        }
        String pswd = passwordEncoder.encode(dto.getNewPassword());
        profileRepository.changePassword(entity.getId(), pswd);
        return true;
    }

    public Boolean changeEmail(UpdateProfileEmailDTO dto){
        ProfileEntity entity = EntityDetails.getProfile();
        if (!entity.getEmail().equals(dto.getOldEmail())){
            throw new BadCredentialsException("Wrong Cretion");
        }
        profileRepository.changeEmail(entity.getId(), dto.getNewEmail());
        return true;
    }


    public ProfileDTO toDTO(ProfileEntity entity){
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        return dto;
    }

    public ProfileDTO getById(String id) {
        ProfileEntity entity = getProfile(id);
        return toDTO(entity);
    }

    private ProfileEntity getProfile(String id){
        ProfileEntity entity = profileRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Not Found!"));
        if (entity.getDeletedDate() != null)  {
            log.error("Not Found!");
            throw new ItemNotFoundException("Not Found!");
        }
        return entity;
    }

    public PageImpl<ProfileDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<ProfileDTO> dtoList = new ArrayList<>();
        Page<ProfileEntity > pageList = profileRepository.findAll(pageable);
        for (ProfileEntity commentEntity : pageList){
            dtoList.add(toDTO(commentEntity));
        }
        return new PageImpl<ProfileDTO>(dtoList, pageable, pageList.getTotalElements());
    }

    public Boolean delete(String id) {
        ProfileEntity entity = getProfile(id);
        entity.setDeletedDate(LocalDateTime.now());
        profileRepository.save(entity);
        return true;
    }

}
