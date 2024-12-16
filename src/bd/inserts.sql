-- Inserts de toutes les données (générées par IA)
insert into PARTICIPANT values
(1, 'Dupont', 'Carle', 'MP', 'F', 'x.carle@yahoo.fr', 'Paris', true, '0647882565', 'SQL', 1, '2001-06-15', 'bleu', true),
(2, 'Pilaf', 'Mila', 'PO', 'F', 'mila.Pilaf@yahoo.fr', 'Orleans', true, '0692243352', 'SQL', 1, '2001-03-24', 'bleu', false),
(3, 'Sparkis', 'Devoid', 'S', 'M', 'devoid.Sparkis@yahoo.fr', 'Angers', false, '0695476132', 'Marchal', 1, '2001-06-15', 'jaune', true),
(4, 'Daniel', 'Antoine', 'MP', 'F', 'x.dupont@yahoo.fr', 'Paris', true, '0647882565', 'SQL', 1, '2001-06-15', 'rouge', true),
(5, 'Dupont', 'Dupon', 'MP', 'F', 'x.dupon@yahoo.fr', 'Paris', true, '0647882565', 'SQL', 1, '2001-06-15', 'noire', true);

insert into EPREUVE values
(1, 'Course feur', 'format feur', true, '12:00:00', 130),
(2, 'Marathon X', 'long format', false, '08:30:00', 200),
(3, 'Trail des montagnes', 'trail', true, '06:00:00', 150),
(4, 'Sprint 100m', 'sprint', false, '10:00:00', 50),
(5, 'Course nocturne', 'nocturne', true, '21:00:00', 100),
(6, 'Randonnée nature', 'découverte', true, '09:00:00', 75),
(7, 'Ultra-marathon', 'ultra format', false, '04:30:00', 300);

insert into PARTICIPER values
(1, 1, true),
(2, 1, true),
(3, 1, true),
(4, 1, true),
(5, 1, true),
(2, 2, true),
(3, 3, false),
(4, 4, false),
(5, 5, true); 

-- numero qui marche 0692243352
insert into UTILISATEUR values
("test", "test"),
("admin", "admin"),
("siecle", "siecle");