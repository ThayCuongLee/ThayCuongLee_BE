package com.huynhducphu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Admin 6/14/2026
 *
 **/
public record ChangeSelfPasswordRequest(
        @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
        @NotBlank(message = "Mật khẩu cũ không được để trống")
        String oldPassword,

        @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
        @NotBlank(message = "Mật khẩu mới không được để trống")
        String newPassword
) {
}
