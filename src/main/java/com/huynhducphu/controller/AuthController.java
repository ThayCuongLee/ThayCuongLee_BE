package com.huynhducphu.controller;

import com.huynhducphu.dto.base.ApiResponse;
import com.huynhducphu.dto.request.ChangeSelfPasswordRequest;
import com.huynhducphu.dto.request.ChangeUserPasswordRequest;
import com.huynhducphu.service.user.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin 6/14/2026
 *
 **/
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {

    AuthService authService;

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
