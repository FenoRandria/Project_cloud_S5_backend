package com.project_cloud_s5.hallo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.categorie.Categorie_culture;
import com.project_cloud_s5.hallo.model.parcelle.Parcelle;

@Repository
public class Parcelle_dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Parcelle> getParcelles() {
        String sql = "SELECT * FROM parcelle";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Parcelle.class));
    }

    public Parcelle getParcelleById(String id) {
        String sql = "SELECT * FROM parcelle WHERE id_parcelle = ?";
        System.out.println("id ----- "+id);
        System.out.println(sql);
        return jdbcTemplate.query(sql, new Object[]{Integer.parseInt(id)}, new BeanPropertyRowMapper<>(Parcelle.class)).get(0);
    }

    public List<Categorie_culture> getParcelleCategorieCultureById(String id) {
        String sql = "SELECT cp.id_parcelle,cc.id_categorie_culture,cc.nomcategorie FROM categories_parcelle cp,categorie_culture cc where cp.id_categorie_culture = cc.id_categorie_culture and cc.corbeille =0 and cp.corbeille =0 and cp.id_parcelle = ?";
        System.out.println(sql);
        return jdbcTemplate.query(sql, new Object[]{Integer.parseInt(id)}, new BeanPropertyRowMapper<>(Categorie_culture.class));
    }

    public List<Parcelle> getByTerrain(String idTerrain) {
        String sql = "SELECT * FROM parcelle WHERE id_terrain = ?";
        System.out.println(sql);
        return jdbcTemplate.query(sql, new Object[]{Integer.parseInt(idTerrain)}, new BeanPropertyRowMapper<>(Parcelle.class));
    }

    public int insertParcelle(int idTerrain, double longueur, double largeur, double rendement) throws Exception {
        double surface = longueur * largeur;
        String sql = "INSERT INTO PARCELLE (id_terrain, surface, rendement) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, idTerrain, surface, rendement);
    }

    public int updateSurface(int idParcelle, double longueur, double largeur) throws Exception {
        double surface = longueur * largeur;
        String sql = "UPDATE parcelle SET surface = ? WHERE id_parcelle = ?";
        return jdbcTemplate.update(sql, surface, idParcelle);
    }

    public int updateRendement(int idParcelle, double rendement) throws Exception {
        String sql = "UPDATE parcelle SET rendement = ? WHERE id_parcelle = ?";
        return jdbcTemplate.update(sql, rendement, idParcelle);
    }

    public int deleteParcelle(int idParcelle) throws Exception {
        String sql = "DELETE FROM parcelle WHERE id_parcelle = ?";
        return jdbcTemplate.update(sql, idParcelle);
    }
  
}
