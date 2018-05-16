--Pruebas para Pareja Entity
delete from ServicioEntity_ProveedorEntity;
delete from RegaloEntity_UbicacionEntity;
delete from PagoEntity;
delete from TarjetaCreditoEntity;
delete from TareaEntity;
delete from calificacionentity;
delete from OpcionServicioEntity;
delete from RegaloEntity;
delete from InvitadoEntity;
delete from ProveedorEntity;
delete from ServicioEntity;
delete from UbicacionEntity;
delete from ParejaEntity;
delete from BodaEntity;


Alter table calificacionentity alter column id Restart with 1;

insert into ParejaEntity (correoElec, nombreInd1, nombreInd2, telefono, pago, contrasenia, nombreAbreviado, direccion) values ('fmarsland0@artisteer.com', 'Aaren', 'Ferrell', 86, 1, 'cGUDt68M', 'Agavaceae', '46 Gulseth Place');
insert into ParejaEntity (correoElec, nombreInd1, nombreInd2, telefono, pago, contrasenia, nombreAbreviado, direccion) values ('bsimcoe1@dion.ne.jp', 'Elbertina', 'Baxy', 21493925, 0, '66tNlmFd', 'Asteraceae', '936 Mendota Plaza');
insert into ParejaEntity (correoElec, nombreInd1, nombreInd2, telefono, pago, contrasenia, nombreAbreviado, direccion) values ('cgammack0@foxnews.com', 'Doralia', 'Chauncey', 57015334, 0, 'joMOxXNK', 'bgreenham0', '5892 1st Way');
insert into ParejaEntity (correoElec, nombreInd1, nombreInd2, telefono, pago, contrasenia, nombreAbreviado, direccion) values ('erought1@naver.com', 'Tammie', 'Erv', 85826502, 1, 'V39uUlSMcnS', 'rplowell1', '939 Manufacturers Place');
insert into ParejaEntity (correoElec, nombreInd1, nombreInd2, telefono, pago, contrasenia, nombreAbreviado, direccion) values ('adevita2@digg.com', 'Hermione', 'Asher', 64657474, 1, 'zOtOavmlWdc', 'ajoris3', '97 Fisk Place');


---Fin Pruebas Pareja Entity

--Pruebas BodaEntity
insert into BodaEntity (id, name, fecha, tema, religion, tipoBoda,image) values (1, 'Mafelipe', '3/29/2019', 'Playa', 'Judia', 'Boda de Bronce','https://images.pexels.com/photos/254069/pexels-photo-254069.jpeg');
insert into BodaEntity (id, name, fecha, tema, religion, tipoBoda,image) values (2,'Galen&Rose', '5/2/2018', 'Invierno', 'Ninguna', 'Ninguno','https://images.pexels.com/photos/246490/pexels-photo-246490.jpeg');
insert into BodaEntity (id, name, fecha, tema, religion, tipoBoda,image) values (3,'Roberto&Martha', '10/9/2018', 'Primavera', 'Católica', 'Boda de Oro','https://images.pexels.com/photos/34761/old-people-couple-together-connected.jpg');
---Fin Pruebas BodaEntity

--Pruebas UbicacionEntity
insert into UbicacionEntity (id, name, latitud, longitud, telefono, direccion) values (1, 'Automotive', '50.1681931', '14.0545902', 24356, '88734 Marcy Avenue');
insert into UbicacionEntity (id, name, latitud, longitud, telefono, direccion) values (2, 'Clothing', '-17.8119748', '-50.5981252', 38447, '4701 Dorton Place');
insert into UbicacionEntity (id, name, latitud, longitud, telefono, direccion) values (3, 'Home', '55.816131', '37.8768991', 85959, '976 Myrtle Center');
insert into UbicacionEntity (id, name, latitud, longitud, telefono, direccion) values (4, 'HomeLand', '55.816167', '37.8768998', 859591, '976 Myrtle Center');
insert into UbicacionEntity (id, name, latitud, longitud, telefono, direccion) values (5, 'HomeLands', '55.81617', '37.878998', 85591, '976f Myrtle Center');
---Fin Pruebas UbicacionEntity

