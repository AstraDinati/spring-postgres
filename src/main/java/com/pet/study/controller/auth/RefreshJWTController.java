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
import com.pet.study.dto.auth.TokenDTO;
import com.pet.study.service.auth.RefreshTokenService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class RefreshJWTController {

    private final RefreshTokenService refreshTokenService;

    @Autowired
    public RefreshJWTController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<CustomApiResponse<JwtTokenResponse>> refreshToken(@RequestBody TokenDTO tokenDto) {
        JwtTokenResponse jwtTokenResponse = refreshTokenService.refreshAccessToken(tokenDto);
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Token refreshed successfully", jwtTokenResponse));
    }
}
