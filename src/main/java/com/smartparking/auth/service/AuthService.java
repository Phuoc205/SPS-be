package com.smartparking.auth.service;

import com.smartparking.auth.dto.request.LoginRequest;
import com.smartparking.auth.dto.request.RegisterRequest;
import com.smartparking.auth.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);
}