--Pruebas para Regalo Entity

insert into RegaloEntity (id,name, comprado, imagen, boda_id) values (100, 'Cama', 1, 'https://static.pexels.com/photos/164595/pexels-photo-164595.jpeg',1);
insert into RegaloEntity (id,name, comprado, imagen, boda_id) values (200, 'Platos', 0, 'https://static.pexels.com/photos/6305/holidays-dinner-eating-lunch.jpg',1);
insert into RegaloEntity (id,name, comprado, imagen, boda_id) values (300, 'Lavadora', 0, 'https://static.pexels.com/photos/4414/black-and-white-clean-housework-launderette.jpg',1);
insert into RegaloEntity (id,name, comprado, imagen, boda_id) values (400, 'Sábanas', 0, 'http://4.bp.blogspot.com/-_alFBn_y8zo/TluyLPQfXfI/AAAAAAAAAMw/uGKk-3CKuFE/s1600/sabanas-hotel-blancas.jpg',1);
insert into RegaloEntity (id,name, comprado, imagen, boda_id) values (500, 'Cama', 1, 'https://static.pexels.com/photos/164595/pexels-photo-164595.jpeg',2);
insert into RegaloEntity (id,name, comprado, imagen, boda_id) values (600, 'Platos', 0, 'https://static.pexels.com/photos/6305/holidays-dinner-eating-lunch.jpg',2);
insert into RegaloEntity (id,name, comprado, imagen, boda_id) values (700, 'Lavadora', 0, 'https://static.pexels.com/photos/4414/black-and-white-clean-housework-launderette.jpg',2);
insert into RegaloEntity_UbicacionEntity (regalos_id,locations_id ) values (100,1);
insert into RegaloEntity_UbicacionEntity (regalos_id,locations_id ) values (100,2);
insert into RegaloEntity_UbicacionEntity (regalos_id,locations_id ) values (200,2);
insert into RegaloEntity_UbicacionEntity (regalos_id,locations_id ) values (200,3);
insert into RegaloEntity_UbicacionEntity (regalos_id,locations_id ) values (400,1);



--Pruebas para Invitado Entity

insert into InvitadoEntity (id, name, documento, correo, asistencia, categoria, boda_id) values (1,'Miguel', 90570, 'mloughnan0@noaa.gov', 'Confirmado', 'Trabajo',1);
insert into InvitadoEntity (id, name, documento, correo, asistencia, categoria, boda_id) values (2,'Maddi', 82444, 'mpettengell1@yahoo.com', 'Confirmado', 'Trabajo',1);
insert into InvitadoEntity (id, name, documento, correo, asistencia, categoria, boda_id) values (3,'Griffy', 97900, 'gdeaguirre2@sina.com.cn', 'Confirmado', 'Familia',2);



--Pruebas ProveedorEntity

