
package com.smartparking.user.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.smartparking.user.dto.response.UserResponse;
import com.smartparking.user.entity.User;
import com.smartparking.user.mapper.UserMapper;
import com.smartparking.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAll(String keyword) {

        List<User> users;

        if (keyword != null && !keyword.trim().isEmpty()) {
            users = repo.search(keyword.trim());
        } else {
            users = repo.findAll();
        }

        return users.stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getById(Long id) {

        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toResponse(user);
    }

    @Override
    public User create(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return repo.save(user);
    }

    @Override
    public User update(Long id, User userDetails) {

        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDetails.getName());
        user.setCardId(userDetails.getCardId());
        user.setRole(userDetails.getRole());
        user.setEmail(userDetails.getEmail());

        return repo.save(user);
    }

    @Override
    public void delete(Long id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        repo.deleteById(id);
    }
}