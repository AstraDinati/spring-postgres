package com.pet.study.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pet.study.exception.auth.EmptyJwtClaimsException;
import com.pet.study.exception.auth.ExpiredJwtTokenException;
import com.pet.study.exception.auth.InvalidJwtSignatureException;
import com.pet.study.exception.auth.InvalidJwtTokenException;
import com.pet.study.exception.auth.UnsupportedJwtTokenException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Service
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private SecretKey getSigningKey() {
        byte[] bytes = Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(bytes, "HmacSHA512");
    }

    public String generateToken(Long userId, String email, String firstName, String lastName, Set<String> roles) {
        return Jwts.builder()
                .subject(email)
                .claim("userId", userId)
                .claim("firstName", firstName)
                .claim("lastName", lastName)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public void validateToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(authToken)
                    .getPayload();
        } catch (io.jsonwebtoken.security.SignatureException ex) {
            throw new InvalidJwtSignatureException("Invalid JWT signature");
        } catch (io.jsonwebtoken.MalformedJwtException ex) {
            throw new InvalidJwtTokenException("Invalid JWT token");
        } catch (io.jsonwebtoken.ExpiredJwtException ex) {
            throw new ExpiredJwtTokenException("Expired JWT token");
        } catch (io.jsonwebtoken.UnsupportedJwtException ex) {
            throw new UnsupportedJwtTokenException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new EmptyJwtClaimsException("JWT claims string is empty.");
        }
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("userId", Long.class);
    }

    public String getEmailFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public String getFirstNameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("firstName", String.class);
    }

    public String getLastNameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("lastName", String.class);
    }

    public Set<String> getRolesFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Object rolesObject = claims.get("roles");

        if (rolesObject instanceof List<?>) {
            return ((List<?>) rolesObject).stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
