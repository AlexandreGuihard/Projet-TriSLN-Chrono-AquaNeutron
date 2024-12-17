create role administrateur, spectateur, benevol;

create user admin identified by adminMdp;
create user invite;

grant all on DBguihard.* to administrateur;

grant select on DBguihard.* to benevol;
grant insert on DBguihard.COURSES to benevol;
grant insert on DBguihard.CLASSEMENT to benevol;

grant select on DBguihard.CLASSEMENT to spectateur;

grant administrateur to admin;
grant spectateur to invite;