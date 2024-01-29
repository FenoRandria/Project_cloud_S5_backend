package com.project_cloud_s5.hallo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.State.V_planteUtilise;
import com.project_cloud_s5.hallo.model.State.V_statistique_globale;
import com.project_cloud_s5.hallo.model.categorie.Categorie_culture;

@Repository
public class V_PlanteUtiliseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<V_planteUtilise> getPlanteutilise() {
        String sql = "select * from V_planteUtilise";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(V_planteUtilise.class));
        } catch (Exception e) {
            throw e;
        }
    }

    public List<V_planteUtilise> getPlanteutiliseTop(int limit) {
        String sql = "select * from V_planteUtilise limit " + limit;
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(V_planteUtilise.class));
        } catch (Exception e) {
            throw e;
        }
    }

    public List<V_statistique_globale> getV_statistique_globale() {
        String sql = "select * from v_statistique_globale";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(V_statistique_globale.class));
        } catch (Exception e) {
            throw e;
        }
    }

}
