package com.example.scopelesson.logging;

import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Component
@Scope("prototype")
public class UserAuditLog {
    private final UUID auditId;
    private final LocalDateTime createdAt;

    // Sual 9 - action ucun setter
    @Setter
    private String action;

    // Sual 10 - konstruktorda audit log yazilmaği
    public UserAuditLog() {
        this.auditId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        System.out.println("UserAuditLog yaradildi: " + this.auditId);
    }

    // Sual 13 - prototype bean-in destroy olmağini yoxlamaq
    @PreDestroy
    public void destroy() {
        System.out.println("UserAuditLog silindi: " + this.auditId);
    }
}