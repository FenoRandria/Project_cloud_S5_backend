create database cloud; 
--encore sujet à d'autres modifications
--table referencement de tout les propriétaires
create table proprietaire 
(
    id_proprietaire serial primary key,
    nom varchar(150),
    mail varchar(150),
    mdp varchar(20) ,
    dtn date,
    corbeille int default 0 
);
insert into proprietaire (nom,mail,mdp,dtn) values ('alice','alice@gmail.com','mdp','2002-10-02');
--table referencement de tout les terrain corbeille (0 inserer) (1 valider) (2 supprimer)
create table terrain(
    id_terrain serial primary key,
    id_proprietaire int,
    desc_terrain TEXT,
    coord_location varchar(40),
    surface float,
    corbeille int default 0 
);

insert into terrain (id_proprietaire,desc_terrain,coord_location,surface) values (1,'description','coordessai',10);

--table referencement des photos des terrains
create table photos_terrain(
    id_photos_terrain serial primary key,
    id_terrain int,
    photo varchar(50),
    FOREIGN KEY(id_terrain) 
    REFERENCES terrain(id_terrain)
);

--table referencement des différentes categories de culture
create table categorie_culture(
    id_categorie_culture serial primary key,
    nomcategorie varchar(50)
);
alter table categorie_culture add column corbeille int default 0;

--table referencement des différent types de plantes appartenant à la categorie
create table plante(
    id_plante serial primary key,
    id_categorie_culture int,
    nom_plante varchar(50),
    prixachat float,
    prixvente float,
    sprite_plante varchar(30),
    placeingamemaker int,
    FOREIGN KEY(id_categorie_culture) 
    REFERENCES categorie_culture(id_categorie_culture)
);

--table referencement de toutes les parcelles d'un terrain
--rendement quantité de culture récoltée par cette parcelle
create table parcelle(
    id_parcelle serial primary key,
    id_terrain int,
    surface float,
    rendement float,
    FOREIGN KEY(id_terrain) 
    REFERENCES terrain(id_terrain)
);
insert into parcelle (id_terrain,rendement) values (1,200);

--table referencement des categories qu'une parcelle peut accepter 
create table categories_parcelle(
    id_categories_parcelle serial primary key,
    id_parcelle int,
    id_categorie_culture int,
    corbeille int default 0,
    FOREIGN KEY(id_parcelle) 
    REFERENCES parcelle(id_parcelle),
    FOREIGN KEY(id_categorie_culture) 
    REFERENCES categorie_culture(id_categorie_culture)

);

--types d'action planter/recolter
--séparer en 2 tables recolte et achat

create table recolte(
    id_recolte serial primary key,
    id_parcelle int,
    id_plante int,
    dateaction timestamp,
    FOREIGN KEY(id_parcelle) 
    REFERENCES parcelle(id_parcelle),
    FOREIGN KEY (id_plante)
    REFERENCES plante(id_plante)
);

create table vente(
    id_action serial primary key,
    id_parcelle int,
    id_plante int,
    dateaction timestamp,
    FOREIGN KEY(id_parcelle) 
    REFERENCES parcelle(id_parcelle),
    FOREIGN KEY (id_plante)
    REFERENCES plante(id_plante)
);

create table historique









SELECT 
    p.id_terrain,
    DISTINCT ON (cp.id_parcelle) id_parcelle,
    cc.id_categorie_culture,
    cc.nomcategorie 
FROM 
    parcelle p,
    categories_parcelle cp,
    categorie_culture cc 
where 
    cp.id_categorie_culture = cc.id_categorie_culture 
    and p.id_parcelle = cp.id_parcelle 
    and cc.corbeille =0 
    and cp.corbeille =0 
    and cp.id_parcelle = 2 
;

SELECT  DISTINCT ON (cp.id_parcelle) cp.id_parcelle, p.id_terrain, cc.id_categorie_culture, cc.nomcategorie FROM parcelle p JOIN categories_parcelle cp ON p.id_parcelle = cp.id_parcelle JOIN categorie_culture cc ON cp.id_categorie_culture = cc.id_categorie_culture WHERE cc.corbeille = 0 AND cp.corbeille = 0;
