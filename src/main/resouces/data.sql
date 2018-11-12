CREATE TABLE emails
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    address varchar(60),
    subject varchar(100),
    text varchar(512),
    date TIMESTAMP
);
