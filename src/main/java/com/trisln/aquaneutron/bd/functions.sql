delimiter |
-- Getters des prochains id disponibles (return 1 si pas de données)
create or replace function getAvailableIdEpreuve() returns int
begin
    declare idMax int;
    select ifnull(max(id_Epreuve),0) into idMax from EPREUVE;
    return idMax+1;
end|

create or replace function getAvailableIdClassement() returns int
begin
    declare idMax int;
    select ifnull(max(id_Classement),0) into idMax from CLASSEMENT;
    return idMax+1;
end|

create or replace function getAvailableIdParticipant() returns int
begin
    declare idMax int;
    select ifnull(max(id_Participant),0) into idMax from PARTICIPANT;
    return idMax+1;
end|

-- Getter de l'id de la catégorie à partir de la catégorie et de la sous catégorie si non null
create or replace function getIdCategorie(categorie varchar(42), sousCategorie varchar(42)) returns int
begin
    declare idCateg int;
    if sousCategorie is null then
        select idCategorie into idCateg from CATEGORIE where CATEGORIE.categorie=categorie limit 1;
    else
        select idCategorie into idCateg from CATEGORIE where CATEGORIE.categorie=categorie and CATEGORIE.sousCategorie=sousCategorie limit 1;
    end if;
    return idCateg;
end|

-- Getter de la catégorie à partir de l'id de la catégorie
create or replace function getCategorieFromId(idCategorie int) returns varchar(42)
begin
    declare categ varchar(42);
    select categorie into categ from CATEGORIE where CATEGORIE.idCategorie=idCategorie limit 1;
    return categ;
end|

-- Getter de la sous catégorie à partir de l'id de la catégorie
create or replace function getSousCategorieFromId(idCategorie int) returns varchar(42)
begin
    declare sousCateg varchar(42);
    select sousCategorie into sousCateg from CATEGORIE where CATEGORIE.idCategorie=idCategorie limit 1;
    return sousCateg;
end|

-- Création d'une nouvelle course
create or replace procedure createEpreuve(nomEpreuve varchar(42), format varchar(42), categorie varchar(42), sousCategorie varchar(42), heureDepart time, prix int)
begin
    declare newId int;
    declare idCateg int;
    select getIdCategorie(categorie, sousCategorie) into idCateg;
    select getAvailableIdEpreuve() into newId;
    insert into EPREUVE values(newId, nomEpreuve, format, idCateg, heureDepart, prix);
end|

-- Création d'un classement
create or replace procedure createClassement(posGenerale int, posCategorie int, posClub int, temps time)
begin
    declare newId int;
    select getAvailableIdClassement() into newId;
    insert into CLASSEMENT values(newId, posGenerale, posCategorie, posClub, temps);
end|

-- Procédures sur les participants
create or replace procedure addParticipant(nom varchar(42), prenom varchar(42), categorie varchar(42), sousCategorie varchar(42), sexe varchar(42), email varchar(42), ville varchar(42), certification boolean, numTel int, club varchar(42), numLicence int, dateNaissance date, nomEquipe varchar(42), licence boolean)
begin
    declare newId int;
    declare idCateg int;
    select getAvailableIdParticipant() into newId;
    select getIdCategorie(categorie, sousCategorie) into idCateg;
    insert into PARTICIPANT values(newId, nom, prenom, idCateg, sexe, email, ville, certification, numTel, club, realNumLicence, dateNaissance, nomEquipe, licence);
end|

create or replace procedure deleteParticipant(idParticipant int)
begin
    delete from PARTICIPANT where id_Participant=idParticipant;
end|

create or replace procedure updateParticipant(idParticipant int, nom varchar(42), prenom varchar(42), categorie varchar(42), sousCategorie varchar(42), sexe varchar(42), email varchar(42), ville varchar(42), certification boolean, numTel int, club varchar(42), numLicence int, dateNaissance date, nomEquipe varchar(42), licence boolean)
begin
    declare idCateg int;
    select getIdCategorie(categorie, sousCategorie) into idCateg;
    update PARTICIPANT set PARTICIPANT.nom=nom, PARTICIPANT.prenom=prenom, PARTICIPANT.idCategorie=idCateg, PARTICIPANT.sexe=sexe, PARTICIPANT.email=email, PARTICIPANT.ville=ville, PARTICIPANT.certification=certification, PARTICIPANT.num_Tel=numTel, PARTICIPANT.club=club, PARTICIPANT.num_Licence=numLicence, PARTICIPANT.date_Naissance=dateNaissance, PARTICIPANT.nom_Equipe=nomEquipe, PARTICIPANT.licence=licence where id_Participant=idParticipant;
