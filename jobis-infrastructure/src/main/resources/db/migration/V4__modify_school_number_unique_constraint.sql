alter table tbl_student
    drop constraint UKd3mywdtv4c22n8m41uag1osc2;
alter table tbl_student
    add constraint SCHOOL_NUMBER_UNIQUE unique (grade, class_room, number, entrance_year)