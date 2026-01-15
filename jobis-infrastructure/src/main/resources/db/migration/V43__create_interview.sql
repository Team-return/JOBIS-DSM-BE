CREATE TABLE tbl_interview
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    interview_type     VARCHAR(20)  NOT NULL,
    start_date         DATE         NOT NULL,
    end_date           DATE         NULL,
    interview_time     VARCHAR(8)   NOT NULL,
    company_name       VARCHAR(20)  NOT NULL,
    location           VARCHAR(80)  NOT NULL,
    student_id         BIGINT       NOT NULL,
    document_number_id BIGINT       NULL,
    CONSTRAINT fk_interview_student
        FOREIGN KEY (student_id) REFERENCES tbl_student (student_id),
    CONSTRAINT fk_interview_document_number
        FOREIGN KEY (document_number_id) REFERENCES tbl_document_number (id)
);
