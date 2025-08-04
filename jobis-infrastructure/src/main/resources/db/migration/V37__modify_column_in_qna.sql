ALTER TABLE tbl_qna
    ADD COLUMN question_id BIGINT NOT NULL;

ALTER TABLE tbl_qna
    ADD CONSTRAINT FK_TBL_QNA_ON_QUESTION
        FOREIGN KEY (question_id) REFERENCES tbl_question (id);
