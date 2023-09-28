create table Users (
    user_id bigserial primary key,
    username varchar not null ,
    password varchar not null,
    email varchar not null ,
    created_at timestamp,
    updated_at timestamp,
    is_approved boolean,
    role varchar
);

insert into users (
                   username,
                   password,
                   email,
                   created_at,
                   updated_at,
                   is_approved,
                   role)
values (
        'admin',
        'admin',
        'admin@gmail.com',
        now(),
        now(),
        true,
        'ROLE_ADMIN');
