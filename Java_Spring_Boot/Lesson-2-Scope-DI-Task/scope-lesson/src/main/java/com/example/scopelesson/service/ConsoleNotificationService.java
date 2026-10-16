package com.example.scopelesson.service;

import com.example.scopelesson.interfaces.NotificationService;
import org.springframework.stereotype.Service;

// Sual 31 - Notification Service implementasiyasi

@Service
public class ConsoleNotificationService implements NotificationService {

    @Override
    public void sendWelcome(String email) {
        System.out.println("Welcome : " + email);
    }
}
