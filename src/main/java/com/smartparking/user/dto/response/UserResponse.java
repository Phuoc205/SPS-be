package com.smartparking.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String name;
    private String role;
    private String cardId;
    private String email;
    private LocalDateTime createdAt;
}
