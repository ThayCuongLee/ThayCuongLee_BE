package com.huynhducphu.model;

import com.huynhducphu.config.snowflake.SnowflakeGenerated;
import com.huynhducphu.model.base.BaseEntity;
import com.huynhducphu.model.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Admin 6/13/2026
 *
 **/
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id
    @SnowflakeGenerated
    Long id;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    String username;

    @Column(name = "hashed_password", length = 255, nullable = false)
    String hashedPassword;

    @OneToOne(mappedBy = "user")
    UserProfile userProfile;

    @Enumerated(value = EnumType.STRING)
    Role role;

}
