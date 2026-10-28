CREATE TABLE branches (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          code VARCHAR(20) NOT NULL,
                          name VARCHAR(120) NOT NULL,
                          address VARCHAR(500) NOT NULL,
                          active BOOLEAN NOT NULL DEFAULT TRUE,

                          created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                          updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                          created_by BIGINT NULL,
                          updated_by BIGINT NULL,

                          PRIMARY KEY (id),
                          CONSTRAINT uk_branches_code UNIQUE (code)
);