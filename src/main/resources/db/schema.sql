create table roles (
    id serial primary key,
    name varchar(255) unique not null
);

create table persons (
    id serial primary key,
    password varchar(255) not null,
    username varchar(255) not null,
    role_id int references roles(id) not null
);

create table messages (
    id serial primary key,
    text text,
    person_id int references persons(id) not null
);

create table rooms (
    id serial primary key,
    name varchar(255) not null
);

create table rooms_messages (
    Room_id int references rooms(id),
    messages_id int references messages(id)
);