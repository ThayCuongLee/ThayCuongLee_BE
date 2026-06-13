package com.huynhducphu.model;

import com.huynhducphu.config.snowflake.SnowflakeGenerated;
import com.huynhducphu.model.base.BaseEntity;
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
public class UserProfile extends BaseEntity {

    // =====================================================
    // Thông tin định danh
    // =====================================================

    @Id
    @SnowflakeGenerated
    Long id;

    @Column(name = "fullname", length = 100)
    String fullName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    Gender gender;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "avatar_key")
    String avatarKey;

    // =====================================================
    // Thông tin liên hệ
    // =====================================================

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address")
    String address;

    // =====================================================
    // Thông tin quan hệ
    // =====================================================

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

}
