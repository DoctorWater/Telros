create table "TelrosTestDB".telros_user_schema."user"
(
    id           bigserial
        constraint user_pk
            primary key,
    name         varchar(100) not null,
    surname      varchar(100) not null,
    second_name  varchar(100),
    phone_number varchar(100),
    email        varchar(100)
);

alter table "TelrosTestDB".telros_user_schema."user"
    owner to postgres;