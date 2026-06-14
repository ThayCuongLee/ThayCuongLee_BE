package com.huynhducphu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Admin 6/14/2026
 *
 **/
public record ChangeUserPasswordRequest(
        @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
        @NotBlank(message = "Mật khẩu không được để trống")
        String password
) {
}
