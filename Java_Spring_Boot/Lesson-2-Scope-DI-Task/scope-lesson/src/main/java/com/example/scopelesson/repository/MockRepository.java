package com.example.scopelesson.repository;

import com.example.scopelesson.dto.request.CreateUserRequest;
import com.example.scopelesson.dto.response.UserInfoResponse;
import com.example.scopelesson.interfaces.UserRepository;
import com.example.scopelesson.logging.RequestContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MockRepository implements UserRepository {

    private final RequestContext requestContext;

    public MockRepository(RequestContext requestContext) {
        this.requestContext = requestContext;
        System.out.println("MockRepository: " + this.hashCode() + " yaradildi");
    }

    @Override
    public UserInfoResponse getUserById(Long id) {
        System.out.println("MockRepository requestId: " + requestContext.getRequestId());
        return new UserInfoResponse(id, "Mock User", "mock@test.com", true, LocalDateTime.now());
    }

    @Override
    public List<UserInfoResponse> getAllUsers() {
        return List.of(
                new UserInfoResponse(1L, "Mock User 1", "mock1@test.com", true, LocalDateTime.now()),
                new UserInfoResponse(2L, "Mock User 2", "mock2@test.com", true, LocalDateTime.now())
        );
    }

    @Override
    public UserInfoResponse createUser(CreateUserRequest request) {
        return new UserInfoResponse(99L, "Mock User", request.getEmail(), true, LocalDateTime.now());
    }
}
