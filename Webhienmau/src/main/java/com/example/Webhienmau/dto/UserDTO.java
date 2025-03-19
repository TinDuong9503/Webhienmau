package com.example.Webhienmau.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String cccd;
    private String phone;
    private String email;
    private String fullName;
    private String role;
}
