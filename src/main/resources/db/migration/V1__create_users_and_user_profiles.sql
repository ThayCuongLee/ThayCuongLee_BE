CREATE TABLE users (
   id BIGINT NOT NULL,
   username VARCHAR(50) NOT NULL,
   hashed_password VARCHAR(255) NOT NULL,

   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   created_by VARCHAR(100),
   updated_by VARCHAR(100),
   active BOOLEAN DEFAULT TRUE NOT NULL,

   CONSTRAINT pk_users PRIMARY KEY (id),
   CONSTRAINT uc_users_username UNIQUE (username)
);


CREATE TABLE user_profiles (
   id BIGINT NOT NULL,
   fullname VARCHAR(100),
   gender VARCHAR(50),
   date_of_birth DATE,
   avatar_key VARCHAR(255),
   phone_number VARCHAR(255),
   address VARCHAR(255),

   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   created_by VARCHAR(100),
   updated_by VARCHAR(100),
   active BOOLEAN DEFAULT TRUE NOT NULL,

   user_id BIGINT NOT NULL,

   CONSTRAINT pk_user_profiles PRIMARY KEY (id),
   CONSTRAINT uc_user_profiles_user UNIQUE (user_id),
   CONSTRAINT fk_user_profiles_user FOREIGN KEY (user_id) REFERENCES users (id)
);
