


 1 ==> Plantes le plus utiliser par les utilisateurs 
 2 ==> Benefice mensuel 
 3 ==> Liste plante + vente effectue 
 4 ==> Nombre parcelle
 5 ==> 


 Dashboard
Login Terminé
Gestion des éléments : 
	Catégorie culture-Terminé
	-Plante Pas commencé
	-Terrain Terminé
	-Parcelles Terminé
	=> CRUD

	-Achat / Planter Terminé
	-Recolter Terminé
	=> Historique , liste

Tableau statistiques
	-à voir
		-évolution des récoltes mensuels Terminé
		-Plantes les plus utilisées par l’utilisateur Terminé
		-Bénéfice mensuel Terminé
		-Listes plantes + ventes effectuées Terminé


       STATE 1  ==> 

       /* VIEW Plantes le plus utiliser par les utilisateurs  */ 

    create or replace view V_planteUtilise as (
       select pl.id_plante, pl.nom_plante, count(pl.id_plante) nbr_vente
       from vente v join plante pl on v.id_plante = pl.id_plante 
    group by pl.id_plante,pl.nom_plante order by nbr_vente desc);



    /* ---- ----- ------ ------ ------ ------ ------ -------- ------ ----- ------ ------ --- */




    /* VIEW BENEFICE */
    create table month (id serial primary key ,mois varchar(50));

INSERT INTO month (mois) VALUES
('Janvier'),
('Février'),
('Mars'),
('Avril'),
('Mai'),
('Juin'),
('Juillet'),
('Août'),
('Septembre'),
('Octobre'),
('Novembre'),
('Décembre');


    create or replace view V_VentePlante as (
    select 
        sum(pl.prixvente-pl.prixachat)benefice,extract(month from v.dateaction) mois,extract(year from v.dateaction) anne 
    from vente v join plante pl on v.id_plante = pl.id_plante
    group by extract(month from v.dateaction),extract(year from v.dateaction));

    create or replace view Anne_Exist as (
        select extract (year from dateaction) anne from vente v group by extract (year from dateaction)
    );

    

    create or replace view V_VentePlanteMois as (  
        select m.*,coalesce(v.benefice,0) benefice from V_VentePlante v cross join month m
    );

    select * from Anne_Exist a_e cross join V_VentePlanteMois  
    select m.*,coalesce(v.benefice,0) benefice,v.anne from month m left join V_VentePlante v on m.id = v.mois; 

    select * from month m left join  V_VentePlante v_p on m.id = v_p.mois

    create or replace view V_Anne_Mois as (
    select * from Anne_Exist a_e cross join month m );

    create or replace view benefice as (
    select v_a_m.anne,v_a_m.mois,v_a_m.id,coalesce(v_v.benefice,0) as benefice from V_Anne_Mois v_a_m left join V_VentePlante v_v 
    on v_a_m.anne = v_v.anne and v_a_m.id = v_v.mois);

    /* - ------ -------------- ----------- ----------- ---------- ------------ --------- ---------- ------*/



    /* VIEW */
    -- data test -----------------------------------------------------------------------------------------------------------------------------------------------
 insert into proprietaire (nom, mail, mdp, dtn) values
('Bob', 'bob@gmail.com', 'password123', '1995-05-15'),
('Charlie', 'charlie@gmail.com', 'securepass', '1988-11-25'),
('David', 'david@gmail.com', 'davypass', '2000-03-08'),
('Eva', 'eva@gmail.com', 'evapass', '1992-07-20'),
('Frank', 'frank@gmail.com', 'frank123', '1985-09-12'),
('Grace', 'grace@gmail.com', 'gracepass', '1998-01-30'),
('Helen', 'helen@gmail.com', 'helenpass', '1980-12-05'),
('Ian', 'ian@gmail.com', 'ianpass', '1991-04-18'),
('Julia', 'julia@gmail.com', 'juliapass', '1987-06-22'),
('Kurt', 'kurt@gmail.com', 'kurtpass', '1993-10-10');

