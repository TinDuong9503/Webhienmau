package com.example.Webhienmau.controller;


import com.example.Webhienmau.dto.ApiResponse;
import com.example.Webhienmau.dto.AuthResponse;
import com.example.Webhienmau.dto.Request.AuthRequest;
import com.example.Webhienmau.dto.Request.RefreshTokenRequest;
import com.example.Webhienmau.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private  AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        ApiResponse apiResponse = new ApiResponse(200, "Login successful", response);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshAccessToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshAccessToken(request));
    }
}
