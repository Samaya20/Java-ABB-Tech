package com.example.scopelesson.controller;

import com.example.scopelesson.dto.request.CreateUserRequest;
import com.example.scopelesson.dto.response.UserInfoResponse;
import com.example.scopelesson.logging.RecentlyViewedUsers;
import com.example.scopelesson.logging.RequestContext;
import com.example.scopelesson.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-info")
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final RequestContext requestContext;

    public UserInfoController(UserInfoService userInfoService, RequestContext requestContext) {
        this.userInfoService = userInfoService;
        this.requestContext = requestContext;
        System.out.println("UserInfoController: " + this.hashCode() + " yaradildi");
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUserById(@PathVariable Long id) {
        // Sual 16 - Request id log
        System.out.println("Controller requestId: " + requestContext.getRequestId());

        // Sual 19 - Class adini gormek ucun
        System.out.println("RequestContext class: " + requestContext.getClass());

        // Sual 17 - Sorğunun neçe millisaniye cekdiyini gormek
        long duration = System.currentTimeMillis() - requestContext.getStartTime();
        System.out.println("Request time: " + duration + "ms");

        return userInfoService.getUserById(id);
    }

    @GetMapping("/all")
    public List<UserInfoResponse> getAllUsers() {
        return userInfoService.getAllUsers();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userInfoService.createUser(createUserRequest);
    }

    @GetMapping("/recent")
    public List<UserInfoResponse> getUserIdsRecent() {
        return userInfoService.getRecentUsers();
    }

}