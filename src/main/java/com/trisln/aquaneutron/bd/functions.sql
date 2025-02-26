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

-- Getter du format de la course à partir de l'id format
create or replace function getFormatFromId(idDuFormat int) returns varchar(42)
begin
    declare idCateg int;
    if sousCategorie is null then
        select idCategorie into idCateg from CATEGORIE where CATEGORIE.categorie=categorie limit 1;
    else
        select idCategorie into idCateg from CATEGORIE where CATEGORIE.categorie=categorie and CATEGORIE.sousCategorie=sousCategorie limit 1;
    end if;
    return idCateg;
end|

create or replace function getPositionCategorie(idParticipant int, idEpreuve int) 
returns int
BEGIN
    declare posCategorie int;
    declare idCateg int;
    select idCategorie into idCateg FROM PARTICIPANT WHERE id_Participant = idParticipant;
    select COUNT(*) + 1 into posCategorie FROM PARTICIPANT p JOIN GENERER g on p.id_Participant = g.id_Participant WHERE p.idCategorie = idCateg and g.id_Epreuve = idEpreuve and p.id_Participant != idParticipant and g.id_Classement in (select id_Classement FROM CLASSEMENT WHERE temps <= (select temps FROM CLASSEMENT WHERE id_Classement = g.id_Classement));
    return posCategorie;
end|

CREATE OR REPLACE FUNCTION getPositionClub(idParticipant INT, club VARCHAR(42), idEpreuve INT)
RETURNS INT
begin
    declare posClub int;
    select COUNT(*) + 1 into posClub FROM PARTICIPANT p JOIN GENERER g on p.id_Participant = g.id_Participant JOIN CLASSEMENT c on c.id_Classement = g.id_Classement WHERE p.club = club and g.id_Epreuve = idEpreuve and p.id_Participant != idParticipant and c.temps <= (select temps FROM CLASSEMENT WHERE id_Classement = (select id_Classement FROM GENERER WHERE id_Participant = idParticipant and id_Epreuve = idEpreuve limit 1));
    return posClub;
end|




-- Getter de l'id du format à partir du nom du format
CREATE OR REPLACE FUNCTION getIdFormatFromFormat(formatInput VARCHAR(42)) 
RETURNS INT
BEGIN
    DECLARE idFormat INT;

    -- Recherche dans la table FORMATCOURSE avec la colonne format
    SELECT idFormat INTO idFormat
    FROM FORMATCOURSE
    WHERE format = formatInput;

    RETURN idFormat;
END |

-- Getter de l'id de la catégorie à partir de la catégorie et de la sous catégorie si non null
CREATE OR REPLACE FUNCTION getIdCategorie(categorie VARCHAR(42), sousCategorie VARCHAR(42)) 
RETURNS INT
BEGIN
    DECLARE idCateg INT;
    IF sousCategorie IS NULL THEN
        SELECT idCategorie INTO idCateg FROM CATEGORIE WHERE CATEGORIE.categorie = categorie LIMIT 1;
    ELSE
        SELECT idCategorie INTO idCateg FROM CATEGORIE WHERE CATEGORIE.categorie = categorie AND CATEGORIE.sousCategorie = sousCategorie LIMIT 1;
    END IF;
    IF idCateg IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Catégorie ou sous-catégorie introuvable';
    END IF;
    RETURN idCateg;
END |

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
create or replace procedure createEpreuve(nomEpreuve varchar(42), format varchar(42), categorie varchar(42), heureDepart time, prix int)
begin
    declare newId int;
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

create or replace procedure updateParticipant(idParticipant int, nom varchar(42), prenom varchar(42), categorie varchar(42), sousCategorie varchar(42), sexe varchar(42), email varchar(42), ville varchar(42), certification boolean, numTel int, club varchar(42), numLicence int, dateNaissance date, nomEquipe varchar(42))
begin
    declare idCateg int;
    select getIdCategorie(categorie, sousCategorie) into idCateg;
    update PARTICIPANT set nom=nom, prenom=prenom, idCategorie=idCateg, sexe=sexe, email=email, ville=ville, certification=certification, num_Tel=numTel, club=club, num_Licence=numLicence, date_Naissance=dateNaissance, nom_Equipe=nomEquipe where id_Participant=idParticipant;
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

-- Vérifie si le participant participe à la course à partir de son numéro de dossard
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