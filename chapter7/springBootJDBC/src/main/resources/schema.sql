drop database if exists springbootdb;

create database springbootdb;
use springbootdb;
drop table if exists users;
create table users(
                      id int UNSIGNED primary key not null auto_increment,
                      name varchar(100),
                      email varchar(100));