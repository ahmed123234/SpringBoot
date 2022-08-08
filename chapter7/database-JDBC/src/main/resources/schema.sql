use test_schema;

create table user(
username varchar(50) primary key,
email varchar(50) not null,
password varchar(10)not null,
creation_date datetime
);

insert into user values("ahmad", "ahmad@gm,ail.com", "1234", now());
insert into user values("ali", "ali@gm,ail.com", "12345", now());
insert into user values("ayham", "ayham@gm,ail.com", "123456", now());
