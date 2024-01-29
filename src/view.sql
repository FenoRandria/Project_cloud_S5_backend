


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
    create table mois (id serial primary key ,mois varchar(50));




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