insert into  ProveedorEntity (id, name, especialidad,imagen) values (100,'Andres Carne de Res', 'Ofrecemos servicios para cualquier tipo de evento que requiera el cliente, en nuestros restaurantes tenemos espacio hasta para 500 personas. En cuánto a comida nos especializamos en Comida Latina y Carnes. Tenemos música para cualquier tipo de evento.','https://images.pexels.com/photos/597422/pexels-photo-597422.jpeg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (200,'Rosas y Claveles', 'Realizamos Arreglos para cualquier con variedad de flores y vasijas decoradas. Hacemos envío a domicilio para clientes partículares, pero también hacemos arreglos para decoración de eventos.','https://images.pexels.com/photos/15239/flower-roses-red-roses-bloom.jpg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (300,'WOK', 'Nuestros restaurantes se especializan en Comida Asiática, y tenemos los mejores chefs del país para realizar los platos. Tenemos la capacidad de atender eventos de gran magnitud','https://images.pexels.com/photos/357756/pexels-photo-357756.jpeg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (400,'DJ InTheHouse', 'Soy un DJ que se espcializa en realizar mezclas de Música Moderna, aunque también tengo una variedad considerable de música para el gusto de cualquier grupo de personas.','https://images.pexels.com/photos/374703/pexels-photo-374703.jpeg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (500,'Peluquería de la 93', 'Ofrecemos servicios para estética para hombres y mujeres: Cabello, Maquillaje, Maniquire, Pediquire, entre otros.','https://images.pexels.com/photos/332688/pexels-photo-332688.jpeg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (600,'Casa de Eventos', 'Tenemos espacios variados para atender eventos al aire libre o cubierto, ofrecemos maestros de ceremonias, meseros y decoración de eventos','https://images.pexels.com/photos/206673/pexels-photo-206673.jpeg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (700,'Primi', 'Nuestros restaurantes se especializan en Comida Italiana. Tenemos servicios para comida en eventos de hasta 100 personas','https://images.pexels.com/photos/2232/vegetables-italian-pizza-restaurant.jpg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (800,'Harry Sasson', 'Soy un chef de altísima calidad y experiencia, junto con mi equipo de cocineros nos especializamos en Comida de mar y Mediterráneo. Tenemos la capacidad de atender eventos de hasta 120 personas','https://images.pexels.com/photos/629093/pexels-photo-629093.jpeg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (900,'MacroEventos', 'Nos especializamos en la organización y recreación en eventos de distintas índoles. Ofrecemos excelentes maestros de ceremonia, entretenimiento de diferentes tipos y música variada.','https://images.pexels.com/photos/5156/people-eiffel-tower-lights-night.jpg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (1000,'Iglesia Minuto de Dios', 'Realizamos Matrimonio Católicos, para todas aquellas parejas preparadas de realizar la promesa más importante de sus vidas delante de nuestro señor Dios Padre.','https://images.pexels.com/photos/161081/eucharist-body-of-christ-church-mass-161081.jpeg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (1100,'Nicolukas', 'Nos especializamos en Pasteles y Postres de altísima calidad. Tenemos la capacidad de atender eventos grandes con variedades de postres y pasteles de hasta 150 porciones','https://images.pexels.com/photos/416534/pexels-photo-416534.jpeg');
insert into  ProveedorEntity (id, name, especialidad,imagen) values (1200,'Alexis Vargas', 'Ofrecemos servicio de Alquiler y compra de vestidos para hombres y mujeres para cualquier tipo de evento de gala. También podemos realizar atuendos de acuerdo al pedido de nuestros clientes.','https://images.pexels.com/photos/313702/pexels-photo-313702.jpeg');

--Pruebas ServicioEntity
insert into ServicioEntity (id, name, descripcion, image) values (1, 'Vestidos', 'Te ofrecemos una gran variedad de opciones y proveedores para que encuentres tu vestido ideal', 'http://media.bodaclick.com/img/img_reportajes/19942_1359111008_51026360c7dda.jpg');
insert into ServicioEntity (id, name, descripcion, image) values (2, 'Flores', 'Encuentra las flores ideales para tu matrimonio, que tu día especial sea el más vivo y colorido','https://images.pexels.com/photos/306066/pexels-photo-306066.jpeg');
insert into ServicioEntity (id, name, descripcion, image) values (5, 'Catering', 'Comida para la boda: Entrada, Platos Fuertes y Picadas', 'https://static.pexels.com/photos/265903/pexels-photo-265903.jpeg');
insert into ServicioEntity (id, name, descripcion, image) values (6, 'Decoración', 'Decoración para la boda', 'https://images.pexels.com/photos/169190/pexels-photo-169190.jpeg');
insert into ServicioEntity (id, name, descripcion, image) values (7, 'Música', 'DJ o Música en vivo para la boda', 'https://images.pexels.com/photos/163219/wedding-party-dance-bride-163219.jpeg');
insert into ServicioEntity (id, name, descripcion, image) values (8, 'Entretenimiento', 'Bailes y Presentaciones', 'http://estaticos01.telva.com/blogs/planes-boda/imagenes_posts/2012/06/08/8318_625x416.jpg');
insert into ServicioEntity (id, name, descripcion, image) values (9, 'Estética', 'Maquillaje, Peinado y Manicure', 'https://images.pexels.com/photos/457701/pexels-photo-457701.jpeg');
insert into ServicioEntity (id, name, descripcion, image) values (10, 'Fotografía y Filmación', 'Fotografía y Filmación para el evento', 'https://www.dzoom.org.es/wp-content/uploads/2012/09/boda-fotografo-734x489.jpg');
insert into ServicioEntity (id, name, descripcion, image) values (11, 'Transporte', 'Transporte de los invistados para el evento', 'https://static1.squarespace.com/static/551be0c7e4b072084063e805/552d3f33e4b0f5e9c64b9b20/552d3f6ce4b0ba29bc994cab/1451948301649/127-silvercloud57_large.jpg');
insert into ServicioEntity (id, name, descripcion, image) values (12, 'Pastelería', 'Pastel y postre para el evento', 'https://images.pexels.com/photos/2226/food-couple-sweet-married.jpg');
insert into ServicioEntity (id, name, descripcion, image) values (13, 'Bebidas', 'Bebidas y Licores para el evento', 'https://images.pexels.com/photos/302515/pexels-photo-302515.jpeg');
insert into ServicioEntity (id, name, descripcion, image) values (14, 'Tipos de Boda', 'Ofrecemos diferentes tipos de boda dependiendo de la tendencia religiosa.', 'https://images.pexels.com/photos/415571/pexels-photo-415571.jpeg');

---Fin Pruebas ServicioEntity


insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (1,1200);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (2,200);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (5,100);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (5,300);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (5,800);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (6,600);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (7,400);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (7,900);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (8,600);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (8,900);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (9,500);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (12,200);
insert into ServicioEntity_ProveedorEntity (servicios_id, proveedores_id ) values (14,1000);

---Fin Pruebas ProveedorEntity

--Pruebas para OpcionServicio Entity
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (1,'Servicio de Catering para eventos entre 10 y 25 personas',250000 ,'Cualquier día de la semana',100,'https://images.pexels.com/photos/533325/pexels-photo-533325.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (2,'Servicio de Atención de Eventos para grupos de personas entre 25 y 50',3500000 ,'Lunes a Jueves',100,'https://images.pexels.com/photos/382297/pexels-photo-382297.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (3,'Servicio de Decoración con Arreglo de flores para evento, 10 arreglos de rosas o 12 arreglos de cualquier flor diferente.',200000 ,'Cualquier día', 200,'https://images.pexels.com/photos/169190/pexels-photo-169190.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (4,'Arreglo de flores de matrimonio para la esposa',30000 ,'Cualquier día', 200 ,'https://images.pexels.com/photos/540522/pexels-photo-540522.jpeg');

insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (5,'Servicio de Catering de comida asiática para eventos entre 10 y 40 personas', ,'Cualquier día de la semana',300,'https://images.pexels.com/photos/533325/pexels-photo-533325.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (6,'Servicio de Cocina en vivo, evento entre 5 y 20 personas',3500000 ,'Lunes a Jueves',300,'https://images.pexels.com/photos/382297/pexels-photo-382297.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (7,'Manejar la música para un evento durante 2 hora',400 ,'Cualquier día', 400,'https://images.pexels.com/photos/169190/pexels-photo-169190.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (8,'Manejar la música para un evento durante 4 hora',30000 ,'Cualquier día', 400 ,'https://images.pexels.com/photos/540522/pexels-photo-540522.jpeg');

insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (9,'Servicio de Catering para eventos entre 10 y 25 personas',250000 ,'Cualquier día de la semana',500,'https://images.pexels.com/photos/533325/pexels-photo-533325.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (10,'Servicio de Atención de Eventos para grupos de personas entre 25 y 50',3500000 ,'Lunes a Jueves',500,'https://images.pexels.com/photos/382297/pexels-photo-382297.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (11,'Servicio de Decoración con Arreglo de flores para evento, 10 arreglos de rosas o 12 arreglos de cualquier flor diferente.',200000 ,'Cualquier día', 600,'https://images.pexels.com/photos/169190/pexels-photo-169190.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (12,'Arreglo de flores de matrimonio para la esposa',30000 ,'Cualquier día', 600 ,'https://images.pexels.com/photos/540522/pexels-photo-540522.jpeg');

insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (13,'Servicio de Catering para eventos entre 10 y 25 personas',250000 ,'Cualquier día de la semana',700,'https://images.pexels.com/photos/533325/pexels-photo-533325.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (14,'Servicio de Atención de Eventos para grupos de personas entre 25 y 50',3500000 ,'Lunes a Jueves',700,'https://images.pexels.com/photos/382297/pexels-photo-382297.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (15,'Servicio de Decoración con Arreglo de flores para evento, 10 arreglos de rosas o 12 arreglos de cualquier flor diferente.',200000 ,'Cualquier día', 800,'https://images.pexels.com/photos/169190/pexels-photo-169190.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (16,'Arreglo de flores de matrimonio para la esposa',30000 ,'Cualquier día', 800 ,'https://images.pexels.com/photos/540522/pexels-photo-540522.jpeg');

insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (17,'Servicio de Catering para eventos entre 10 y 25 personas',250000 ,'Cualquier día de la semana',900,'https://images.pexels.com/photos/533325/pexels-photo-533325.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (18,'Servicio de Atención de Eventos para grupos de personas entre 25 y 50',3500000 ,'Lunes a Jueves',900,'https://images.pexels.com/photos/382297/pexels-photo-382297.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (19,'Servicio de Decoración con Arreglo de flores para evento, 10 arreglos de rosas o 12 arreglos de cualquier flor diferente.',200000 ,'Cualquier día', 1000,'https://images.pexels.com/photos/169190/pexels-photo-169190.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (20,'Arreglo de flores de matrimonio para la esposa',30000 ,'Cualquier día', 1000 ,'https://images.pexels.com/photos/540522/pexels-photo-540522.jpeg');

insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (21,'Servicio de Catering para eventos entre 10 y 25 personas',250000 ,'Cualquier día de la semana',1100,'https://images.pexels.com/photos/533325/pexels-photo-533325.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (22,'Servicio de Atención de Eventos para grupos de personas entre 25 y 50',3500000 ,'Lunes a Jueves',1100,'https://images.pexels.com/photos/382297/pexels-photo-382297.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (23,'Servicio de Decoración con Arreglo de flores para evento, 10 arreglos de rosas o 12 arreglos de cualquier flor diferente.',200000 ,'Cualquier día', 1200,'https://images.pexels.com/photos/169190/pexels-photo-169190.jpeg');
insert into OpcionServicioEntity(id,descripcion, costo,diasDisponibles,proveedor_id,image) values (24,'Arreglo de flores de matrimonio para la esposa',30000 ,'Cualquier día', 1200 ,'https://images.pexels.com/photos/540522/pexels-photo-540522.jpeg');


insert into OPCIONSERVICIOENTITY_BODAENTITY (opcionservicioentity_id, bodas_id ) values (1,1);
insert into OPCIONSERVICIOENTITY_BODAENTITY (opcionservicioentity_id, bodas_id ) values (1,2);
insert into OPCIONSERVICIOENTITY_BODAENTITY (opcionservicioentity_id, bodas_id ) values (1,3);
insert into OPCIONSERVICIOENTITY_BODAENTITY (opcionservicioentity_id, bodas_id ) values (2,1);
insert into OPCIONSERVICIOENTITY_BODAENTITY (opcionservicioentity_id, bodas_id ) values (2,2);
insert into OPCIONSERVICIOENTITY_BODAENTITY (opcionservicioentity_id, bodas_id ) values (3,3);
insert into OPCIONSERVICIOENTITY_BODAENTITY (opcionservicioentity_id, bodas_id ) values (3,2);


---Fin Calificacion OpcionServicio Entity

--Pruebas para Calificacion Entity
insert into calificacionentity (opcionservicio_id,comentario, calificacionNum) values (1,'Encantador, de verdad lo recomiendo mucho. El proveedor ser encarga de todo, la atención es perfecta, me hicieron sentir única y especial', 4);
insert into calificacionentity (opcionservicio_id,comentario, calificacionNum) values (1,'La mejor atención del mundooo! No busquen más opciones, esta es la ideal, todo es tan perfecto y lindo, de verdad mi pareja y yo quedamos muy felices, más de lo que esperábamos', 5);
insert into calificacionentity (opcionservicio_id,comentario, calificacionNum) values (2,'La mejor atención del mundooo! No busquen más opciones, esta es la ideal, todo es tan perfecto y lindo, de verdad mi pareja y yo quedamos muy felices, más de lo que esperábamos', 5);
insert into calificacionentity (opcionservicio_id,comentario, calificacionNum) values (3,'Horrible, muy desorganizados y cuesta muchísmimo >:C !', 1);
---Fin Calificacion Pareja Entity

--Pruebas para Tarea Entity
insert into TareaEntity(opcionservicio_id,id,aprobada, dia,nombre, ubicacion_id,image) values (1,12,0,'2/11/2017', 'Prueba de vestido',1,'http://media.bodaclick.com/img/img_reportajes/19942_1359111008_51026360c7dda.jpg');
insert into TareaEntity(opcionservicio_id,id,aprobada, dia,nombre, ubicacion_id,image) values (2,13,1,'1/2/2017', 'Floristería',1,'https://www.kukyflor.com/blog/wp-content/uploads/2014/11/flores-boda-casamiento-matrimonio-altar-novia.jpg');
insert into TareaEntity(opcionservicio_id,id,aprobada, dia,nombre, ubicacion_id,image) values (2,14,0,'3/13/2017', 'Fotografía',2,'http://img3.woman.es/8a/b4/b8/10-preguntas-novia-debe-fotografo-boda-640x422.jpg');
---Fin Calificacion Tarea Entity




---Pruebas TarjetaCreditoEntity
delete from TarjetaCreditoEntity;
insert into TarjetaCreditoEntity (numero, name, numDeSeg, fechaVen, pareja_correoElec) values (4445556667778889, 'Banca Mia', 777, '3/8/2022','fmarsland0@artisteer.com');
insert into TarjetaCreditoEntity (numero, name, numDeSeg, fechaVen, pareja_correoElec) values (7894561230789456, 'Banca Tuya', 456, '6/29/2017', 'fmarsland0@artisteer.com');
insert into TarjetaCreditoEntity (numero, name, numDeSeg, fechaVen, pareja_correoElec) values (7777771111115556, 'Banca Nuestra', 123, '1/12/2019', 'bsimcoe1@dion.ne.jp');
---Fin Pruebas TarjetaCreditoEntity

---Pruebas PagoEntity
insert into PagoEntity (id, name, montoTotal, fecha, tarjetaCredito_numero) values (1, 'Pago por mesas', 150000, '4/4/2017',4445556667778889);
insert into PagoEntity (id, name, montoTotal, fecha, tarjetaCredito_numero) values (2, 'Pago por transporte', 200000, '6/29/2017', 4445556667778889);
insert into PagoEntity (id, name, montoTotal, fecha, tarjetaCredito_numero) values (3, 'Pago por banquete', 30000, '3/14/2017', 7894561230789456);
insert into PagoEntity (id, name, montoTotal, fecha, tarjetaCredito_numero) values (4, 'Pago por sillas', 70000, '1/12/2017', 7777771111115556);
---Fin Pruebas PagoEntity