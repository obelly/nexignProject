CREATE TABLE IF NOT EXISTS roles
(
    id   BIGSERIAL PRIMARY KEY,
    role VARCHAR(20) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id  BIGINT
        CONSTRAINT fk_role_id_user REFERENCES roles
);

INSERT INTO roles (role)
VALUES ('ABONENT'),
       ('MANAGER');

INSERT INTO users (login, password, role_id)
VALUES ('abonent', '123', 1),
       ('manager', '123', 2);



