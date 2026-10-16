package com.example.scopelesson.repository;

import com.example.scopelesson.dto.request.CreateUserRequest;
import com.example.scopelesson.dto.response.UserInfoResponse;
import com.example.scopelesson.interfaces.UserRepository;
import com.example.scopelesson.logging.RequestContext;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository


// Sual 28 - NoUniqueBeanDefinitionException xetasinin Primary ile helli
// @Primary

public class InMemoryUserRepository implements UserRepository {


    // Sual 1 - Thread safe map
    private final Map<Long, UserInfoResponse> storage = new ConcurrentHashMap<>();

    // Sual 2 - Ardicil id ucun atomiclong
    private final AtomicLong idCounter = new AtomicLong(0);

    private RequestContext requestContext;

    // Sual 6 - Konstruktor log
    public InMemoryUserRepository(RequestContext requestContext) {
        // Sual 15 - Request Context-i inject etmek
        this.requestContext = requestContext;

        System.out.println("UserRepository: " + this.hashCode() + " yaradildi");
    }

    // Sual 3 - Tetbiq acilanda 3 userin yuklenmeyi
    @PostConstruct
    public void initializeUser() {
        saveUser(new UserInfoResponse(
                0L,
                "Ruslan",
                "ruslan@example.com",
                true,
                LocalDateTime.now()
        ));
        saveUser(new UserInfoResponse(
                0L,
                "Samaya",
                "samaya@example.com",
                true,
                LocalDateTime.now()
        ));
        saveUser(new UserInfoResponse(
                0L,
                "TestUser",
                "testuser@example.com",
                true,
                LocalDateTime.now()
        ));

        System.out.println("UserRepository : 3 user yuklendi");
    }

    // Sual 4 - Tetbiq baglananda storage temizlenmeyi
    @PreDestroy
    public void destroy() {
        storage.clear();
        System.out.println("UserRepository baglandi, storage temizlendi");
    }

    // User save elemek ucun metod
    public UserInfoResponse saveUser(UserInfoResponse user) {
        long id = idCounter.incrementAndGet();
        user.setId(id);
        storage.put(id, user);
        return user;
    }

    // Sual 5 - user tapilmasa exception atmasi
    public UserInfoResponse getUserById(Long id) {
        UserInfoResponse user = storage.get(id);

        if (user == null) {
            throw new NoSuchElementException("Istifadeci tapilmadi. Id: " + id);
        }
        // Sual 16 - Request id log
        System.out.println("Repository requestId: " + requestContext.getRequestId());

        return user;
    }

    public List<UserInfoResponse> getAllUsers() {
        return new ArrayList<>(storage.values());
    }

    public UserInfoResponse createUser(CreateUserRequest createUserRequest) {
        UserInfoResponse user = new UserInfoResponse();
        long newId = idCounter.incrementAndGet();
        user.setId(newId);
        user.setFullName(createUserRequest.getFullName());
        user.setEmail(createUserRequest.getEmail());
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        storage.put(newId, user);

        return user;
    }
}