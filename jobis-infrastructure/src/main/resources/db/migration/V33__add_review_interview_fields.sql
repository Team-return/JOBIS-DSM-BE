ALTER TABLE tbl_review
    ADD COLUMN interview_type VARCHAR(20);

ALTER TABLE tbl_review
    ADD COLUMN interview_location VARCHAR(20);

ALTER TABLE tbl_review
    ADD COLUMN code_id BIGINT;

ALTER TABLE tbl_review
    ADD COLUMN interviewer_count INT;

ALTER TABLE tbl_review
    ADD COLUMN interview_question VARCHAR(1000);
