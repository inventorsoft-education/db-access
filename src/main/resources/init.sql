create table IF NOT EXISTS email
(
  id integer NOT NULL,
  recipient varchar(255) NOT NULL,
  subject varchar(255) NOT NULL,
  body varchar(255) NOT NULL,
  deliver_date time_stamp NOT NULL,
  PRIMARY KEY (id)
);