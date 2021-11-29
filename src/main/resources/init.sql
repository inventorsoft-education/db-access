create table if not exists teams
(
    id      serial      not null primary key,
    name    varchar(50) not null,
    capitan varchar(50) not null,
    coach   varchar(50) not null
);

create table if not exists matches
(
    id          serial      not null primary key,
    first_team  varchar(50) not null,
    second_team varchar(50) not null,
    round       varchar(6)  not null,
    score       varchar(6)  not null
);
