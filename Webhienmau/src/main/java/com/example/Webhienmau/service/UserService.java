package com.example.Webhienmau.service;

import com.example.Webhienmau.config.mapper.Utils;
import com.example.Webhienmau.dto.ApiResponse;
import com.example.Webhienmau.dto.Request.RegisterRequest;
import com.example.Webhienmau.dto.UserDTO;
import com.example.Webhienmau.model.Enum.RoleName;
import com.example.Webhienmau.model.Role;
import com.example.Webhienmau.model.User;
import com.example.Webhienmau.model.UserConfig;
import com.example.Webhienmau.model.UserProfile;
import com.example.Webhienmau.repository.RoleRepository;
import com.example.Webhienmau.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(RegisterRequest request) {
        // Kiểm tra CCCD đã tồn tại chưa
        if (userRepository.existsByUsername(request.getCccd())) {
            return new ApiResponse(400, "CCCD already exists!", null);
        }

        // Kiểm tra email đã tồn tại chưa
        if (userRepository.existsByEmail(request.getEmail())) {
            return new ApiResponse(400, "Email already exists!", null);
        }

        // Kiểm tra số điện thoại đã tồn tại chưa
        if (userRepository.existsByPhone(request.getPhone())) {
            return new ApiResponse(400, "Phone number already exists!", null);
        }

        // Lấy quyền USER từ DB
        long nextId = userRepository.count() + 1;
        String userId = "US." + nextId; // Tạo ID
        Role userRole = roleRepository.findByName(RoleName.USER);//                .orElseThrow(() -> new RuntimeException("Role USER not found!"));

        // Tạo tài khoản User mới (CHƯA LƯU)
        User user = User.builder()
                .id(userId) // Gán ID tùy chỉnh
                .username(request.getCccd())
                .password(passwordEncoder.encode(request.getPassword())) // Mã hóa mật khẩu
                .phone(request.getPhone())
                .email(request.getEmail())
                .roles(Set.of(userRole)) // Gán quyền USER
                .build();

        // **LƯU USER TRƯỚC**
        user = userRepository.save(user);

        // Tạo UserProfile với ID trùng User ID
        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .fullName(request.getFullName())
                .build();
        user.setUserProfile(userProfile);

        // Tạo UserConfig mặc định
        UserConfig userConfig = UserConfig.builder()
                .user(user)
                .notiSMS(1)
                .notiEmail(1)
                .notiApp(1)
                .build();
        user.setUserConfig(userConfig);

        UserDTO dto = Utils.mapUserEntityToDTO(user);
        // LƯU LẠI User với UserProfile & UserConfig
        userRepository.save(user);
        return new ApiResponse(200, "User registered successfully!", dto);
    }
//    public ApiResponse loginUser(LoginRequest request) {
//            ApiResponse api = new ApiResponse();
//            try {
//
//
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//    }

}
