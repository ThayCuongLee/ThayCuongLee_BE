package com.huynhducphu.model;

import com.huynhducphu.config.snowflake.SnowflakeGenerated;
import com.huynhducphu.model.constant.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * Admin 6/13/2026
 *
 **/
@Entity
@Table(name = "user_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfile {

    // =====================================================
    // Thông tin định danh
    // =====================================================

    @Id
    @SnowflakeGenerated
    Long id;

    String fullName;

    @Enumerated(value = EnumType.STRING)
    Gender gender;

    LocalDate dateOfBirth;

    String avatarKey;

    // =====================================================
    // Thông tin liên hệ
    // =====================================================

    String phoneNumber;

    String address;

    String className;


}
