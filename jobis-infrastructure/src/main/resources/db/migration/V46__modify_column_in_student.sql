alter table tbl_student
    modify column profile_image_url varchar(300) not null default 'EXTENSION_FILE/default_image.png';
