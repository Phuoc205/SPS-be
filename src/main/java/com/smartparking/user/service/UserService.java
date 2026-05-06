package com.smartparking.user.service;

import com.smartparking.user.dto.response.UserResponse;
import com.smartparking.user.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponse> getAll(String keyword);

    UserResponse getById(Long id);

    User create(User user);

    User update(Long id, User user);

    void delete(Long id);
}