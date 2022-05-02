CREATE TABLE users (
    username VARCHAR(30) PRIMARY KEY,
    passwordHash VARCHAR(64) NOT NULL
);