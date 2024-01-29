package com.project_cloud_s5.hallo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.plante.Plante;
import com.project_cloud_s5.hallo.model.plante.PlanteWithCategorie;

@Repository
public class Plante_dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PlanteWithCategorie> getPlantes() {
        String sql = "select p.id_plante,p.nom_plante,p.prixachat,p.prixvente,p.sprite_plante,p.placeingamemaker,c.id_categorie_culture,c.nomcategorie,c.corbeille from Plante p join categorie_culture c on p.id_categorie_culture = c.id_categorie_culture where c.corbeille = 0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PlanteWithCategorie.class));
    }

    public List<PlanteWithCategorie> getPlantesGames() {
        String sql = "select p.id_plante,p.nom_plante,p.prixachat,p.prixvente,p.sprite_plante,p.placeingamemaker,c.id_categorie_culture,c.nomcategorie,c.corbeille from Plante p join categorie_culture c on p.id_categorie_culture = c.id_categorie_culture where c.corbeille = 0 and sprite_plante is not null and placeingamemaker is not null";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PlanteWithCategorie.class));
    }

    public Plante getPlanteById(int idplante) {
        String sql = "select * from Plante where id_plante"+idplante;
        return jdbcTemplate.queryForObject(sql,Plante.class);
    }

    public List<PlanteWithCategorie> getPlantesByIdCategorie(String idculture) 
    {
        String sql = "select p.id_plante,p.nom_plante,p.prixachat,p.prixvente,p.sprite_plante,p.placeingamemaker,c.id_categorie_culture,c.nomcategorie,c.corbeille from Plante p join categorie_culture c on p.id_categorie_culture = c.id_categorie_culture where c.corbeille = 0 and p.id_categorie_culture = ?";
        return jdbcTemplate.query(sql,new Object[]{Integer.parseInt(idculture)},new BeanPropertyRowMapper<>(PlanteWithCategorie.class));
    }

    public int insertplante(int idcat,String nom,double prixachat,double prixvente){
        String sql = "INSERT INTO Plante (id_categorie,nom_plante,prixachat,prixvente) values (?,?,?,?,?)";
        return jdbcTemplate.update(sql, idcat,nom,prixachat,prixvente);
    }

    public int updateNom_plante(int idplante,String nom)  {
        String sql = "update plante set nom_plante=? where id_plante=?";
        return jdbcTemplate.update(sql,nom,idplante);

    }

    public int updatePrix(int idplante,double prixvente)  {
        
        String sql = "update plante set prixvente=? where id_plante=?";
        return jdbcTemplate.update(sql,prixvente,idplante);

    }

    public int updateSpritePlante(int idplante,String npm)  {
        String sql = "update plante set sprite_plante=? where id_plante=?";
        return jdbcTemplate.update(sql,npm,idplante);

    }

    //index du placement de la terre de cultivation du sprite dans gamemaker
    public int updatePlaceingamemaker(int idplante,int plc)  {
        String sql = "update plante set placeingamemaker=? where id_plante=?";
        return jdbcTemplate.update(sql,plc,idplante);

    }

    public int deleteplante(int idplante)throws Exception{
        String sql = "delete from plante where id_plante=?";
        return jdbcTemplate.update(sql,idplante);

    }

}
