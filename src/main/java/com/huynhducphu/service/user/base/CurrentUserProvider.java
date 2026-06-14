package com.huynhducphu.service.user.base;

import com.huynhducphu.model.User;
import com.huynhducphu.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Admin 6/14/2026
 *
 **/
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CurrentUserProvider {

    UserRepository userRepository;

    public User get() {
        String username = Objects.requireNonNull(
                SecurityContextHolder.getContext().getAuthentication()
        ).getName();

        if (username == null)
            throw new EntityNotFoundException("Username không được để trống");

        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Người dùng không tồn tại"));
    }


}
