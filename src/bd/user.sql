create user admin1 identified by mdpAdmin;

create role administrateur, benevol, spectateur;
grant all on DBguihard.* to administrateur;
grant select on DBguihard.* to benevol;
grant insert on DBguihard.CLASSEMENT to benevol;
grant insert on DBguihard.EPREUVE to benevol;
grant select on DBguihard.CLASSEMENT to spectateur;

grant administrateur to admin1;
