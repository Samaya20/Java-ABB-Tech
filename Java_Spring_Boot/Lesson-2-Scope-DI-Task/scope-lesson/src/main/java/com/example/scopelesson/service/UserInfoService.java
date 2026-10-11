package com.example.scopelesson.service;

import com.example.scopelesson.dto.request.CreateUserRequest;
import com.example.scopelesson.dto.response.UserInfoResponse;
import com.example.scopelesson.logging.RequestContext;
import com.example.scopelesson.logging.UserAuditLog;
import com.example.scopelesson.repository.UserRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    private final ObjectProvider<UserAuditLog> userAuditLog;
    private final UserRepository userRepository;
    private final RequestContext requestContext;

    public UserInfoService(UserRepository userRepository, ObjectProvider<UserAuditLog> userAuditLog, RequestContext requestContext) {
        this.userRepository = userRepository;
        this.userAuditLog = userAuditLog;
        this.requestContext = requestContext;
        System.out.println("UserInfoService: " + this.hashCode() + " yaradildi");
    }

    public UserInfoResponse getUserById(Long id) {
        UserAuditLog userAuditLogProvider = userAuditLog.getObject();
//        System.out.println("AuditID" + userAuditLogProvider.getAuditId());
//        System.out.println("TraceID" + requestContext.getTraceId());
        return userRepository.getUserById(id);
    }

    public List<UserInfoResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserInfoResponse createUser(CreateUserRequest createUserRequest) {
        return userRepository.createUser(createUserRequest);
    }
}
