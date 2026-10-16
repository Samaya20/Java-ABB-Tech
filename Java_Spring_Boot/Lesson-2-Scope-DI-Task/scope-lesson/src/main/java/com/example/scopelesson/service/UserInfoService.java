package com.example.scopelesson.service;

import com.example.scopelesson.dto.request.CreateUserRequest;
import com.example.scopelesson.dto.response.UserInfoResponse;
import com.example.scopelesson.logging.RecentlyViewedUsers;
import com.example.scopelesson.logging.RequestContext;
import com.example.scopelesson.logging.UserAuditLog;
import com.example.scopelesson.repository.UserRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {

    private final ObjectProvider<UserAuditLog> userAuditLog;
    private final UserRepository userRepository;
    private final RequestContext requestContext;
    private final RecentlyViewedUsers recentlyViewedUsers;

    public UserInfoService(UserRepository userRepository, ObjectProvider<UserAuditLog> userAuditLog, RequestContext requestContext, RecentlyViewedUsers recentlyViewedUsers) {
        this.userRepository = userRepository;
        this.userAuditLog = userAuditLog;
        this.requestContext = requestContext;
        this.recentlyViewedUsers = recentlyViewedUsers;
        System.out.println("UserInfoService: " + this.hashCode() + " yaradildi");
    }

    public UserInfoResponse getUserById(Long id) {

        // Sual 16 - Request id log
        System.out.println("Service requestId: " + requestContext.getRequestId());

        // Sual 21 - id-nin elave olunmasi
        recentlyViewedUsers.addUser(id);

        return userRepository.getUserById(id);
    }

    public List<UserInfoResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // Sual 12 - hər createUser-də yeni obyekt yaranmasi
    public UserInfoResponse createUser(CreateUserRequest createUserRequest) {
        UserAuditLog auditLog = userAuditLog.getObject();
        auditLog.setAction("CREATE_USER");
        return userRepository.createUser(createUserRequest);
    }

    // Sual 22 - Tam user melumatlarini qaytaran yeni metod
    public List<UserInfoResponse> getRecentUsers() {
        List<Long> recentIds = recentlyViewedUsers.getUsers();
        List<UserInfoResponse> recentUsersList = new ArrayList<>();

        for (Long id : recentIds) {
            recentUsersList.add(userRepository.getUserById(id));
        }
        return recentUsersList;
    }


}
