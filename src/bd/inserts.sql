-- Inserts de toutes les données (générées par IA)
INSERT INTO PARTICIPANT (id_Participant, nom, prenom, categorie, sexe, email, ville, certification, num_Tel, club, num_Licence, date_Naissance, nom_Equipe, licence) VALUES
(1, 'Dupont', 'Carle', 'MP', 'F', 'x.carle@yahoo.fr', 'Paris', TRUE, '0647882565', 'SQL', 1, '2001-06-15', 'bleu', TRUE),
(2, 'Pilaf', 'Mila', 'PO', 'F', 'mila.Pilaf@yahoo.fr', 'Orleans', TRUE, '0692243352', 'SQL', 1, '2001-03-24', 'bleu', FALSE),
(3, 'Sparkis', 'Devoid', 'S', 'M', 'devoid.Sparkis@yahoo.fr', 'Angers', FALSE, '0695476132', 'Marchal', 1, '2001-06-15', 'jaune', TRUE),
(4, 'Daniel', 'Antoine', 'MP', 'F', 'x.dupont@yahoo.fr', 'Paris', TRUE, '0647882565', 'SQL', 1, '2001-06-15', 'rouge', TRUE),
(5, 'Dupont', 'Dupon', 'MP', 'F', 'x.dupon@yahoo.fr', 'Paris', TRUE, '0647882565', 'SQL', 1, '2001-06-15', 'noire', TRUE);

INSERT INTO EPREUVE (id_Epreuve, nom_Epreuve, format, categorie, heure_Depart, prix) VALUES
(1, 'Course feur', 'format feur', TRUE, '12:00:00', 130),
(2, 'Marathon X', 'long format', FALSE, '08:30:00', 200),
(3, 'Trail des montagnes', 'trail', TRUE, '06:00:00', 150),
(4, 'Sprint 100m', 'sprint', FALSE, '10:00:00', 50),
(5, 'Course nocturne', 'nocturne', TRUE, '21:00:00', 100),
(6, 'Randonnée nature', 'découverte', TRUE, '09:00:00', 75),
(7, 'Ultra-marathon', 'ultra format', FALSE, '04:30:00', 300);

INSERT INTO PARTICIPER (id_Participant, id_Epreuve, payee) VALUES
(1, 1, TRUE),
(2, 1, TRUE),
(3, 1, TRUE),
(4, 1, TRUE),
(5, 1, TRUE),
(2, 2, TRUE),
(3, 3, FALSE),
(4, 4, FALSE),
(5, 5, TRUE); 

-- numero qui marche 0692243352
INSERT INTO UTILISATEUR (identifiant, mot_de_passe) VALUES
("test", "test"),
("admin", "admin"),
("siecle", "siecle");