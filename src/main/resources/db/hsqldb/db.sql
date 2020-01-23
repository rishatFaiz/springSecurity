create table users(
    username varchar(75) not null primary key,
    password varchar(150) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(75) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username));

insert into users(username, password, enabled)
 values('admin', '$2a$04$lcVPCpEk5DOCCAxOMleFcOJvIiYURH01P9rx1Y/pl.wJpkNTfWO6u',true);

insert into authorities(username, authority)
    values('admin','ROLE_ADMIN');insert into users(username, password, enabled)    values('user', '$2a$04$nbz5hF5uzq3qsjzY8ZLpnueDAvwj4x0U9SVtLPDROk4vpmuHdvG3a',true);insert into authorities(username,authority)     values('user','ROLE_USER')
