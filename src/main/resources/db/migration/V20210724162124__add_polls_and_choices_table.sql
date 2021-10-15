CREATE TABLE polls (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `question` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `expires_at` DATETIME
);

CREATE TABLE poll_choices (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `text` VARCHAR(255) NOT NULL,
    `vote_count` INT(11) NOT NULL DEFAULT 0,
    `poll_id` BIGINT(20) NOT NULL,
    FOREIGN KEY (poll_id) REFERENCES polls (id)
);