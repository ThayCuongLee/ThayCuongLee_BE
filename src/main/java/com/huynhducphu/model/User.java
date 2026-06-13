package com.huynhducphu.model;

import com.huynhducphu.config.snowflake.SnowflakeGenerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class User {

    @Id
    @SnowflakeGenerated
    Long id;

    @Column(name = "username", length = 50, unique = true)
    String username;

    @Column(name = "hashed_password", length = 255)
    String hashedPassword;

}
