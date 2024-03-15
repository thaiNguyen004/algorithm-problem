create table USERS (
    id bigint auto_increment primary key,
    first_name nvarchar(50) not null,
    last_name nvarchar(50) not null,
    email varchar(500) not null,
    username varchar(50) not null,
    password varchar(100) not null,
    role varchar(20) not null
);