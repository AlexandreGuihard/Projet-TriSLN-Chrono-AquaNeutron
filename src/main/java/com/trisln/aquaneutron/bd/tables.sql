-- Création des tables de la bd
create or replace table CATEGORIE(
                                     idCategorie int,
                                     categorie varchar(42),
                                     sousCategorie varchar(42),
                                     primary key(idCategorie)
);

create or replace table CHRONOMETRAGE (
                                          id_Chrono int not null,
                                          temps_depart time,
                                          temps_arrivee time,
                                          primary key(id_Chrono)
);

create or replace table CLASSEMENT (
                                       id_Classement int not null,
                                       pos_generale int,
                                       pos_categorie int,
                                       pos_club int,
                                       temps time,
                                       primary key(id_Classement)
);

create or replace table ENREGISTRER (
                                        id_Chrono int,
                                        num_Dossard int,
                                        primary key(id_Chrono, num_Dossard)
);

create or replace table EPREUVE (
                                    id_Epreuve int,
                                    nom_Epreuve varchar(42),
                                    format varchar(42),
                                    idCategorie int,
                                    heure_Depart time,
                                    prix int,
                                    primary key(id_Epreuve)
);

create or replace table PARTICIPANT (
                                        id_Participant int,
                                        nom varchar(42) not null,
                                        prenom varchar(42) not null,
                                        idCategorie int not null,
                                        sexe varchar(42) not null,
                                        email varchar(42),
                                        ville varchar(42),
                                        certification boolean not null,
                                        num_Tel varchar(10) not null,
                                        club varchar(42),
                                        num_Licence int,
                                        date_Naissance date not null,
                                        nom_Equipe varchar(42),
                                        licence boolean not null,
                                        primary key(id_Participant)
);

create or replace table DOSSARD (
                                    num_dossard int,
                                    id_Participant int,
                                    primary key(num_Dossard)
);

create or replace table GENERER (
                                    id_Classement int,
                                    id_Epreuve int,
                                    id_Participant int,
                                    primary key(id_Classement, id_Epreuve, id_Participant)
);

create or replace table PARTICIPER (
                                       id_Participant int not null,
                                       id_Epreuve int not null,
                                       payee boolean,
                                       primary key(id_Participant, id_Epreuve)
);

create or replace table UTILISATEUR (
                                        identifiant varchar(42),
                                        email varchar(254) unique,
                                        mot_de_passe varchar(42),
                                        primary key(identifiant)
);

-- Ajout des foreign keys

alter table PARTICIPER add foreign key (id_Epreuve) references EPREUVE (id_Epreuve);
alter table PARTICIPER add foreign key (id_Participant) references PARTICIPANT (id_Participant);

alter table GENERER add foreign key (id_Participant) references PARTICIPANT (id_Participant);
alter table GENERER add foreign key (id_Epreuve) references EPREUVE (id_Epreuve);
alter table GENERER add foreign key (id_Classement) references CLASSEMENT (id_Classement);

alter table DOSSARD add foreign key (id_Participant) references PARTICIPANT (id_Participant);

alter table ENREGISTRER add foreign key (num_Dossard) references DOSSARD (num_Dossard);
alter table ENREGISTRER add foreign key (id_Chrono) references CHRONOMETRAGE (id_Chrono);

alter table PARTICIPANT add foreign key (idCategorie) references CATEGORIE (idCategorie);

alter table EPREUVE add foreign key (idCategorie) references CATEGORIE (idCategorie);