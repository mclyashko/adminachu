create table if not exists broadcast
(
    id          uuid        not null primary key,
    streamed_at timestamptz not null,
    audio_id    uuid        not null,
    foreign key (audio_id) references audio (id)
);