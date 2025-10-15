create table tbl_question
(
    id       bigint auto_increment
        primary key,
    question varchar(255) not null,
    constraint UK_hl19akhd9qxyx97xw3pcgqoby
        unique (question)
);
