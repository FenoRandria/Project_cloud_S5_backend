package com.project_cloud_s5.hallo.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.categorie.Categorie_culture;
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
        String sql = "select * from terrain where corbeille = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terrain.class));
    }

    public List<Terrain> getTerrainsInvalid() {
        String sql = "select * from terrain where corbeille = 0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Terrain.class));
    }
    
    public int validateTerrain(String idterrain)throws Exception{

        String sql = "UPDATE terrain SET corbeille = 1 WHERE id_terrain = ?";
        try {
            return jdbcTemplate.update(sql,Integer.parseInt(idterrain));
        } catch (Exception e) {
            throw new Exception("erreur terrain inexistant, validation terrain invalid",e);
        }
    }
    public Terrain getTerrainById(String id) throws Exception {
         try {
            String sql = "select * from terrain where corbeille = 1 and id_terrain = ?";
            List<Terrain> result = jdbcTemplate.query(sql, new Object[]{Integer.parseInt(id)}, new BeanPropertyRowMapper<>(Terrain.class));
            return result.isEmpty() ? null : result.get(0);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            throw new Exception("Erreur lors de la requête de recherche terrain par ID", e);
        }
    }

    public List<String> getTerrainsPhotos(String idterrain) throws Exception {
        try {
            String sql = "SELECT photo FROM photos_terrain WHERE id_terrain = ?";
            int idTerrain = Integer.parseInt(idterrain);
            return jdbcTemplate.query(sql, new Object[]{idTerrain}, (rs, rowNum) -> rs.getString("photo"));
        } catch (Exception e) {
            throw new Exception("Erreur lors de la requête de recherche photo terrain par ID", e);
        }
    }
    
    public int insertTerrain(Terrain terrain,int nbparcelle)throws Exception{

        String sql = "INSERT INTO terrain (id_proprietaire, desc_terrain, coord_location, surface, corbeille) VALUES (?, ?, ?, ?, 0) RETURNING *";
        try {
            List<Terrain> result = jdbcTemplate.query(sql, new Object[]{terrain.getId_proprietaire(),terrain.getDesc_terrain(),terrain.getCoord_location(),terrain.getSurface()}, new BeanPropertyRowMapper<>(Terrain.class));
            Terrain newterrain = result.isEmpty() ? null : result.get(0);
            if(newterrain.equals(null)){
                throw new Exception("Erreur insertion terrain");
            }
            if (nbparcelle>0) {
                for(int i=0;i<nbparcelle;i++){
                    parcelleDao.insertParcelle(newterrain.getId_terrain(),0.0,0.0, 0.0);
                }
            }
            return newterrain.getId_terrain();
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("Erreur insertion terrain our parcelle",e);
        }
    }

    public int deleteterrain(int idterrain)throws Exception{
        String sql = "UPDATE terrain SET corbeille = 2 WHERE corbeille = 1 and id_terrain = ?";
        try {
            return jdbcTemplate.update(sql, idterrain);
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("erreur terrain inexistant, delete invalid",e);
        }
    }

    public int updateTerrainDesc(String newtext, String idterrain)throws Exception{
        String sql = "UPDATE terrain SET desc_terrain = ? WHERE corbeille = 1 and id_terrain = ?";
        try {
            return jdbcTemplate.update(sql, newtext, Integer.parseInt(idterrain));
        } catch (Exception e) {
            throw new Exception("erreur terrain inexistant, update description terrain invalid",e);
        }
    }

    public int updateTerrainSurface(double longueur, double largeur, String idterrain)throws Exception{
        if (largeur<0 || longueur <0 || largeur>longueur) 
        {
            throw new Exception("erreur update terrain surface valeur illegal");
        }
        double surface = longueur*largeur;
        String sql = "UPDATE terrain SET surface = ? WHERE corbeille = 1 and id_terrain = ?";
        try {
            return jdbcTemplate.update(sql, surface, Integer.parseInt(idterrain));
        } catch (Exception e) {
            throw new Exception("erreur terrain inexistant, update surface terrain invalid",e);
        }
    }

    public int deletepicture(String idpic, String idterrain)throws Exception{

        String sql = "delete from photos_terrain WHERE id_terrain = ? and id_photos_terrain = ?";
        try {
            return jdbcTemplate.update(sql, Integer.parseInt(idterrain), idpic);
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("erreur terrain inexistant, delete picture terrain invalid",e);
        }
    }

    //a modifier selon comment on upload une photo sur le serveur?
    public int insertpicture(String photo, String id_terrain)throws Exception{

        String sql = "insert into photos_terrain (id_terrain, photo) Values (?,?)";
        try {
            return jdbcTemplate.update(sql, Integer.parseInt(id_terrain), photo);
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("erreur terrain inexistant, insertion picture invalid",e);
        }
    }


    //fonctions à terminer pour cette classe 
    //get parcelles par terrain / done --> Parcelle_dao
    //inserer nouveau terrain 
    //modifier details du terrain --> all done except / parcelles
    //supprimer une photo du terrain --> done
    //getphotos du terrain* List<Integer> columnIntValues = jdbcTemplate.queryForList(sql, Integer.class);  --> effectuer reqête qui rend un tableu ou une liste de string

}
