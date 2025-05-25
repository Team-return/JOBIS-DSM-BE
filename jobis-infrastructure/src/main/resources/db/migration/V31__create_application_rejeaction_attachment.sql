CREATE TABLE tbl_application_rejection_attachment (
    id             BIGINT NOT NULL AUTO_INCREMENT,
    attachment_url VARCHAR(300) NULL,
    application_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FKaj39235kx6f8qsj7l32l79vbi
        FOREIGN KEY (application_id) REFERENCES tbl_application (id)
);
