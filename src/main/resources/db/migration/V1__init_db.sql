create table animal
(
    id       bigint  not null auto_increment,
    age      integer not null,
    color    integer,
    gender   integer,
    images   varchar(255),
    name     varchar(255),
    breed_id bigint,
    user_id  bigint,
    primary key (id)
);

create table breed
(
    id      bigint not null auto_increment,
    name    varchar(255),
    type_id bigint,
    primary key (id)
);

create table type
(
    id   bigint not null auto_increment,
    type varchar(255),
    primary key (id)
);

create table users
(
    id        bigint       not null auto_increment,
    address   varchar(255),
    city      varchar(255) not null,
    email     varchar(255) not null,
    name      varchar(255) not null,
    password  varchar(255) not null,
    phone     varchar(255) not null,
    role      varchar(255) not null,
    user_name varchar(255) not null,
    primary key (id)
);
alter table animal
    add constraint FK5vuppijm6mptl6xm5g9jhegwh foreign key (breed_id) references breed (id);
alter table animal
    add constraint FKk0qknpb7ssejf7ioliooqykqb foreign key (user_id) references users (id);
alter table breed
    add constraint FK30h5iofrmeyaw49dhel5cbra0 foreign key (type_id) references type (id);