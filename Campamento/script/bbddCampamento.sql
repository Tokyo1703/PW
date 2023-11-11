-- Active: 1698755137224@@127.0.0.1@3306@pw
-- Base de datos: 'i12brmac'

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Asistente'
-- 


DROP TABLE IF EXISTS Asistente;
CREATE TABLE IF NOT EXISTS Asistente (
    Id int(8) primary key,
	nombreApellidos varchar(64) not null,
	fechaNacimiento date not null,
	atencionEspecial varchar(2) not null,
	constraint ck_atencionEspecial CHECK (atencionEspecial in ('Si','No'))	
);

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Monitor'
-- 
DROP TABLE IF EXISTS Monitor;
CREATE TABLE IF NOT EXISTS Monitor (
    Id int(8) primary key,
	nombreApellidos varchar(64) not null,
	atencionEspecial varchar(2) not null,
	constraint ck_atencionEspecialMonitor CHECK (atencionEspecial in ('Si','No'))
);


-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Actividad'
-- 
DROP TABLE IF EXISTS Actividad;
CREATE TABLE IF NOT EXISTS Actividad (
    nombre varchar(64) primary key,
	nivelEducativo varchar(64) not null,
    horario varchar(32) not null,
    numMaxAsistentes int(14) not null,
    numeroMonitores int(14) not null,
	constraint ck_NivelEducativo CHECK (nivelEducativo in ('Infantil', 'Juvenil', 'Adolescente')),
    constraint ck_Horario CHECK (horario in ('Manana','Tarde'))	
);


-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Campamento'
-- 
DROP TABLE IF EXISTS Campamento;
CREATE TABLE IF NOT EXISTS Campamento (
    Id int(8) primary key,
    fechaInicio date not null,
    fechaFin date not null,
	nivelEducativo varchar(64) not null,
    numMaxAsistentes int(14) not null,
    IdMonitorResponsable int(8),
    IdMonitorEspecial int(8),
	constraint ck_NivelEducativoCampamento CHECK (nivelEducativo in ('Infantil', 'Juvenil', 'Adolescente')),
    constraint fk_IdMonitorResponsable foreign key (IdMonitorResponsable) references Monitor(Id),
    constraint fk_IdMonitorEspecial foreign key (IdMonitorEspecial) references Monitor(Id)
);


-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Actividad_Monitor'
-- 
DROP TABLE IF EXISTS Actividad_Campamento;
CREATE TABLE IF NOT EXISTS Actividad_Campamento (
    nombreActividad varchar(64) not null,
	idCampamento int(8) not null,
    primary key(nombreActividad,idCampamento),
    constraint fk_Campamento foreign key (idCampamento) references Campamento(Id),
    constraint fk_nombreActividad foreign key (nombreActividad) references Actividad(nombre)
);

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Actividad_Campamento'
-- 
DROP TABLE IF EXISTS Actividad_Monitor;
CREATE TABLE IF NOT EXISTS Actividad_Monitor (
    nombreActividad varchar(64) not null,
	idMonitor int(8) not null,
    primary key(nombreActividad,idMonitor),
    constraint fk_IdMonitor foreign key (idMonitor) references Monitor(Id),
    constraint fk_nombreActividadMonitor foreign key (nombreActividad) references Actividad(nombre)
);

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Inscripcion'
-- 
DROP TABLE IF EXISTS Inscripcion;
CREATE TABLE IF NOT EXISTS Inscripcion (
    IdParticipante int(8) not null,
    IdCampamento int(8) not null,
    fecha date not null,
    precio real not null,
    tipo varchar(16) not null,
    registro varchar(16) not null,
    primary key(IdParticipante,IdCampamento),
    constraint ck_Tipo CHECK (tipo in ('Completa', 'Parcial')),
    constraint ck_Registro CHECK (registro in ('Temprano', 'Tardio')),
    constraint fk_IdParticipante foreign key (IdParticipante) references Asistente(Id),
    constraint fk_IdCampamento foreign key (IdCampamento) references Campamento(Id)
);
