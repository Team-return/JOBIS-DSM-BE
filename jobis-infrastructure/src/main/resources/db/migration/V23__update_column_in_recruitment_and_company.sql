alter table tbl_recruitment add column integration_plan tinyint(1) not null;

alter table tbl_company rename column representative_phone_no to manager_phone_no;

