package com.huynhducphu.service.user;

import com.huynhducphu.dto.request.ChangeSelfPasswordRequest;
import com.huynhducphu.dto.request.ChangeUserPasswordRequest;
import com.huynhducphu.dto.request.LoginRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseCookie;

/**
 * Admin 6/14/2026
 *
 **/
public interface AuthService {
    AuthResultWrapper login(LoginRequest body);

    ResponseCookie logout();

    @Transactional
    void changeUserPassword(String id, ChangeUserPasswordRequest body);

    @Transactional
    void changeSelfPassword(ChangeSelfPasswordRequest body);

    record AuthResultWrapper(
            String accessToken,
            ResponseCookie refreshToken
    ) {
    }
}
