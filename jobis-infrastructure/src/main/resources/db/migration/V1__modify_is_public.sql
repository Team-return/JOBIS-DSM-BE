alter table tbl_code
    modify is_public tinyint(1) not null;
alter table tbl_company
    add representation_phone_no varchar(12) not null;
alter table tbl_recruitment
    add working_hours varchar(100) null;
alter table tbl_recruitment
    drop start_time;
alter table tbl_recruitment
    drop end_time;