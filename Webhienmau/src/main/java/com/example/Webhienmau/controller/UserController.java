package com.example.Webhienmau.controller;

import com.example.Webhienmau.config.mapper.Utils;
import com.example.Webhienmau.dto.ApiResponse;
import com.example.Webhienmau.dto.Request.RegisterRequest;
import com.example.Webhienmau.dto.UserDTO;
import com.example.Webhienmau.model.User;
import com.example.Webhienmau.repository.UserRepository;
import com.example.Webhienmau.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = Utils.mapUserEntityToDTO(user);

        ApiResponse response = new ApiResponse(200, "User retrieved successfully", userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {
        ApiResponse response = userService.registerUser(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
