-- tablas
delete from factor_estimacion;
delete from actor;
delete from caso_de_uso;
delete from cronograma;
delete from estimacion;

delete from lval;
delete from plataforma;
delete from factor;
delete from tarea;
delete from proveedor;
delete from punto;

-- tabla de valor
-- complejidad
insert into lval (tipolval, codlval, valor) values ('Complejidad', 1,'Baja');
insert into lval (tipolval, codlval, valor) values ('Complejidad', 2,'Media');
insert into lval (tipolval, codlval, valor) values ('Complejidad', 3,'Alta');
-- tipo de costo
insert into lval (tipolval, codlval, valor) values ('TipoCosto', 1,'Calculado');
insert into lval (tipolval, codlval, valor) values ('TipoCosto', 2,'Fijo');
insert into lval (tipolval, codlval, valor) values ('TipoCosto', 3,'Gestión');
-- tipo de factor
insert into lval (tipolval, codlval, valor) values ('TipoFactor', 1,'Ambiente');
insert into lval (tipolval, codlval, valor) values ('TipoFactor', 2,'Técnico');
-- tipo de punto
insert into lval (tipolval, codlval, valor) values ('TipoPunto', 1,'Actor');
insert into lval (tipolval, codlval, valor) values ('TipoPunto', 2,'Caso de Uso');
-- parametro de desviación
insert into lval (tipolval, codlval, valor) values ('Desviación', 1, '0.3');
-- eds
insert into lval (tipolval, codlval, valor) values ('EDS', 1,'Angela Valencia');
insert into lval (tipolval, codlval, valor) values ('EDS', 2,'Diego Ccallo');
insert into lval (tipolval, codlval, valor) values ('EDS', 3,'Edson Jimenez');
insert into lval (tipolval, codlval, valor) values ('EDS', 4,'Hernán George');
insert into lval (tipolval, codlval, valor) values ('EDS', 5,'Kenji Dettleff');
insert into lval (tipolval, codlval, valor) values ('EDS', 6,'Miguel Payet');
insert into lval (tipolval, codlval, valor) values ('EDS', 7,'Patricia Quiñónez');

-- plataformas
insert into plataforma (idplataforma, nombre, factorproductividad) values (1, 'Acsel/X', 28); -- Oracle Forms, Reports, PLSQL...
insert into plataforma (idplataforma, nombre, factorproductividad) values (2, 'GuideWire', 28); -- Gosu
insert into plataforma (idplataforma, nombre, factorproductividad) values (3, 'Apps Lotus Notes', 28);
insert into plataforma (idplataforma, nombre, factorproductividad) values (4, 'Novasys', 28); -- Powerbuilder, WS, PLSQL
insert into plataforma (idplataforma, nombre, factorproductividad) values (5, 'Apps .NET', 28); 
insert into plataforma (idplataforma, nombre, factorproductividad) values (6, 'Apps Java', 28);
insert into plataforma (idplataforma, nombre, factorproductividad) values (7, 'Business Objects', 28); 
insert into plataforma (idplataforma, nombre, factorproductividad) values (8, 'BPM', 28);
insert into plataforma (idplataforma, nombre, factorproductividad) values (9, 'HP Exstream', 28);

