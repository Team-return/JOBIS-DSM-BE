CREATE TABLE new_tbl_application_attachment
(
    id BIGINT AUTO_INCREMENT,
    attachment_url VARCHAR(400) NOT NULL,
    type VARCHAR(4) NOT NULL,
    application_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_NEW_TBL_APPLICATION_ATTACHMENT_ON_APPLICATION
        FOREIGN KEY (application_id) REFERENCES tbl_application (id)
);

INSERT INTO new_tbl_application_attachment (attachment_url, type, application_id)
SELECT attachment_url, type, application_id FROM tbl_application_attachment;

DROP TABLE tbl_application_attachment;

RENAME TABLE new_tbl_application_attachment TO tbl_application_attachment;
