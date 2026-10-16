package com.example.scopelesson.logging;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Getter
@Component

// Sual 14 - Request scope teyin etmek
//@RequestScope

//Sual 18 - Request scope xetasi ve proxy mode ile duzeldilmesi
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class RequestContext {
    private final String traceId = UUID.randomUUID().toString();
    private final String requestId;
    private final long startTime;

    public RequestContext() {
        this.requestId = UUID.randomUUID().toString().substring(0, 8);
        this.startTime = System.currentTimeMillis();
    }
}
