package com.example.scopelesson.controller;

import com.example.scopelesson.dto.request.CreateUserRequest;
import com.example.scopelesson.dto.response.UserInfoResponse;
import com.example.scopelesson.logging.RecentlyViewedUsers;
import com.example.scopelesson.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-info")
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final RecentlyViewedUsers recentlyViewedUsers;

    public UserInfoController(UserInfoService userInfoService, RecentlyViewedUsers recentlyViewedUsers) {
        this.userInfoService = userInfoService;
        this.recentlyViewedUsers = recentlyViewedUsers;
        System.out.println("UserInfoController: " + this.hashCode() + " yaradildi");
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUserById(@PathVariable Long id) {
        recentlyViewedUsers.addUser(id);
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
    public List<Long> getUserIdsRecent() {
        return recentlyViewedUsers.getUsers();
    }

}