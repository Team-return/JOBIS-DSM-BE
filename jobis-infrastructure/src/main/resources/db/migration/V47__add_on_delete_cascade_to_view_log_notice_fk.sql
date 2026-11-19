ALTER TABLE tbl_view_log DROP FOREIGN KEY fk_view_log_notice;

ALTER TABLE tbl_view_log
    ADD CONSTRAINT fk_view_log_notice
        FOREIGN KEY (notice_id) REFERENCES tbl_notice (id) ON DELETE CASCADE;
