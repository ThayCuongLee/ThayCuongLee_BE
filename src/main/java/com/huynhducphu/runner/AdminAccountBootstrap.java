package com.huynhducphu.runner;

import com.huynhducphu.model.User;
import com.huynhducphu.model.UserProfile;
import com.huynhducphu.model.constant.Role;
import com.huynhducphu.repository.UserProfileRepository;
import com.huynhducphu.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Admin 6/16/2026
 *
 **/
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminAccountBootstrap implements ApplicationRunner {

    UserRepository userRepository;
    UserProfileRepository userProfileRepository;

    PasswordEncoder passwordEncoder;

    @Value("${app.bootstrap.default-admin-username}")
    @NonFinal
    String bootstrapAdminUsername;

    @Value("${app.bootstrap.default-admin-password}")
    @NonFinal
    String bootstrapAdminPassword;


    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        var runFlag = userRepository.existsByUsername(bootstrapAdminUsername);
        if (runFlag) return;

        var saveUser = userRepository.save(new User(
                null,
                bootstrapAdminUsername,
                passwordEncoder.encode(bootstrapAdminPassword),
                null,
                Role.ADMIN
        ));

        var userProfile = new UserProfile();
        userProfile.setFullName("Administrator");
        userProfile.setUser(saveUser);
        userProfileRepository.save(userProfile);

    }
}
