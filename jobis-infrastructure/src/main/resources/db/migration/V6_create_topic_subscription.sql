CREATE TABLE tbl_topic_subscription (
    device_token VARCHAR(255) NOT NULL
    topic VARCHAR(26) NOT NULL,
    is_subscribed TINYINT(1) NOT NULL,
    PRIMARY KEY (device_token, topic)
);

