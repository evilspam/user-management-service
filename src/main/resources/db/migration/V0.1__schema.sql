CREATE TABLE user
(
    id BIGINT(20) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NULL,
    surname VARCHAR(255) NULL,
    marital_status VARCHAR(255),
    birth_date DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE generated_code
(
    email VARCHAR(255) PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    confirmed BIT NULL,
    created DATETIME NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table user_table_id_seq
(
    next_val bigint NULL
);


