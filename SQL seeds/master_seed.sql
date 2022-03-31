drop schema if exists argonotes;
create schema if not exists argonotes;

set SQL_SAFE_UPDATES = 0;

-- use this schema
use argonotes;

-- Table holds authorized devices for app users (assoc_user = uuid of user)
drop table if exists devices;
create table if not exists devices (
	authorized_MAC char(12) not null primary key,
    device_type text,
    device_os varchar(30),
    assoc_user varchar(37)
);

-- Table of account associations (uuid of user, uuid of hashed password)
drop table if exists accounts;
create table if not exists accounts (
	user_acc varchar(37) not null,
    user_pass varchar(37) not null
);

-- Table to hold all user data
drop table if exists users;
create table if not exists users (
	uid varchar(37) default (uuid()) not null primary key, -- uuid of user
    alias varchar(32) not null, -- username
    first_name varchar(50) not null, 
    last_name varchar(80) not null,
    dob date,
    email varchar(128) not null,
    role_id integer not null -- role of user in software
);

create table if not exists user_state (
	uid varchar(37) not null primary key,
    last_active timestamp not null,
    is_active bool not null
);

-- Table to hold role associations (minimal)
drop table if exists roles;
create table if not exists roles (
	role_id integer not null primary key,
    role_desc varchar(50) not null
);

insert into roles(role_id, role_desc) 
values
	(0, 'ADMIN'),
    (1, 'TEACHER'),
    (2, 'STUDENT')
;



-- Table to hold clustered users
drop table if exists clusters;
create table if not exists clusters (
	cluster_id varchar(37) default (uuid()) not null primary key,
    cluster_owner varchar(37) not null,
    cluster_title varchar(120) not null
    -- ,timeout_date date 
);

drop table if exists cluster_assoc;
create table if not exists cluster_assoc (
	cluster_id varchar(37) not null,
    participant varchar(37) not null
);

drop table if exists notes;
create table if not exists notes (
	note_id varchar(37) default (uuid()) not null primary key,
	cluster_id varchar(37) not null,
    collab_id varchar(37),
    user_id varchar(37) not null,
    title text,
    contents text,
    comments text,
    updated_at datetime not null,
    is_collab boolean default False not null
);

-- use this schema when running analyses on argonotes
drop database if exists analysis;
create schema if not exists analysis;