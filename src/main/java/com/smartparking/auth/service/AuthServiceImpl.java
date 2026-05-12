package com.smartparking.auth.service;

import com.smartparking.auth.dto.request.LoginRequest;
import com.smartparking.auth.dto.request.RegisterRequest;
import com.smartparking.auth.dto.response.AuthResponse;
import com.smartparking.auth.repository.AccountRepository;
import com.smartparking.security.JwtService;
import com.smartparking.user.entity.User;
import com.smartparking.user.entity.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository repo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                user.getId(),
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(UserRole.USER);
        user.setCardId(request.getCardId());

        repo.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                user.getId(),
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }
}