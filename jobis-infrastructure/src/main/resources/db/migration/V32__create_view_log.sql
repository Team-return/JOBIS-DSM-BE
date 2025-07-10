create table tbl_view_log
(
    id         bigint auto_increment
        primary key,
    notice_id  bigint      not null,
    student_id bigint      not null,
    viewed_at  datetime(6) null,
    constraint FKj9xc58qnoauvj0t2d00tsi1e
        foreign key (student_id) references tbl_student (student_id),
    constraint FKr1n5hx3dqy3aks71v61v6lhin
        foreign key (notice_id) references tbl_notice (id)
);

