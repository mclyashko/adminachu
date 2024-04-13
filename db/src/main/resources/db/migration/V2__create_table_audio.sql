create table if not exists audio
(
    id          uuid    not null primary key,
    name        varchar not null unique,
    description varchar not null,
    file        bytea   not null,
    created_by  uuid    not null,
    removed_by  uuid,
    foreign key (created_by) references "user" (id),
    foreign key (removed_by) references "user" (id)
);