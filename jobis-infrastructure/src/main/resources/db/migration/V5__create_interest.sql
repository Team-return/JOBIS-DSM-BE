CREATE TABLE tbl_interest
(
    interest_id BIGINT AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    code BIGINT NOT NULL,
    PRIMARY KEY (interest_id),
    CONSTRAINT fk_interest_student
        FOREIGN KEY (student_id) REFERENCES tbl_student (student_id),
    CONSTRAINT fk_interest_code
        FOREIGN KEY (code) REFERENCES tbl_code (code)
);
