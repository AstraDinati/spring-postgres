package com.pet.study.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.pet.study.dto.auth.JwtTokenResponse;
import com.pet.study.dto.auth.LoginRequest;
import com.pet.study.entity.RefreshToken;
import com.pet.study.exception.user.UserAuthenticationException;
import com.pet.study.security.CustomUserDetails;
import com.pet.study.security.JwtTokenProvider;

@Service
public class LoginAuthService {

    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LoginAuthService(
            RefreshTokenService refreshTokenService,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider) {
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtTokenResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String jwtToken = jwtTokenProvider.generateToken(
                    userDetails.getUserId(),
                    userDetails.getEmail(),
                    userDetails.getFirstName(),
                    userDetails.getLastName(),
                    userDetails.getRoles());

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUserId());

            return new JwtTokenResponse(jwtToken, refreshToken.getToken());
        } catch (AuthenticationException e) {
            throw new UserAuthenticationException("Invalid username or password");
        }
    }
}
