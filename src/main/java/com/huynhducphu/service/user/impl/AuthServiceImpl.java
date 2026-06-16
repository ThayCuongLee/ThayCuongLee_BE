package com.huynhducphu.service.user.impl;

import com.huynhducphu.dto.request.ChangeSelfPasswordRequest;
import com.huynhducphu.dto.request.ChangeUserPasswordRequest;
import com.huynhducphu.dto.request.LoginRequest;
import com.huynhducphu.model.User;
import com.huynhducphu.repository.UserRepository;
import com.huynhducphu.service.user.base.CurrentUserProvider;
import com.huynhducphu.service.user.base.JwtBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Admin 6/14/2026
 *
 **/
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements com.huynhducphu.service.user.AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    CurrentUserProvider currentUserProvider;
    AuthenticationManager authenticationManager;

    JwtBuilder jwtBuilder;

    @Value("${app.access-token-duration}")
    @NonFinal
    Long accessTokenDuration;

    @Value("${app.refresh-token-duration}")
    @NonFinal
    Long refreshTokenDuration;

    @Value("${app.cookie-secure-enable}")
    @NonFinal
    Boolean cookieSecureEnable;

    @Value("${app.cookie-same-site}")
    @NonFinal
    String cookieSameSite;

    static String refreshTokenCookieName = "refresh_cookie";


    @Override
    public AuthResultWrapper login(LoginRequest body) {

        var token = new UsernamePasswordAuthenticationToken(
                body.username(),
                body.password()
        );
        var authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User currentUser = currentUserProvider.get();
        return buildResultWrapper(currentUser);
    }

    @Override
    public ResponseCookie logout() {
        return ResponseCookie
                .from(refreshTokenCookieName, "")
                .httpOnly(true)
                .maxAge(0)
                .path("/auth/refresh")
                .secure(cookieSecureEnable)
                .sameSite(cookieSameSite)
                .build();
    }

    @Transactional
    @Override
    public void changeUserPassword(String id, ChangeUserPasswordRequest body) {
        User user = userRepository
                .findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng này"));

        user.setHashedPassword(passwordEncoder.encode(body.password()));
    }

    @Transactional
    @Override
    public void changeSelfPassword(ChangeSelfPasswordRequest body) {
        User user = currentUserProvider.get();

        String oldPassword = body.oldPassword();
        if (!passwordEncoder.matches(oldPassword, user.getHashedPassword()))
            throw new AccessDeniedException("Mật khẩu cũ không trùng");

        user.setHashedPassword(passwordEncoder.encode(body.newPassword()));
    }

    private AuthResultWrapper buildResultWrapper(User user) {

        String accessToken = jwtBuilder.buildJwt(user, accessTokenDuration);
        String refreshToken = jwtBuilder.buildJwt(user, refreshTokenDuration);

        ResponseCookie refreshTokenCookie = ResponseCookie
                .from(refreshTokenCookieName, refreshToken)
                .httpOnly(true)
                .maxAge(refreshTokenDuration)
                .path("/auth/refresh")
                .secure(cookieSecureEnable)
                .sameSite(cookieSameSite)
                .build();

        return new AuthResultWrapper(
                accessToken,
                refreshTokenCookie
        );
    }


}
