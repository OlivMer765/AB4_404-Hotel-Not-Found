create database HotelDB;
use HotelDB;

create table Huespedes (
    idHuesped int auto_increment,
    nombre varchar(64),
    apellido varchar(64),
    correo varchar(128),
    telefono varchar(8),
    direccion varchar(128),
    contrasena varchar(100),
    constraint PK_huesped primary key (idHuesped)
);

create table Habitaciones (
    idHabitacion int auto_increment,
    tipo enum('Simple', 'Doble', 'Suite'),
    estado enum('Disponible', 'Ocupada'),
    constraint PK_habitacion primary key (idHabitacion)
);

create table Reserva (
    idReserva int auto_increment,
    fechaEntrada datetime default current_timestamp,
    fechaSalida datetime,
    idHuesped int not null,
    idHabitacion int not null,
    constraint FK_reserva_huesped foreign key (idHuesped)
        references Huespedes(idHuesped),
    constraint FK_reserva_habitacion foreign key (idHabitacion)
        references Habitaciones(idHabitacion),
    constraint  PK_reserva primary key (idReserva)
);