create table if not exists "user"
(
    id        uuid    not null primary key,
    user_name varchar not null unique,
    email     varchar not null unique,
    password  varchar not null,
    activated boolean not null
);