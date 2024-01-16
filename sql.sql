--table referencement de tout les propriétaires
create table proprietaire 
(
    id_proprietaire serial primary key,
    nom varchar(150),
    mail varchar(150),
    mdp varchar(20) ,
    dtn date,
    corbeil int default 0 
);

--table referencement de tout les terrain
create table terrain(
    id_terrain serial primary key,
    id_proprietaire int,
    desc_terrain TEXT,
    longitude varchar(),
    latitude varchar(),
    surface float,
    corebille int default 0
);

--table referencement des photos des terrains
create table photos_terrain(
    id_photos_terrain serial primary key,
    id_terrain int,
    photo varchar(50),
    FOREIGN KEY(fk_id_terrain) 
    REFERENCES terrain(id_terrain)
);

--table referencement des différentes categories de culture
create table categorie_culture(
    id_categorie_culture serial primary key,
    nomcategorie varchar(50)
);

--table referencement des différent types de plantes appartenant à la categorie
create table type_plante(
    id_type_plante serial primary key,
    id_categorie_culture int,
    nom_plante varchar(50),
    recolte int,
    sprite_plante varchar(30),
    placeingamemaker int,
    FOREIGN KEY(fk_id_categorie_culture) 
    REFERENCES categorie_culture(id_categorie_culture)
);

--table referencement de toutes les parcelles d'un terrain
create table parcelle(
    id_parcelle serial primary key,
    id_terrain int,
    rendement float,
    FOREIGN KEY(fk_id_terrain) 
    REFERENCES terrain(id_terrain)
);

--table referencement des categories qu'une parcelle peut accepter 
create table categories_parcelle(
    id_categories_parcelle serial primary key,
    id_parcelle int,
    id_categorie_culture int,
    FOREIGN KEY(fk_id_parcelle) 
    REFERENCES parcelle(id_parcelle),
    FOREIGN KEY(fk_id_categorie_culture) 
    REFERENCES categorie_culture(id_categorie_culture)

);

--types d'action planter/recolter
create table action(
    id_action serial primary key,
    id_parcelle int,
    typeaction int,
    dateaction datetime,
    FOREIGN KEY(fk_id_parcelle) 
    REFERENCES parcelle(id_parcelle)
);