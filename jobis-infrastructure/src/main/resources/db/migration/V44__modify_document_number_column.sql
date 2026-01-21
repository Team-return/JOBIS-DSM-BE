ALTER TABLE tbl_document_number
    MODIFY COLUMN document_number VARCHAR(255) NOT NULL,
    ADD CONSTRAINT uk_document_number UNIQUE (document_number);
