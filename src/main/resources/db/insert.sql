insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_ADMIN');

insert into persons (password, username, role_id) VALUES ('password', 'Pablo', 1);
insert into persons (password, username, role_id) VALUES ('password', 'Alina', 1);

insert into rooms (name) values ('Meeting room');

insert into messages (text, person_id) VALUES ('Hello, guys!!!!', 1);
insert into messages (text, person_id) VALUES ('Hello! Where are you?', 2);
insert into messages (text, person_id) VALUES ('I am from Brazil, Rio!', 1);
insert into messages (text, person_id) VALUES ('Rio is my crash! Love it.', 2);

insert into rooms_messages (Room_id, messages_id) VALUES (1, 1);
insert into rooms_messages (Room_id, messages_id) VALUES (1, 2);
insert into rooms_messages (Room_id, messages_id) VALUES (1, 3);
insert into rooms_messages (Room_id, messages_id) VALUES (1, 4);