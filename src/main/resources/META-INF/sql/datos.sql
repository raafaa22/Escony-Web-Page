/*create table Ropa
(
    id     VARCHAR not null,
    nombre VARCHAR not null,
    precio NUMBER not null,
    talla VARCHAR not null
);*/
insert into Ropa(id,nombre,precio,talla) values ('1', 'Camiseta',20,'S');
insert into Ropa(id,nombre,precio,talla) values ('2', 'Pantalon',14.99,'M');
insert into Ropa(id,nombre,precio,talla) values ('3', 'Camisa',35,'L');


/*create table Cliente
(
    email    VARCHAR not null,
    nombre VARCHAR not null,
    password VARCHAR not null,
    direccion VARCHAR not null
);*/
insert into Cliente(email,nombre,password,direccion) values ('rafa@email.com', 'Rafa','serrano','micasa');
insert into Cliente(email,nombre,password,direccion) values ('lydia@email.com', 'Lydia','escony','centro');
insert into Cliente(email,nombre,password,direccion) values ('erre@email.com', 'Alvaro','contrasena','arjonilla');