end|

-- Vérification des attributs licence,numLicence,club et dateNaissance selon le type de participant
-- Fonctions pour savoir le type de participant selon certains attributs
create or replace function isParticipantsRelais(club varchar(42), nom_Equipe varchar(42), licence boolean, numLicence int) returns boolean
begin
    return club='null' and nom_Equipe!='null' and licence and numLicence=0;
end|

create or replace function isParticipantsLicenceIndiv(club varchar(42), nomEquipe varchar(42), licence boolean, numLicence int) returns boolean
begin
    return club!='null' and numLicence!=0 and nomEquipe='null' and not licence;
end|

create or replace function isParticipantsNonLicenceIndiv(club varchar(42), nomEquipe varchar(42), licence boolean, numLicence int) returns boolean
begin
    return club='null' and nomEquipe='null' and not licence and numLicence=0;
end|

create or replace procedure supprimerParticipant(id int)
begin
    delete from PARTICIPER where id_Participant=id;
    delete from GENERER where id_Participant=id;
    delete from DOSSARD where id_Participant=id;
    delete from PARTICIPANT where id_Participant=id;
end|

create or replace function isParticipantOfCourse(idDossard INT, idEpreuve INT) returns boolean
begin
    if exists (select * FROM DOSSARD JOIN PARTICIPANT ON DOSSARD.id_Participant = PARTICIPANT.id_Participant JOIN PARTICIPER ON PARTICIPANT.id_Participant = PARTICIPER.id_Participant
        WHERE DOSSARD.num_dossard = idDossard and PARTICIPER.id_Epreuve = idEpreuve
    ) then
        return FALSE;
    end if;
end|

create or replace function isParticipantOfCourse(idDossard INT, idEpreuve INT) returns boolean
begin
    if exists (select * FROM DOSSARD JOIN PARTICIPANT ON DOSSARD.id_Participant = PARTICIPANT.id_Participant JOIN PARTICIPER ON PARTICIPANT.id_Participant = PARTICIPER.id_Participant
        WHERE DOSSARD.num_dossard = idDossard and PARTICIPER.id_Epreuve = idEpreuve
    ) then
        return TRUE;
    ELSE
        return FALSE;
    end if;
end|

create or replace procedure updateParticipant(id int, nomParticipant varchar(42), prenomParticipant varchar(42), sexeParticipant varchar(42), dateNaissanceParticipant date, categorieParticipant varchar(42), sousCategorieParticipant varchar(42), clubParticipant varchar(42), nomEquipeParticipant varchar(42), emailParticipant varchar(42), telParticipant varchar(42), certificationParticipant varchar(42), numLicenceParticipant int, villeParticipant varchar(42), licenceParticipant boolean)
begin
    declare idCateg int;
    select getIdCategorie(categorieParticipant, sousCategorieParticipant) into idCateg;
    update PARTICIPANT set nom=nomParticipant, prenom=prenomParticipant, idCategorie=idCateg, sexe=sexeParticipant, email=emailParticipant, ville=villeParticipant, certification=certificationParticipant, num_tel=telParticipant, club=clubParticipant, num_Licence=numLicenceParticipant, date_Naissance=dateNaissanceParticipant, nom_Equipe=nomEquipeParticipant, licence=licenceParticipant where id_Participant=id;
end|

-- Triggers
create or replace trigger checkParticipant before insert on PARTICIPANT for each row
begin
    declare msg varchar(200) default "";
    declare participantRelais boolean default true;
    declare participantLicenceIndiv boolean default true;
    declare participantNonLicenceIndiv boolean default true;
    select isParticipantsRelais(new.club, new.nom_Equipe, new.licence, new.num_Licence) into participantRelais;
    select isParticipantsLicenceIndiv(new.club, new.nom_Equipe, new.licence, new.num_Licence) into participantLicenceIndiv;
    select isParticipantsNonLicenceIndiv(new.club, new.nom_Equipe, new.licence, new.num_Licence) into participantNonLicenceIndiv;
    if not participantRelais and not participantLicenceIndiv and not participantNonLicenceIndiv then
        set msg=concat("Le participant ", new.prenom, " ", new.nom, " ne correspond à aucun type de participant connu (relais, licence individuelle, sans licence individuelle)");
        signal SQLSTATE '45000'set MESSAGE_TEXT=msg;
    end if;
end|