create table if not exists sequence
(
    id         uuid   not null primary key,
    "order"    bigint not null,
    audio_id   uuid   not null,
    foreign key (audio_id) references audio (id)
);