insert into terrain (id_proprietaire, desc_terrain, coord_location, surface) values
(1, 'Garden A', '12.345,67.890', 20.5),
(2, 'Backyard', '45.678,89.012', 15.8),
(3, 'Farm Fields', '78.901,23.456', 30.2),
(4, 'Greenhouse', '34.567,12.890', 40.0),
(5, 'Orchard', '56.789,90.123', 25.5),
(6, 'Rooftop Garden', '78.901,23.456', 12.7),
(7, 'Vineyard', '12.345,67.890', 18.3),
(8, 'Urban Plot', '45.678,89.012', 22.0),
(9, 'Park Area', '34.567,12.890', 50.5),
(10, 'Experimental Zone', '56.789,90.123', 15.2);

insert into photos_terrain (id_terrain, photo) values
(1, 'garden_image1.jpg'),
(1, 'garden_image2.jpg'),
(2, 'backyard_image1.jpg'),
(3, 'fields_image1.jpg'),
(4, 'greenhouse_image1.jpg'),
(5, 'orchard_image1.jpg'),
(6, 'rooftop_image1.jpg'),
(7, 'vineyard_image1.jpg'),
(8, 'urbanplot_image1.jpg'),
(9, 'park_image1.jpg');

insert into categorie_culture (nomcategorie) values
('Fruits'),
('Vegetables'),
('Herbs'),
('Flowers'),
('Grains'),
('Spices'),
('Medicinal Plants'),
('Exotic Plants'),
('Trees'),
('Cacti');

insert into plante (id_categorie_culture, nom_plante, prixachat, prixvente, sprite_plante, placeingamemaker) values
(1, 'Apple Tree', 10.5, 25.0, 'apple_sprite.png', 1),
(2, 'Tomato Plant', 2.0, 5.0, 'tomato_sprite.png', 2),
(3, 'Basil', 1.5, 4.0, 'basil_sprite.png', 3),
(4, 'Rose', 3.0, 8.0, 'rose_sprite.png', 4),
(5, 'Wheat', 0.5, 2.0, 'wheat_sprite.png', 5),
(6, 'Cinnamon', 4.0, 10.0, 'cinnamon_sprite.png', 6),
(7, 'Aloe Vera', 2.5, 6.0, 'aloe_sprite.png', 7),
(8, 'Orchid', 5.0, 12.0, 'orchid_sprite.png', 8),
(9, 'Oak Tree', 15.0, 30.0, 'oak_sprite.png', 9),
(10, 'Cactus', 3.5, 8.0, 'cactus_sprite.png', 10);

insert into parcelle (id_terrain, surface, rendement) values
(1, 5.0, 150.0),
(1, 8.0, 220.0),
(2, 6.5, 180.0),
(3, 12.0, 320.0),
(4, 15.0, 400.0),
(5, 10.5, 300.0),
(6, 3.0, 90.0),
(7, 7.5, 200.0),
(8, 9.0, 250.0),
(9, 20.0, 500.0);

insert into categories_parcelle (id_parcelle, id_categorie_culture) values
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

insert into recolte (id_parcelle, id_plante, dateaction) values
(2, 10, current_timestamp),
(4, 12, current_timestamp),
(5, 13, current_timestamp),
(6, 14, current_timestamp),
(7, 15, current_timestamp),
(8, 16, current_timestamp),
(9, 17, current_timestamp),
(10,18, current_timestamp),
(11, 19, current_timestamp),
(12, 20, current_timestamp);

insert into vente (id_parcelle, id_plante, dateaction) values
(11, 11, current_timestamp),
(2, 12, current_timestamp),
(12, 13, current_timestamp),
(4, 14, current_timestamp),
(5, 15, current_timestamp),
(6, 16, current_timestamp),
(7, 17, current_timestamp),
(8, 18, current_timestamp),
(9, 19, current_timestamp),
(10, 20, current_timestamp);

-- Sample data for 2022
INSERT INTO vente (id_parcelle, id_plante, dateaction)
VALUES
(11, 11, '2022-03-15 08:30:00'),
(12, 12, '2022-05-20 14:45:00'),
(2, 13, '2022-08-10 10:00:00'),
(4, 14, '2022-11-02 16:20:00'),
(5, 15, '2022-04-18 12:00:00');

-- Sample data for 2023
INSERT INTO vente (id_parcelle, id_plante, dateaction)
VALUES
(6, 16, '2023-02-08 09:15:00'),
(7, 17, '2023-06-25 11:30:00'),
(8, 18, '2023-09-14 13:45:00'),
(9, 19, '2023-12-05 17:10:00'),
(10, 10, '2023-03-22 20:00:00');


