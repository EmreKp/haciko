ALTER TABLE posts ADD COLUMN category_id BIGINT (20) AFTER message;

CREATE TABLE categories (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL
);

ALTER TABLE posts ADD FOREIGN KEY (category_id) REFERENCES categories (id);