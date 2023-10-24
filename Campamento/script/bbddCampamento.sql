-- Base de datos: 'i12brmac'

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Asistente'
-- 

DROP TABLE IF EXISTS Asistente;
CREATE TABLE IF NOT EXISTS Asistente (

    Id int(8) primary key,
	nombreApellidos varchar2(64) not null,
	fechaNacimiento date not null,
	atencionEspecial varchar2(2) not null,
	constraint ck_atencionEspecial CHECK (atencionEspecial in ('Si','No')),	
)

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Monitor'
-- 
DROP TABLE IF EXISTS Monitor;
CREATE TABLE IF NOT EXISTS Monitor (

    Id int(8) primary key,
	nombreApellidos varchar2(64) not null,
	atencionEspecial varchar2(2) not null,
	constraint ck_atencionEspecial CHECK (atencionEspecial in ('Si','No')),	
)

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Actividad'
-- 
DROP TABLE IF EXISTS Actividad;
CREATE TABLE IF NOT EXISTS Actividad (

    nombre varchar2(64) primary key,
	nivelEducativo varchar2(64) not null,
    horario varchar2(32) not null,
    numMaxAsistentes number(14) not null,
    numeroMonitores number(14) not null,
    --Queda pendiente representar los monitores de una actividad 
	constraint ck_NivelEducativo CHECK (nivelEducativo in ('Infantil', 'Juvenil', 'Adolescente')),
    constraint ck_Horario CHECK (horario in ('Maniana','Tarde')),	
)

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Campamento'
-- 
DROP TABLE IF EXISTS Campamento;
CREATE TABLE IF NOT EXISTS Campamento (

    Id int(8) primary key,
    fechaInicio date not null,
    fechaFin date not null,
	nivelEducativo varchar2(64) not null,
    numMaxAsistentes number(14) not null,
    --Queda pendiente representar los monitores responsables
    --Queda pendiente representar las actividades
	constraint ck_NivelEducativo CHECK (nivelEducativo in ('Infantil', 'Juvenil', 'Adolescente')),
)

-- --------------------------------------------------------
-- 
-- Estructura de tabla para la tabla 'Inscripcion'
-- 
DROP TABLE IF EXISTS Inscripcion;
CREATE TABLE IF NOT EXISTS Inscripcion (

    IdParticipante int(8) primary key,
    IdCampamento int(8) not null,
    fecha date not null,
    precio real not null,
    tipo varchar2(16) not null,
    registro varchar2(16) not null,
    constraint ck_Tipo CHECK (tipo in ('Completa', 'Parcial')),
    constraint ck_Registro CHECK (registro in ('Temprano', 'Tardio')),
    constraint fk_IdParticipante foreign key (IdParticipante) references Asistente(Id)
    constraint fk_IdCampamento foreign key (IdCampamento) references Campamento(Id)
)
