package com.huynhducphu.service.user;

import com.huynhducphu.dto.request.ChangeSelfPasswordRequest;
import com.huynhducphu.dto.request.ChangeUserPasswordRequest;
import jakarta.transaction.Transactional;

/**
 * Admin 6/14/2026
 *
 **/
public interface AuthService {
    @Transactional
    void changeUserPassword(String id, ChangeUserPasswordRequest body);

    @Transactional
    void changeSelfPassword(ChangeSelfPasswordRequest body);
}
