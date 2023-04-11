create table Ropa
(
    id     VARCHAR not null,
    nombre VARCHAR not null
);
insert into Ropa(id,nombre) values ('1', 'Camiseta');
insert into Ropa(id,nombre) values ('2', 'Pantalon');
insert into Ropa(id,nombre) values ('3', 'Camisa');


create table Cliente
(
    email    VARCHAR not null,
    nombre VARCHAR not null
);
insert into Cliente(email,nombre) values ('rafa@email.com', 'Rafa');
insert into Cliente(email,nombre) values ('lydia@email.com', 'Lydia');
insert into Cliente(email,nombre) values ('erre@email.com', 'Alvaro');