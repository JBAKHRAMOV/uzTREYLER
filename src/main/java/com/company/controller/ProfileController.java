package com.company.controller;

import com.company.service.ProfileService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
@Api(tags = "Profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
}
