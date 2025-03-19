package com.example.Webhienmau.service;

import com.example.Webhienmau.config.utils.JWTUtils;

import com.example.Webhienmau.dto.AuthResponse;
import com.example.Webhienmau.dto.Request.AuthRequest;
import com.example.Webhienmau.dto.Request.RefreshTokenRequest;
import com.example.Webhienmau.model.User;
import com.example.Webhienmau.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 🔹 LOGIN & TOKEN ISSUANCE
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );

        UserDetails user = userRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String accessToken = jwtUtils.generateAccessToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshAccessToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtUtils.extractUsername(refreshToken);
        UserDetails user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (!jwtUtils.isTokenValid(refreshToken,user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid or expired refresh token");
        }
        String newAccessToken = jwtUtils.generateAccessToken(user);
        return new AuthResponse(newAccessToken, refreshToken);
    }
}