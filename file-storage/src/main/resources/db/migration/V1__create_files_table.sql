create table Files (
    file_id bigserial primary key,
    filename varchar(128) not null,
    path varchar not null,
    type varchar not null,
    uploaded_at timestamp not null,
    updated_at timestamp not null
);