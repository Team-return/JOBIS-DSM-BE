CREATE TABLE view_log (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,

                          notice_id BIGINT NOT NULL,
                          student_id BIGINT NOT NULL,
                          viewed_at DATETIME,

                          CONSTRAINT fk_view_log_notice
                              FOREIGN KEY (notice_id)
                                  REFERENCES tbl_notice(id)
                                  ON DELETE CASCADE,

                          CONSTRAINT fk_view_log_student
                              FOREIGN KEY (student_id)
                                  REFERENCES tbl_student(id)
                                  ON DELETE CASCADE
);
