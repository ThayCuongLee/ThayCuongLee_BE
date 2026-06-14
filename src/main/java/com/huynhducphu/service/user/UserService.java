package com.huynhducphu.service.user;

import com.huynhducphu.dto.request.CreateUserRequest;
import com.huynhducphu.dto.response.UserSummaryResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Admin 6/14/2026
 *
 **/
public interface UserService {
    @Transactional
    void createUser(CreateUserRequest request);

    @Transactional
    void toggleUser(String id);

    Page<UserSummaryResponse> getUsers(Pageable pageable);
}
