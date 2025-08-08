create table if not exists users (
  id bigint generated always as identity primary key,
  username varchar(64) not null,
  password varchar(64) not null
);