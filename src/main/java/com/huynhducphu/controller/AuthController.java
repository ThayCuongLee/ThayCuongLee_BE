package com.huynhducphu.controller;

import com.huynhducphu.dto.base.ApiResponse;
import com.huynhducphu.dto.request.ChangeSelfPasswordRequest;
import com.huynhducphu.dto.request.ChangeUserPasswordRequest;
import com.huynhducphu.dto.request.LoginRequest;
import com.huynhducphu.dto.response.LoginResponse;
import com.huynhducphu.service.user.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin 6/14/2026
 *
 **/
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody @Valid LoginRequest body
    ) {
        AuthService.AuthResultWrapper authResultWrapper = authService.login(body);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, authResultWrapper.refreshToken().toString())
                .body(new ApiResponse<>(new LoginResponse(authResultWrapper.accessToken())));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        ResponseCookie res = authService.logout();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, res.toString())
                .body(new ApiResponse<>());
    }

    @PutMapping("/admin/auth/{id}/password")
    public ResponseEntity<ApiResponse<Void>> changeUserPassword(
            @PathVariable("id") String id,
            @RequestBody @Valid ChangeUserPasswordRequest body
    ) {
        authService.changeUserPassword(id, body);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>());
    }

    @PutMapping("/me/password")
    public ResponseEntity<ApiResponse<Void>> changeSelfPassword(
            @RequestBody @Valid ChangeSelfPasswordRequest body
    ) {
        authService.changeSelfPassword(body);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>());
    }


}
