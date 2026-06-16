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
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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


        log.info("Looking for Admin account");
        var runFlag = userRepository.existsByUsername(bootstrapAdminUsername);
        if (runFlag) {
            log.info("Admin account found, skip bootstrapping admin account");
            return;
        } else log.warn("Admin account not found, bootstrapping admin account");

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
        log.info("Admin account bootstrapped successfully");
    }
}
