package com.company.service;

import com.company.dto.AuthDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.RegistrationDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder;

    public ProfileDTO login(AuthDTO dto) {

        ProfileEntity profileEntity = profileRepository.findByEmailAndDeletedDateIsNull(dto.getEmail()).orElseThrow(() -> {
            throw new ItemNotFoundException("profile not found!");
        });


        if (!passwordEncoder.matches(dto.getPassword(), profileEntity.getPassword())) {
            throw new AppBadRequestException("password wrong");
        }

        ProfileDTO profileDTO = toDTO(profileEntity);

        String jwt = JwtUtil.encode(profileEntity.getId());
        profileDTO.setToken(jwt);

        return profileDTO;
    }

    public ProfileDTO registration(RegistrationDTO dto) {
        Optional<ProfileEntity> profileEntity =
                profileRepository.findByEmailAndDeletedDateIsNull(dto.getEmail());
        if (profileEntity.isPresent()) {
            throw new ItemNotFoundException("profile all ready exists!");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setRole(ProfileRole.ROLE_USER);

        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setEmail(dto.getEmail());

        profileRepository.save(entity);
        return toDTO(entity);
    }

    public static ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();

        dto.setName(entity.getName());

        dto.setSurname(entity.getSurname());

        dto.setId(entity.getId());

        dto.setCreatedDate(entity.getCreatedDate());

        dto.setRole(entity.getRole());

        dto.setEmail(entity.getEmail());

        dto.setStatus(entity.getStatus());

        return dto;
    }

}