-- factores
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (1, 2, 'Sistema Distribuido', 2, 0, 5, '¿El sistema tiene una arquitectura centralizada o distribuida?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (2, 2, 'Desempeño, Tiempo de Respuesta', 1, 0, 5, '¿El cliente del sistema necesita que sea éste rápido? ¿Es el tiempo de respuesta un criterio importante?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (3, 2, 'Eficiencia de Usuario Final', 1, 0, 5, '¿La aplicación se debe diseñar para incrementar la eficiencia del usuario final?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (4, 2, 'Grado de Complejidad del Procesamiento Interno', 1, 0, 5, '¿La aplicación tiene lógica elaborada o procesamiento matemático?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (5, 2, 'Reusabilidad del Código', 1, 0, 5, '¿Se requiere cuidar la reusabilidad?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (6, 2, 'Facilidad de Instalación', 0.5, 0, 5, '¿El cliente está buscando facilidad en la instalación?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (7, 2, 'Portabilidad', 2, 0, 5, '¿Se busca una implantación que soporte diversas plataformas tecnológicas?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (8, 2, 'Facilidad al Cambio', 1, 0, 5, '¿Se busca una alta capacidad de adecuación en el futuro?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (9, 2, 'Concurrencia', 1, 0, 5, '¿Se busca que una gran cantidad de usuarios trabajen bajo características de bloqueo a secciones críticas de datos?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (10, 2, 'Características Especiales Seguridad', 1, 0, 5, '¿Se busca tener mecanismos de seguridad?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (11, 2, 'Proporciona Acceso Directo a Software de Terceros', 1, 0, 5, '¿El proyecto depende del uso de software de terceros?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (12, 2, 'Se requieren facilidades de Capacitación al usuario', 1, 0, 5, '¿Qué necesidades y facilidades de capacitación hacia los usuarios se requieren sobre el sistema o la aplicación?');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (13, 2, 'Grado de complejidad del proceso de certificación', 1, 0, 5, 'Grado de complejidad del proceso de certificación');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (14, 1, 'Familiaridad con el modelo del proyecto utilizado', 1.5, 0, 5, '');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (15, 1, 'Experiencia en la Aplicación', 0.5, 0, 5, '');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (16, 1, 'Experiencia en Orientación a Objetos', 1, 0, 5, '');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (17, 1, 'Capacidades del Líder Técnico', 0.5, 0, 5, '');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (18, 1, 'Motivación', 1, 0, 5, '');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (19, 1, 'Requerimientos Estables', 2, 0, 5, '');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (20, 1, 'Desarrolladores de Tiempo Parcial', -1, 0, 5, '');
INSERT INTO factor (idfactor, tipofactor, nombre, peso, minimo, maximo, descripcion) 
VALUES (21, 1, 'Lenguaje de Programación', -2, 0, 5, '');

-- proveedores
insert into proveedor (idproveedor, nombre, costohora) values (1,'Pacífico', 40);
insert into proveedor (idproveedor, nombre, costohora) values (2,'Accenture', 100);
insert into proveedor (idproveedor, nombre, costohora) values (3,'Itera', 100);

-- tareas
INSERT INTO tarea (idtarea, idproveedor, nombre, incluir, orden, porcentaje, tipocosto, disenotecnico) 
VALUES (1, 2, 'Definición Funcional', 1, 1, NULL, 2, 0);
INSERT INTO tarea (idtarea, idproveedor, nombre, incluir, orden, porcentaje, tipocosto, disenotecnico) 
VALUES (2, 2, 'Acompañamiento', 1, 2, 0.3, 4, 0);
INSERT INTO tarea (idtarea, idproveedor, nombre, incluir, orden, porcentaje, tipocosto, disenotecnico) 
VALUES (3, 2, 'Diseño Técnico', 1, 3, 0.2, 1, 1);
INSERT INTO tarea (idtarea, idproveedor, nombre, incluir, orden, porcentaje, tipocosto, disenotecnico) 
VALUES (4, 2, 'Construcción', 1, 4, 0.4, 1, 0);
INSERT INTO tarea (idtarea, idproveedor, nombre, incluir, orden, porcentaje, tipocosto, disenotecnico) 
VALUES (5, 3, 'Certificación', 1, 5, 0.3, 1, 0);
INSERT INTO tarea (idtarea, idproveedor, nombre, incluir, orden, porcentaje, tipocosto, disenotecnico) 
VALUES (6, 3, 'Pruebas de Stress', 1, 6, 0.1, 1, 0);
INSERT INTO tarea (idtarea, idproveedor, nombre, incluir, orden, porcentaje, tipocosto, disenotecnico) 
VALUES (7, 2, 'Gestión', 1, 7, 0.15, 3, 0);

-- puntos funcionales
INSERT INTO punto (tipo, complejidad, puntos) 
VALUES (1, 1, 1);
INSERT INTO punto (tipo, complejidad, puntos) 
VALUES (1, 2, 2);
INSERT INTO punto (tipo, complejidad, puntos) 
VALUES (1, 3, 3);
INSERT INTO punto (tipo, complejidad, puntos) 
VALUES (2, 1, 5);
INSERT INTO punto (tipo, complejidad, puntos) 
VALUES (2, 2, 10);
INSERT INTO punto (tipo, complejidad, puntos) 
VALUES ( 2, 3, 15);
