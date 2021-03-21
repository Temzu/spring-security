create table users (
                       id                    bigserial,
                       username              varchar(30) not null,
                       password              varchar(80) not null,
                       score                 integer not null,
                       primary key (id)
);

create table roles (
                       id                    serial,
                       name                  varchar(50) not null,
                       primary key (id)
);

CREATE TABLE users_roles (
                             user_id               bigint not null,
                             role_id               int not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references users (id),
                             foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, score)
values
('user1', '$2y$12$9Jdthnn0cFa4Nb/O4x6bZuEaRVhPVlAUHYrsn2uzRFU2e6ZQL1dYy', 0),
('user2', '$2y$12$9Jdthnn0cFa4Nb/O4x6bZuEaRVhPVlAUHYrsn2uzRFU2e6ZQL1dYy', 0);

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(2, 1);