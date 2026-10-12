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
    private final ObjectProvider<UserAuditLog> userAuditLogProvider;

    public UserInfoService(UserRepository userRepository, ObjectProvider<UserAuditLog> userAuditLog, RequestContext requestContext, ObjectProvider<UserAuditLog> userAuditLogProvider) {
        this.userRepository = userRepository;
        this.userAuditLog = userAuditLog;
        this.requestContext = requestContext;
        this.userAuditLogProvider = userAuditLogProvider;
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

    // Sual 12 - hər createUser-də yeni obyekt yaranmaği
    public UserInfoResponse createUser(CreateUserRequest createUserRequest) {
        UserAuditLog auditLog = userAuditLogProvider.getObject();
        auditLog.setAction("CREATE_USER");
        return userRepository.createUser(createUserRequest);
    }
}
