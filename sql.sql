create table proprietaire 
(
    id serial primary key,
    nom varchar(150),
    mail varchar(150),
    mdp varchar(20) ,
    dtn date,
    corbeil int default 0 
);

