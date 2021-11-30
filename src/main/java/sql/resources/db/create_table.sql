CREATE TABLE teams(
    id INTEGER NOT NULL,
    name VARCHAR(50),
    coach_id VARCHAR(50),
    captain_id VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE if not exists results(
    round VARCHAR(10),
    team_one_name VARCHAR(50),
    team_two_name VARCHAR(50),
    score VARCHAR(10)
);

CREATE TABLE if not exists tournament_winners(
    id SERIAL PRIMARY KEY,
    team_name VARCHAR(50)
);
