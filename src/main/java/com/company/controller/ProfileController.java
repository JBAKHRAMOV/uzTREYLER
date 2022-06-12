package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.dto.request.ProfileRequestDTO;
import com.company.dto.request.update.UpdateProfileDTO;
import com.company.dto.request.update.UpdateProfileEmailDTO;
import com.company.dto.request.update.UpdateProfilePasswordDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@Api(tags = "Profile")
@Slf4j
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @ApiOperation(value = "Create", notes = "Method used for create profile", nickname = "Bilol")
    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileRequestDTO dto){
        log.info("Create Profile: {}", dto);
        return ResponseEntity.ok(profileService.create(dto));
    }

    @ApiOperation(value = "Update", notes = "Method used for update profile", nickname = "Bilol")
    @PutMapping("")
    public ResponseEntity<Boolean> update(@RequestBody @Valid UpdateProfileDTO dto) {
        log.info("Update Profile: {}", dto);
        return ResponseEntity.ok(profileService.update(dto));
    }

    @ApiOperation(value = "Update Password", notes = "Method used for update profile password", nickname = "Bilol")
    @PutMapping("/changePswd")
    public ResponseEntity<Boolean> changePassword(@RequestBody UpdateProfilePasswordDTO dto){
        return ResponseEntity.ok(profileService.changePassword(dto));
    }

    @ApiOperation(value = "Change Email", notes = "Method used for change profile email", nickname = "Bilol")
    @PutMapping("/changeEmail")
    public ResponseEntity<Boolean> changeEmail(@RequestBody UpdateProfileEmailDTO dto){
        return ResponseEntity.ok(profileService.changeEmail(dto));
    }

    @ApiOperation(value = "Get By Id", notes = "Method used for get by profile id for admin", nickname = "Bilol")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProfileDTO> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(profileService.getById(id));
    }

    @ApiOperation(value = "Get All", notes = "Method used for get profile list for admin", nickname = "Bilol")
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(profileService.getAll(page, size));
    }

    @ApiOperation(value = "Delete", notes = "Method used for delete profile ", nickname = "Bilol")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable("id")String profileId){
        return ResponseEntity.ok(profileService.delete(profileId));
    }
}
