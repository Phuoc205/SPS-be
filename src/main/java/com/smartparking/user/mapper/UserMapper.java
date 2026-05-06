package com.smartparking.user.mapper;

import com.smartparking.user.dto.response.UserResponse;
import com.smartparking.user.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole().name(),
                user.getCardId(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}