package com.huynhducphu.repository;

import com.huynhducphu.model.User;
import com.huynhducphu.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Admin 6/14/2026
 *
 **/
@Repository
public interface UserProfileRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<UserProfile> {
}
