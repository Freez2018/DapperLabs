package com.example.demo.services;

import com.example.demo.data.User;
import com.example.demo.data.UserResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserTransformer {
    public static List<UserResponse> transformUserToUserResponse(List<User> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(user -> new UserResponse(
                        user.getFirstName().trim(),
                        user.getLastName().trim(),
                        user.getEmail().trim()
                ))
                .collect(Collectors.toList());
    }
}
