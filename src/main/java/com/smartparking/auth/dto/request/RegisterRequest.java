package com.smartparking.auth.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String cardId;
    private String name;
    private String email;
}