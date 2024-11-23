package com.pet.study.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.study.dto.CustomApiResponse;
import com.pet.study.dto.auth.JwtTokenResponse;
import com.pet.study.dto.auth.LoginRequest;
import com.pet.study.dto.auth.RegisterRequest;
import com.pet.study.service.auth.LoginAuthService;
import com.pet.study.service.auth.RegisterAuthService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class LogPassAuthController {

    private final LoginAuthService loginAuthService;
    private final RegisterAuthService registerAuthService;

    @Autowired
    public LogPassAuthController(LoginAuthService loginAuthService, RegisterAuthService registerAuthService) {
        this.loginAuthService = loginAuthService;
        this.registerAuthService = registerAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse<String>> registerUser(@RequestBody RegisterRequest registerRequest) {
        registerAuthService.registerUser(registerRequest);
        return ResponseEntity.ok(new CustomApiResponse<>(true,
                "User registered successfully."));
    }

    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<JwtTokenResponse>> login(@RequestBody LoginRequest loginRequest) {
        JwtTokenResponse jwtTokenResponse = loginAuthService.login(loginRequest);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Login successful", jwtTokenResponse));
    }
}
