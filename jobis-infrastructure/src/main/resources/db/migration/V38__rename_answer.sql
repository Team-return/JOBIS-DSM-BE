ALTER TABLE tbl_review CHANGE COLUMN interview_question answer VARCHAR(500);
ALTER TABLE tbl_review
    MODIFY question VARCHAR(100) NULL;
