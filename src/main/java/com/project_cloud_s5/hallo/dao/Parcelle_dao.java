package com.project_cloud_s5.hallo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.parcelle.Parcelle;

@Repository
public class Parcelle_dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Parcelle> getParcelles() {
        String sql = "select * from parcelle";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Parcelle.class));
    }

    public Parcelle getParcelleByid(String id) {
        String sql = "select * from parcelle where id_parcelle="+id;
        return jdbcTemplate.queryForObject(sql,Parcelle.class);
    }

    public List<Parcelle> getByTerrain(String idterrain) {
        String sql = "select * from parcelle where id_terrain="+idterrain;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Parcelle.class));
    }

    public void insertparcelle(int idterrain,double longueur,double largeur,double rendement)throws Exception{
        
        double surface = longueur * largeur;
        String sql = "INSERT INTO PARCELLE (id_terrain,surface,rendement) values (?,?,?)";

        int rowsAffected = jdbcTemplate.update(sql, idterrain,surface,rendement);

    }
    public void updateSurface(int idparcelle,double longueur,double largeur)throws Exception{
        
        double surface = longueur * largeur;
        String sql = "update parcelle set surface=? where id_parcelle=?";

        int rowsAffected = jdbcTemplate.update(sql,surface,idparcelle);

    }
    public void updateRendement(int idparcelle,double rendement)throws Exception{
        
        // double surface = longueur * largeur;
        String sql = "update parcelle set rendement=? where id_parcelle=?";

        int rowsAffected = jdbcTemplate.update(sql,rendement,idparcelle);

    }
    public void deleteparcelle(int idparcelle)throws Exception{
        
        // double surface = longueur * largeur;
        String sql = "delete from parcelle where id_parcelle=?";

        int rowsAffected = jdbcTemplate.update(sql,idparcelle);

    }
  
}
