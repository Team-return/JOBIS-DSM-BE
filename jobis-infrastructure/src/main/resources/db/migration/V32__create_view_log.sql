CREATE TABLE post_view_log (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               post_id BIGINT NOT NULL,
                               user_id BIGINT NOT NULL,
                               viewed_at DATETIME NOT NULL,

                               INDEX idx_post_id (post_id),
                               INDEX idx_user_id (user_id)
);
