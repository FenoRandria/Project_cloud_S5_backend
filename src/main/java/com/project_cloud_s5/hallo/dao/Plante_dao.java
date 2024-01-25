package com.project_cloud_s5.hallo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.plante.Plante;

@Repository
public class Plante_dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Plante> getPlantes() {
        String sql = "select * from Plante";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Plante.class));
    }

    public List<Plante> getPlantesGames() {
        String sql = "select * from Plante where sprite_plante not null and placeingamemaker not null";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Plante.class));
    }

    public Plante getPlanteById(int idplante) {
        String sql = "select * from Plante where id_plante"+idplante;
        return jdbcTemplate.queryForObject(sql,Plante.class);
    }

    public List<Plante> getPlantesByIdCategorie(String idculture) {
        String sql = "select * from Plante where id_categorie_culture"+idculture;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Plante.class));
    }

    public void insertplante(int idcat,String nom,double prixachat,double prixvente){
        String sql = "INSERT INTO Plante (id_categorie,nom_plante,prixachat,prixvente) values (?,?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(sql, idcat,nom,prixachat,prixvente);
        // return jdbcTemplate.query(sql, );
    }

    public void updateNom_plante(int idplante,String nom)  {
        
        String sql = "update plante set nom_plante=? where id_plante=?";

        int rowsAffected = jdbcTemplate.update(sql,nom,idplante);

    }

    public void updatePrix(int idplante,double prix)  {
        
        String sql = "update plante set prix=? where id_plante=?";

        int rowsAffected = jdbcTemplate.update(sql,prix,idplante);

    }

    public void updateSpritePlante(int idplante,String npm)  {
        
        String sql = "update plante set sprite_plante=? where id_plante=?";

        int rowsAffected = jdbcTemplate.update(sql,npm,idplante);

    }

    //index du placement de la terre de cultivation du sprite dans gamemaker
    public void updatePlaceingamemaker(int idplante,int plc)  {
        
        String sql = "update plante set placeingamemaker=? where id_plante=?";

        int rowsAffected = jdbcTemplate.update(sql,plc,idplante);

    }

    public void deleteplante(int idplante)throws Exception{
        
        String sql = "delete from plante where id_plante=?";

        int rowsAffected = jdbcTemplate.update(sql,idplante);

    }

}
