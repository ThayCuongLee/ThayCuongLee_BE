package com.huynhducphu.controller;

import com.huynhducphu.dto.base.ApiPageResponse;
import com.huynhducphu.dto.base.ApiResponse;
import com.huynhducphu.dto.request.CreateUserRequest;
import com.huynhducphu.dto.response.UserSummaryResponse;
import com.huynhducphu.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin 6/14/2026
 *
 **/
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserService userManagementService;

    @PostMapping("/admin/users")
    public ResponseEntity<ApiResponse<Void>> createUser(
            @RequestBody @Valid CreateUserRequest bodyRequest
    ) {
        userManagementService.createUser(bodyRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>());
    }

    @PostMapping("/admin/users/toggle/{id}")
    public ResponseEntity<ApiResponse<Void>> toggleUser(@PathVariable("id") String id) {
        userManagementService.toggleUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>());
    }

    @GetMapping("/admin/users")
    public ResponseEntity<ApiResponse<ApiPageResponse<UserSummaryResponse>>> getUsers(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<UserSummaryResponse> page = userManagementService.getUsers(pageable);
        ApiPageResponse<UserSummaryResponse> res = new ApiPageResponse<>(page);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(res));
    }


}
