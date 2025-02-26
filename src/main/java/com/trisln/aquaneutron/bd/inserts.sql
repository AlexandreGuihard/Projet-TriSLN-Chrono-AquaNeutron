-- Inserts de toutes les données
insert into CATEGORIE (idCategorie, categorie, sousCategorie) values
                                                                  (1, 'MP', null),
                                                                  (2, 'PO', null),
                                                                  (3, 'PU', null),
                                                                  (4, 'BE', null),
                                                                  (5, 'MI', null),
                                                                  (6, 'CA', null),
                                                                  (7, 'JU', null),
                                                                  (8, 'S', 'S1'),
                                                                  (9, 'S', 'S2'),
                                                                  (10, 'S', 'S3'),
                                                                  (11, 'S', 'S4'),
                                                                  (12, 'V', 'V1'),
                                                                  (13, 'V', 'V2'),
                                                                  (14, 'V', 'V3'),
                                                                  (15, 'V', 'V4'),
                                                                  (16, 'V', 'V5'),
                                                                  (17, 'V', 'V6'),
                                                                  (18, 'V', 'V7');

insert into PARTICIPANT values
                            (1, 'Dupont', 'Carle', 1, 'F', 'x.carle@yahoo.fr', 'Paris', true, '0647882565', null, null, '2001-01-20', 'bleu', true),-- Relais
                            (2, 'Pilaf', 'Mila', 2, 'F', 'mila.Pilaf@yahoo.fr', 'Orleans', true, '0692243352', null, null, '2001-03-24', null, false),-- Non licence individuel
                            (3, 'Sparkis', 'Devoid', 8, 'M', 'devoid.Sparkis@yahoo.fr', 'Angers', false, '0695476132', null, null, '2001-02-03', 'jaune', true),-- Relais
                            (4, 'Disney', 'Antoine', 1, 'F', 'disant@mick.fr', 'Paris', true, '0647882565', 'SQL', 2, '2001-01-18', null, false),-- Licence individuel
                            (5, 'Dupont', 'Dupon', 1, 'F', 'x.dupon@yahoo.fr', 'Paris', true, '0647882566', 'SQL', 3, '2001-06-15', null, false),-- Licence individuel
                            (6, 'Ping', 'Alex', 8, 'M', 'ping@toto.com', 'Bourges', true, '0642693366', 'Club Bg', 1, '2005-10-03', null, false),-- Licence individuel
                            (7, 'Cable', 'Romain', 6, 'M', 'ctouche@mail.fr', 'Chateauroux', false, '0622568710', 'Club Bg', 4, '2005-04-23', null, false),-- Licence individuel
                            (8, 'Trigger', 'Clement', 1, 'M', 'donet@test.com', 'Orleans', false, '0623891245', null, null, '2023-12-25', null, false),-- Non licence individuel
                            (9, 'Pdf', 'Amine', 6, 'M', 'gen@gmail.com', 'Orleans', true, '0638991660', null, null, '2002-01-18', 'rouge', true);-- Relais

insert into EPREUVE values
                        (1, 'Course feur', 'Relais', 3, '12:00:00', 130),
                        (2, 'Marathon X', 'S', 6, '08:30:00', 200),
                        (3, 'Trail des montagnes', 'XS', 10, '06:00:00', 150),
                        (4, 'Sprint 100m', 'M', 5, '10:00:00', 50),
                        (5, 'Course nocturne', 'XS', 2, '21:00:00', 100),
                        (6, 'Randonnée pour débutants', 'S', 6, '09:00:00', 75),
                        (7, 'Ultra-marathon', 'Relais', 9, '04:30:00', 300);

insert into PARTICIPER values
                           (1, 1, true),
                           (2, 1, true),
                           (3, 1, true),
                           (4, 1, true),
                           (5, 1, true),
                           (2, 2, true),
                           (3, 3, false),
                           (4, 4, false),
                           (5, 5, true),
                           (6, 1, true),
                           (6, 4, true),
                           (7, 5, true),
                           (7, 7, true),
                           (8, 6, false),
                           (9, 2, true),
                           (9, 7, true);


insert into CLASSEMENT values
                           (1, 1, 2, 4, "12:00"),
                           (2, 4, 1, 3, "30:00"),
                           (3, 2, 3, 1, "15:30"),
                           (4, 3, 4, 2, "23:48");

insert into UTILISATEUR values
                             ("test", "voivenelromain@gmail.com" , "test", "Bénévole", "TestNom", "TestPrenom"),
                            ("admin", "a@a.com", "admin", "Admin", "Administrateur", "Administrateur"),
                            ("siecle", "b@b.com", "siecle", "Bénévole", "Toto", "Titi"),
                            ("ping", "guihardalex98@gmail.com", "ping", "Administrateur",  "Ping", "Pong");

insert into DOSSARD values
                            (101,1),
                            (102,2),
                            (103,3),
                            (104,4),
                            (105,5),
                            (106,6),
                            (107,7),
                            (108,8),
                            (109,9);

insert into GENERER values
                        (1, 1, 2),
                        (2, 4, 1),
                        (3, 2, 3),
                        (4, 3, 4);