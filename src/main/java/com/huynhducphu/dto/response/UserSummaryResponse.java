package com.huynhducphu.dto.response;

/**
 * Admin 6/14/2026
 *
 **/
public record UserSummaryResponse(
        String id,
        String username,
        String fullName,
        String avatarKey
) {
}
