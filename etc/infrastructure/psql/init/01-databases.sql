create schema if not exists public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create table if not exists waiters
(
    id varchar(36) not null
        constraint waiters_pkey
        primary key,
    name varchar(255) not null
);

alter table waiters owner to postgres;

create table if not exists drinks
(
    id varchar(36) not null
        constraint drinks_pkey
        primary key,
    name varchar(255) not null,
    price double precision not null
);

alter table drinks owner to postgres;

create table if not exists food
(
    id varchar(36) not null
        constraint food_pkey
        primary key,
    name varchar(255) not null,
    price double precision not null
);

alter table food owner to postgres;

create table if not exists orders
(
    id varchar(36) not null
        constraint orders_pkey
        primary key,
    waiter_id varchar(36) not null
        constraint orders_waiters_id_fk
        references waiters
        on update cascade on delete cascade,
    dinner_table integer,
    drinks json,
    food json,
    amount double precision
);

alter table orders owner to postgres;

