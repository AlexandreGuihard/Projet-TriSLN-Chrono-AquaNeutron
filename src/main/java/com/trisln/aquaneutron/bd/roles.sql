create role administrateur, spectateur, benevol;

create user admin identified by adminMdp;
create user invite;

grant all on DBTriSLN.* to administrateur;

grant select on DBTriSLN.* to benevol;
grant insert on DBTriSLN.COURSES to benevol;
grant insert on DBTriSLN.CLASSEMENT to benevol;

grant select on DBTriSLN.CLASSEMENT to spectateur;

grant administrateur to admin;
grant spectateur to invite;