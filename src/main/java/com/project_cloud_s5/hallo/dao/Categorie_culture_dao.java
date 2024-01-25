package com.project_cloud_s5.hallo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.categorie.Categorie_culture;
import com.project_cloud_s5.hallo.model.proprietaire.Proprietaire;

@Repository
public class Categorie_culture_dao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Categorie_culture> getCategorie_cultures() {
        String sql = "select * from Categorie_culture where corbeille != 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categorie_culture.class));
    }

    public Categorie_culture getCategorie_cultureByid(String id) throws Exception {
        try {
            String sql = "SELECT * FROM Categorie_culture WHERE corbeille = 0 and id_categorie_culture = ?";
            List<Categorie_culture> result = jdbcTemplate.query(sql, new Object[]{Integer.parseInt(id)}, new BeanPropertyRowMapper<>(Categorie_culture.class));
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la requête de recherche par ID", e);
        }
    }

    public int insertCategorie_culture(String nomcategorie)throws Exception{
        
        String sql = "INSERT INTO CATEGORIE_CULTURE (nomcategorie) values (?)";
        try {
            return jdbcTemplate.update(sql,nomcategorie);
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("error insertion categorie culture",e);
        }
    }
    public int deleteCategorieCulture(String id)throws Exception{
        String sql = "UPDATE CATEGORIE_CULTURE set corbeille = 1 where id_categorie_culture = ?";
        System.out.println(sql);
        try {
            return jdbcTemplate.update(sql,Integer.parseInt(id));
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("error delete categorie culture",e);
        }
    }

    //fonction à terminer pour cette classe
    //get all categories culture
    //get plantes par categories ?

}
