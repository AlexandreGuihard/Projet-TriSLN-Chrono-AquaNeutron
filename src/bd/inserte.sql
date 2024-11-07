INSERT INTO PARTICIPER (INT id_Participant,INT id_Epreuve,BOOLEAN payee) VALUES 
(value1, value2, value3, ...); 

INSERT INTO GENERER (INT id_Classement,INT id_Epreuve,INT id_Participant,) VALUES 
(value1, value2, value3, ...); 

INSERT INTO CLASSEMENT (INT id_Classement,INT pos_generale,INT pos_categorie,INT pos_club,INT temps) VALUES 
(value1, value2, value3, ...); 

INSERT INTO ENREGISTRER (id_Chrono   INT,num_Dossard INT,) VALUES 
(value1, value2, value3, ...); 

INSERT INTO CHRONOMETRAGE (INT id_Chrono ,TIME temps_depart,TIME temps_arrivee) VALUES 
(value1, value2, value3, ...); 

INSERT INTO DOSSARD (INT num_dossard,INT id_Participant) VALUES 
(value1, value2, value3, ...); 

INSERT INTO EPREUVE (INT id_Epreuve,VARCHAR(42) nom_Epreuve,VARCHAR(42) format,BOOLEAN categorie,TIME heure_Depart,INT prix,) VALUES 
(value1, value2, value3, ...); 

INSERT INTO PARTICIPANT (INT id_Participant,VARCHAR(42) nom,VARCHAR(42) prenom,VARCHAR(42) categorie,VARCHAR(42) sexe,VARCHAR(42) email,VARCHAR(42) ville,BOOLEAN certification,INT num_Tel,VARCHAR(42) club,INT num_Licence,DATE date_Naissance,VARCHAR(42) nom_Equipe,BOOLEAN licence,) VALUES 
(value1, value2, value3, ...); 

INSERT INTO UTILISATEUR ( VARCHAR(42) identifiant,VARCHAR(42) mot_de_passe) VALUES 
(value1, value2, value3, ...); 

