ALTER TABLE posts ADD COLUMN member_id BIGINT(20);
ALTER TABLE posts ADD FOREIGN KEY (member_id) REFERENCES members(id);