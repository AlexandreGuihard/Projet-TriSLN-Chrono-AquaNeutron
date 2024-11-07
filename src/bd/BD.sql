drop TABLE PARTICIPER;
drop TABLE GENERER;
drop TABLE CLASSEMENT;
drop TABLE ENREGISTRER;
drop TABLE CHRONOMETRAGE;
drop TABLE DOSSARD;
drop TABLE PARTICIPANT;
drop TABLE EPREUVE;
drop TABLE UTILISATEUR;

CREATE TABLE CHRONOMETRAGE (
    id_Chrono     INT NOT NULL,
    temps_depart  TIME,
    temps_arrivee TIME,
    PRIMARY KEY (id_Chrono)
);

CREATE TABLE CLASSEMENT (
    id_Classement INT NOT NULL,
    pos_generale  INT,
    pos_categorie INT,
    pos_club      INT,
    temps         TIME,
    PRIMARY KEY (id_Classement)
);

CREATE TABLE DOSSARD (
    num_dossard    INT,
    id_Participant INT,
    PRIMARY KEY (num_Dossard)
);

CREATE TABLE ENREGISTRER (
    id_Chrono   INT,
    num_Dossard INT,
    PRIMARY KEY (id_Chrono, num_Dossard)
);

CREATE TABLE EPREUVE (
    id_Epreuve   INT,
    nom_Epreuve  VARCHAR(42),
    format       VARCHAR(42),
    categorie    BOOLEAN,
    heure_Depart TIME,
    prix         INT,
    PRIMARY KEY (id_Epreuve)
);

CREATE TABLE GENERER (
    id_Classement  INT,
    id_Epreuve     INT,
    id_Participant INT,
    PRIMARY KEY (id_Classement, id_Epreuve, id_Participant)
);

CREATE TABLE PARTICIPANT (
    id_Participant INT,
    nom            VARCHAR(42),
    prenom         VARCHAR(42),
    categorie      VARCHAR(42),
    sexe           VARCHAR(42),
    email          VARCHAR(42),
    ville          VARCHAR(42),
    certification  BOOLEAN,
    num_Tel        INT,
    club           VARCHAR(42),
    num_Licence    INT,
    date_Naissance DATE,
    nom_Equipe     VARCHAR(42),
    licence        BOOLEAN,
    PRIMARY KEY (id_Participant)
);

CREATE TABLE PARTICIPER (
    id_Participant INT NOT NULL,
    id_Epreuve     INT NOT NULL,
    payee          BOOLEAN,
    PRIMARY KEY (id_Participant, id_Epreuve)
);

CREATE TABLE UTILISATEUR (
    identifiant    VARCHAR(42),
    mot_de_passe   VARCHAR(42),
    PRIMARY KEY (identifiant)
);

ALTER TABLE PARTICIPER  ADD FOREIGN KEY (id_Epreuve)     REFERENCES EPREUVE (id_Epreuve);
ALTER TABLE PARTICIPER  ADD FOREIGN KEY (id_Participant) REFERENCES PARTICIPANT (id_Participant);

ALTER TABLE GENERER     ADD FOREIGN KEY (id_Participant) REFERENCES PARTICIPANT (id_Participant);
ALTER TABLE GENERER     ADD FOREIGN KEY (id_Epreuve)     REFERENCES EPREUVE (id_Epreuve);
ALTER TABLE GENERER     ADD FOREIGN KEY (id_Classement)  REFERENCES CLASSEMENT (id_Classement);


ALTER TABLE DOSSARD     ADD FOREIGN KEY (id_Participant) REFERENCES PARTICIPANT (id_Participant);

ALTER TABLE ENREGISTRER ADD FOREIGN KEY (num_Dossard)    REFERENCES DOSSARD (num_Dossard);
ALTER TABLE ENREGISTRER ADD FOREIGN KEY (id_Chrono)      REFERENCES CHRONOMETRAGE (id_Chrono);