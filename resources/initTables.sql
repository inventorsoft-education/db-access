create table teams
 (
     id int primary key auto_increment,
     team_name varchar(100) not null,
     horse varchar(50) not null,
     rider varchar(50) not null
 );

 create table races
 (
     id int primary key auto_increment,
     rounds int not null,
     first_team int not null,
     second_team int not null,
     time_of_race varchar(50) not null,
     constraint races_first_team_fk0 foreign key (first_team) references teams (id),
     constraint races_second_team_fk1 foreign key (second_team) references teams (id)
 );
