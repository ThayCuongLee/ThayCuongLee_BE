package com.huynhducphu.service.user.impl;

import com.huynhducphu.dto.request.ChangeSelfPasswordRequest;
import com.huynhducphu.dto.request.ChangeUserPasswordRequest;
import com.huynhducphu.model.User;
import com.huynhducphu.repository.UserRepository;
import com.huynhducphu.service.user.base.CurrentUserProvider;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
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


}
