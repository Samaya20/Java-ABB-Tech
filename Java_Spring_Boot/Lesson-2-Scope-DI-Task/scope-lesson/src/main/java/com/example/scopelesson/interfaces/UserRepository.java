package com.example.scopelesson.interfaces;

import com.example.scopelesson.dto.request.CreateUserRequest;
import com.example.scopelesson.dto.response.UserInfoResponse;

import java.util.List;

public interface UserRepository {
    UserInfoResponse getUserById(Long id);

    List<UserInfoResponse> getAllUsers();

    UserInfoResponse createUser(CreateUserRequest createUserRequest);
}
