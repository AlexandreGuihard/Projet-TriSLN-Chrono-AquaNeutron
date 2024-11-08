INSERT INTO PARTICIPER (INT id_Participant,INT id_Epreuve,BOOLEAN payee) VALUES (
(1, 1, TRUE),
(2, 1, TRUE),
(3, 1, TRUE),
(4, 1, TRUE),
(5, 1, TRUE),
(2, 2, TRUE),
(3, 3, FALSE),
(4, 4, FALSE),
(5, 5, TRUE)
); 

INSERT INTO GENERER (INT id_Classement,INT id_Epreuve,INT id_Participant,) VALUES (
(value1, value2, value3, ...)
); 

INSERT INTO CLASSEMENT (INT id_Classement,INT pos_generale,INT pos_categorie,INT pos_club,INT temps) VALUES (
(value1, value2, value3, ...)
); 



INSERT INTO ENREGISTRER (id_Chrono   INT,num_Dossard INT) VALUES 
(value1, value2, value3, ...); 

INSERT INTO CHRONOMETRAGE (INT id_Chrono ,TIME temps_depart,TIME temps_arrivee) VALUES 
(value1, value2, value3, ...); 

INSERT INTO DOSSARD (INT num_dossard,INT id_Participant) VALUES 
(value1, value2, value3, ...); 

INSERT INTO EPREUVE (INT id_Epreuve,VARCHAR(42) nom_Epreuve,VARCHAR(42) format,BOOLEAN categorie,TIME heure_Depart,INT prix,) VALUES 
(value1, value2, value3, ...); 

INSERT INTO PARTICIPANT (INT id_Participant,VARCHAR(42) nom,VARCHAR(42) prenom,VARCHAR(42) categorie,VARCHAR(42) sexe,VARCHAR(42) email,VARCHAR(42) ville,BOOLEAN certification,INT num_Tel,VARCHAR(42) club,INT num_Licence,DATE date_Naissance,VARCHAR(42) nom_Equipe,BOOLEAN licence,) VALUES 
(1, "Dupont", "Xavier", "MP","F","x.dupont@yahoo.fr","Paris",TRUE,0647882565,"SQL",1,TO_DATE('15-JUN-2001','DD-MON-YYYY'),"bleu",TRUE)

(2, "Pilaf", "mila", "PO","F","mila.Pilaf@yahoo.fr","Orleans",TRUE,0692243352,"SQL",1,TO_DATE('24-MARS-2001','DD-MON-YYYY'),"bleu",FALSE)
(3, "Sparkis", "devoid", "S","M","devoid.Sparkis@yahoo.fr","Angers",FALSE,0695476132,"Marchal",1,TO_DATE('15-JUN-2001','DD-MON-YYYY'),"jaune",TRUE)
(4, "Dupont", "Xavier", "MP","F","x.dupont@yahoo.fr","Paris",TRUE,0647882565,"SQL",1,TO_DATE('15-JUN-2001','DD-MON-YYYY'),"rouge",TRUE)
(5, "Dupont", "Xavier", "MP","F","x.dupont@yahoo.fr","Paris",TRUE,0647882565,"SQL",1,TO_DATE('15-JUN-2001','DD-MON-YYYY'),"noire",TRUE)
; 

-- numero qui marche 0692243352


INSERT INTO UTILISATEUR ( VARCHAR(42) identifiant,VARCHAR(42) mot_de_passe) VALUES (
("test", "test")
); 

