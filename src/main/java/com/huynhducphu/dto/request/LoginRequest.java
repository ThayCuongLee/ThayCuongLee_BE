package com.huynhducphu.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Admin 6/14/2026
 *
 **/
public record LoginRequest(
        @NotBlank(message = "Tên đăng nhập không được để trống")
        String username,

        @NotBlank(message = "Mật khẩu không được để trống")
        String password
) {
}
