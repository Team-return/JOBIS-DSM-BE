ALTER TABLE tbl_review
ADD CONSTRAINT fk_review_code
FOREIGN KEY (code_id) REFERENCES tbl_code(code);