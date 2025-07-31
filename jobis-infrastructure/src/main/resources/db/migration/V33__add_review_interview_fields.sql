ALTER TABLE tbl_review
    ADD COLUMN interview_type VARCHAR(20) NOT NULL;

ALTER TABLE tbl_review
    ADD COLUMN interview_location VARCHAR(20) NOT NULL;

ALTER TABLE tbl_review
    ADD COLUMN code_id BIGINT NOT NULL;

ALTER TABLE tbl_review
    ADD COLUMN interviewer_count INT NOT NULL;

ALTER TABLE tbl_review
    ADD COLUMN interview_question VARCHAR(1000) NOT NULL;
