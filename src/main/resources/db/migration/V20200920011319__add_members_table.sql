CREATE TABLE members (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nick` VARCHAR(255) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;