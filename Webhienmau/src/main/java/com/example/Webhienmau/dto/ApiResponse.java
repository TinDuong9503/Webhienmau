package com.example.Webhienmau.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private int code;
    private String message;    // Thông báo phản hồi
    private Object data;

}
