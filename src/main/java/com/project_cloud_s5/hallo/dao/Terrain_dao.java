package com.project_cloud_s5.hallo.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.parcelle.Parcelle;
import com.project_cloud_s5.hallo.model.proprietaire.Proprietaire;
import com.project_cloud_s5.hallo.model.terrain.Terrain;
import com.project_cloud_s5.hallo.dao.Parcelle_dao;

@Repository
public class Terrain_dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Parcelle_dao parcelleDao;

    public List<Terrain> getTerrains() {
        String sql = "select * from terrain where corbeille != 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terrain.class));
    }
    public Terrain getTerrainById(String id) {
        String sql = "select * from terrain where idterrain ="+id;
        return jdbcTemplate.queryForObject(sql, Terrain.class);
    }

    public List<Integer> getTerrainsPhotos(String idterrain) {

        String sql = "select photo from photos_terrain where idterrain="+idterrain;
        return jdbcTemplate.queryForList(sql, Integer.class);
        // return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terrain.class));
    }

    public void insertTerrain(int idproprio,int nbparcelle,String desc,String coord,double longueur,double largeur)throws Exception{

        String sql = "INSERT INTO terrain (id_proprietaire, desc_terrain, coord_location, surface, corbeille) VALUES (?, ?, ?, ?, 0)";
        double surface = longueur *largeur;
        int rowsAffected = jdbcTemplate.update(sql, idproprio,desc,coord,surface);

        if(rowsAffected == 0){
            throw new Exception("Erreur insertion terrain");
        }

        String sql2 = "SELECT id_terrain FROM terrain where id_proprietaire="+idproprio+" ORDER BY id_terrain ASC LIMIT 1;";
    
        Integer resultat = jdbcTemplate.queryForObject(sql2, Integer.class);

        if (resultat != null) {

            for(int i=0;i<nbparcelle;i++){
                parcelleDao.insertparcelle(resultat, 0,0, 0);
            }
        } else {
        }


    }

    public void deleteterrain(int idterrain)throws Exception{
        String sql = "UPDATE terrain SET corbeille = 1 WHERE id_terrain = ?";

        int rowsAffected = jdbcTemplate.update(sql, idterrain);

        if(rowsAffected == 0){
            throw new Exception("erreur terrain inexistant");
        }
    }

    public void updateTerrainDesc(String newtext, String idterrain)throws Exception{

        String sql = "UPDATE terrain SET desc_terrain =' ? ' WHERE id_terrain = ?";

        int rowsAffected = jdbcTemplate.update(sql, newtext, idterrain);

        if(rowsAffected == 0){
            throw new Exception("erreur terrain inexistant");
        }

        // String sql
    }

    public void updateTerrainSurface(double longueur,double largeur, String idterrain)throws Exception{

        double surface = longueur *largeur;
        String sql = "UPDATE terrain SET surface = ? WHERE id_terrain = ?";

        int rowsAffected = jdbcTemplate.update(sql, surface, idterrain);

        if(rowsAffected == 0){
            throw new Exception("erreur terrain inexistant");
        }

        // String sql
    }

    public void deletepicture(String idpic, String idterrain)throws Exception{

        String sql = "delete from photos_terrain WHERE id_terrain = ? and id_photos_terrain= ?";

        int rowsAffected = jdbcTemplate.update(sql, idterrain, idpic);

        if(rowsAffected == 0){
            throw new Exception("erreur terrain inexistant");
        }

        // String sql
    }

    //a modifier selon comment on upload une photo sur le serveur?
    public void insertpicture(String picname, String idterrain)throws Exception{

        String sql = "insert into photos_terrain (id_terrain,photo) Values ?,'?'";

        int rowsAffected = jdbcTemplate.update(sql, idterrain, picname);

        if(rowsAffected == 0){
            throw new Exception("erreur terrain inexistant");
        }

        // String sql
    }


    //fonctions à terminer pour cette classe 
    //get parcelles par terrain / done --> Parcelle_dao
    //inserer nouveau terrain 
    //modifier details du terrain --> all done except / parcelles
    //supprimer une photo du terrain --> done
    //getphotos du terrain* List<Integer> columnIntValues = jdbcTemplate.queryForList(sql, Integer.class);  --> effectuer reqête qui rend un tableu ou une liste de string

}
