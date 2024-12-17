-- Inserts de toutes les données
insert into CATEGORIE (idCategorie, categorie, sousCategorie) values
(1, 'MP', null),
(2, 'PO', null),
(3, 'PU', null),
(4, 'BE', null),
(5, 'MI', null),
(6, 'CA', null),
(7, 'JU', null),
(8, 'S', "S1"),
(9, 'S', "S2"),
(10, 'S', "S3"),
(11, 'S', "S4"),
(12, 'V', "V1"),
(13, 'V', "V2"),
(14, 'V', "V3"),
(15, 'V', "V4"),
(16, 'V', "V5"),
(17, 'V', "V6"),
(18, 'V', "V7");

insert into PARTICIPANT values
(1, 'Dupont', 'Carle', 1, 'F', 'x.carle@yahoo.fr', 'Paris', true, '0647882565', 'SQL', 1, '2001-06-15', 'bleu', true),
(2, 'Pilaf', 'Mila', 2, 'F', 'mila.Pilaf@yahoo.fr', 'Orleans', true, '0692243352', 'SQL', null, '2001-03-24', 'bleu', false),
(3, 'Sparkis', 'Devoid', 8, 'M', 'devoid.Sparkis@yahoo.fr', 'Angers', false, '0695476132', 'Marchal', 1, '2001-06-15', 'jaune', true),
(4, 'Daniel', 'Antoine', 1, 'F', 'x.dupont@yahoo.fr', 'Paris', true, '0647882565', 'SQL', 1, '2001-06-15', 'rouge', true),
(5, 'Dupont', 'Dupon', 1, 'F', 'x.dupon@yahoo.fr', 'Paris', true, '0647882565', 'SQL', 1, '2001-06-15', 'noire', true);

insert into EPREUVE values
(1, 'Course feur', 'Relais', 3, '12:00:00', 130),
(2, 'Marathon X', 'S', 6, '08:30:00', 200),
(3, 'Trail des montagnes', 'XS', 10, '06:00:00', 150),
(4, 'Sprint 100m', 'M', 5, '10:00:00', 50),
(5, 'Course nocturne', 'XS', 2, '21:00:00', 100),
(6, 'Randonnée nature', 'S', 6, '09:00:00', 75),
(7, 'Ultra-marathon', 'Relais', 9, '04:30:00', 300);

insert into PARTICIPER values
(1, 1, true),
(2, 3, true),
(3, 2, true),
(4, 2, true),
(5, 2, true),
(2, 2, true),
(3, 3, false),
(4, 4, false),
(5, 5, true);

insert into CLASSEMENT values
(1, 1, 2, 4, "12:00"),
(2, 4, 1, 3, "30:00"),
(3, 2, 3, 1, "15:30"),
(4, 3, 4, 2, "23:48");

-- numero qui marche 0692243352
insert into UTILISATEUR values
("test", "test"),
("admin", "admin"),
("siecle", "siecle");