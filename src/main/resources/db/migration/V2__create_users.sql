CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(100) NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       role VARCHAR(30) NOT NULL,
                       branch_id BIGINT NULL,
                       active BOOLEAN NOT NULL DEFAULT TRUE,

                       created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                       updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                       created_by BIGINT NULL,
                       updated_by BIGINT NULL,

                       PRIMARY KEY (id),

                       CONSTRAINT uk_users_username UNIQUE (username),

                       CONSTRAINT fk_users_branch
                           FOREIGN KEY (branch_id) REFERENCES branches (id)
);