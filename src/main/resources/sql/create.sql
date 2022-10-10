create table if not exists teams
(
    id     smallint primary key not null generated always as identity,
    team   varchar(100)         not null,
    pilot1 varchar(50)          not null,
    pilot2 varchar(50)          not null
);
create table if not exists matches
(
    id           int primary key not null generated always as identity,
    round        varchar(20)     not null,
    team1        smallint        not null,
    team2        smallint        not null,
    points_team1 smallint        not null,
    points_team2 smallint        not null,
    constraint matches_team1_id foreign key (team1) references teams (id),
    constraint matches_team2_id foreign key (team2) references teams (id)
);
create table if not exists tournament
(
    id         int primary key not null generated always as identity,
    t_name     varchar(50)     not null,
    match_id   int             not null,
    match_date date            not null,
    constraint tournament_match_id foreign key (match_id) references matches (id)
);