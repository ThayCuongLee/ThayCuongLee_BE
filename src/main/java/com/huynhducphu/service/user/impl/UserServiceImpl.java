package com.huynhducphu.service.user.impl;

import com.huynhducphu.dto.request.CreateUserRequest;
import com.huynhducphu.dto.response.UserSummaryResponse;
import com.huynhducphu.model.User;
import com.huynhducphu.model.UserProfile;
import com.huynhducphu.repository.UserProfileRepository;
import com.huynhducphu.repository.UserRepository;
import com.huynhducphu.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Admin 6/14/2026
 *
 **/
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserProfileRepository userProfileRepository;

    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(CreateUserRequest body) {

        if (userRepository.existsByUsername(body.userName()))
            throw new IllegalArgumentException("Người dùng đã tồn tại");

        // User
        User user = new User(
                null,
                body.userName(),
                passwordEncoder.encode(body.password()),
                null
        );
        User savedUser = userRepository.save(user);

        // User Profile
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(savedUser);
        userProfileRepository.save(userProfile);
    }

    @Transactional
    @Override
    public void toggleUser(String id) {
        User user = userRepository
                .findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng này"));

        user.setActive(!user.getActive());
    }

    @Override
    public Page<UserSummaryResponse> getUsers(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(user -> new UserSummaryResponse(
                        user.getId().toString(),
                        user.getUsername(),
                        user.getUserProfile().getFullName(),
                        user.getUserProfile().getAvatarKey()
                ));
    }


}
