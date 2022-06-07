package com.company.controller;

import com.company.dto.AuthDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.RegistrationDTO;
import com.company.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
@Api(tags = "Authorization")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "Login",notes = "method for login ")
    public ResponseEntity<ProfileDTO> login(@RequestBody @Valid AuthDTO dto){
        log.info("login : {}", dto);
        return ResponseEntity.ok(authService.login(dto));

    }


    @PostMapping("")
    @ApiOperation(value = "registration",notes = "method for registration")
    public ResponseEntity<ProfileDTO> registration(@RequestBody @Valid RegistrationDTO dto){
        log.info("registration : {}", dto);
        return ResponseEntity.ok(authService.registration(dto));
    }

}
