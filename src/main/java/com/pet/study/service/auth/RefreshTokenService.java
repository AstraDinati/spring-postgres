package com.pet.study.service.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pet.study.dto.auth.JwtTokenResponse;
import com.pet.study.dto.auth.TokenDTO;
import com.pet.study.entity.RefreshToken;
import com.pet.study.entity.User;
import com.pet.study.exception.auth.RefreshTokenExpiredException;
import com.pet.study.exception.auth.RefreshTokenNotFoundException;
import com.pet.study.repository.RefreshTokenRepository;
import com.pet.study.security.JwtTokenProvider;
import com.pet.study.service.helper.UserHelper;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserHelper userHelper;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
            JwtTokenProvider jwtTokenProvider,
            UserHelper userHelper) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userHelper = userHelper;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken(
                userId,
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(refreshTokenDurationMs));

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenExpiredException();
        }
        return token;
    }

    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }

    public JwtTokenResponse refreshAccessToken(TokenDTO tokenDto) {
        String requestRefreshToken = tokenDto.getRefreshToken();

        RefreshToken refreshToken = findByToken(requestRefreshToken)
                .map(this::verifyExpiration)
                .orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token not found or expired"));

        User user = userHelper.findUserById(refreshToken.getUserId());

        Set<String> rolesStr = userHelper.getRolesStr(user);

        String newAccessToken = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                rolesStr);

        return new JwtTokenResponse(newAccessToken, requestRefreshToken);
    }
}
