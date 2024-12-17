delimiter |
-- Getters des id max (return 1 si pas de données)
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

-- Création d'une nouvelle course
create or replace procedure createEpreuve(nomEpreuve varchar(42), format varchar(42), categorie boolean, heureDepart time, prix int)
begin
    declare newId int;
    select getAvailableIdEpreuve() into newId;
    insert into EPREUVE values(newId, nomEpreuve, format, categorie, heureDepart, prix);
end|

-- Création d'un classement
create or replace procedure createClassement(posGenerale int, posCategorie int, posClub int, temps time)
begin
    declare newId int;
    select getAvailableIdClassement() into newId;
    insert into CLASSEMENT values(newId, posGenerale, posCategorie, posClub, temps);
end|

-- Procédures sur les participants
create or replace procedure addParticipant(nom varchar(42), prenom varchar(42), categorie varchar(42), sexe varchar(42), email varchar(42), ville varchar(42), certification boolean, numTel int, club varchar(42), numLicence int, dateNaissance date, nomEquipe varchar(42), licence boolean)
begin
    declare newId int;
    select getAvailableIdParticipant() into newId;
    insert into PARTICIPANT values(newId, nom, prenom, categorie, sexe, email, ville, certification, numTel, club, numLicence, dateNaissance, nomEquipe, licence);
end|

create or replace procedure deleteParticipant(idParticipant int)
begin
    delete from PARTICIPANT where id_Participant=idParticipant;
end|

create or replace procedure updateParticipant(idParticipant int, nom varchar(42), prenom varchar(42), categorie varchar(42), sexe varchar(42), email varchar(42), ville varchar(42), certification boolean, numTel int, club varchar(42), numLicence int, dateNaissance date, nomEquipe varchar(42))
begin
    update PARTICIPANT set nom=nom, prenom=prenom, categorie=categorie, sexe=sexe, email=email, ville=ville, certification=certification, num_Tel=numTel, club=club, num_Licence=numLicence, date_Naissance=dateNaissance, nom_Equipe=nomEquipe where id_Participant=idParticipant;
end|

-- Vérification des attributs licence,numLicence,club et dateNaissance selon le type de participant
create or replace trigger checkParticipantsRelais before insert on PARTICIPANT for each row
begin
    declare msg varchar(100) default "";
    if new.licence then
        if new.club!=null or new.nom_Equipe!=null or new.num_Licence!=null then
            set msg=concat("Le participant ",new.prenom,' ',new.nom," participe à une course relais. Il ne peut avoir de club, de date de naissance et d'équipe");
            signal SQLSTATE '45000' set MESSAGE_TEXT=msg;
        end if;
    end if;
end|

create or replace trigger checkParticipantsLicenceIndiv before insert on PARTICIPANT for each row
begin
    declare msg varchar(100) default "";
    if new.nom_Equipe!=null or new.licence!=false then
        set msg=concat("Le participant ",new.prenom," ",new.nom," participe à une course avec licence individuelle. Il ne peut avoir d'équipe et de licence");
        signal SQLSTATE '45000' set MESSAGE_TEXT=msg;
    end if;
end|

create or replace trigger checkParticipantsNonLicenceIndiv before insert on PARTICIPANT for each row
begin
    declare msg varchar(100) default "";
    if new.club!=null or new.licence!=false or new.nom_Equipe!=null or new.num_Licence!=null then
        set msg=concat("Le participant ",new.prenom," ",new.nom," participe à une course sans licence individuelle. Il ne peut avoir qu'une date de naissance");
        signal SQLSTATE '45000' set MESSAGE_TEXT=msg;
    end if;
end