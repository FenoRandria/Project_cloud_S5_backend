package com.project_cloud_s5.hallo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.proprietaire.Proprietaire;

@Repository
public class Proprietaire_dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Proprietaire> getProprietaires() {
        String sql = "select * from proprietaire where corbeille != 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Proprietaire.class));
    }

    public Proprietaire getProprietaires(String id) {
        String sql = "select * from proprietaire where corbeille != 1 and id = ?";
        System.out.println(sql+"-"+id);
        return jdbcTemplate.query(sql, new Object[]{Integer.parseInt(id)}, new BeanPropertyRowMapper<>(Proprietaire.class)).get(0);
    }

    public Proprietaire seLogin(String mail, String mdp) throws Exception {
        if (mail == null || mail.isEmpty() || !mail.contains("@")) {
            throw new Exception("Invalid email");
        }
        String sql = "SELECT * FROM proprietaire WHERE mail = ?";
        try {
            Proprietaire proprietaire = jdbcTemplate.queryForObject(sql, new Object[]{mail}, new BeanPropertyRowMapper<>(Proprietaire.class));

            if (proprietaire==null || mail.compareTo(proprietaire.getMail())!=0) throw new Exception("Compte inexist sur mail: "+mail);
            if (proprietaire != null && mdp.equals(proprietaire.getMdp())) 
                return proprietaire; 
            else throw new Exception("Invalid password");
            
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("error during login", e);
        }
    }
    
    public boolean inscrire(Proprietaire proprietaire) throws Exception {
        if (proprietaire == null) {
            throw new IllegalArgumentException("Invalid inscription, proprietaire is null");
        }
        String sql = "INSERT INTO proprietaire (nom, mail, mdp, dtn) VALUES (?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, proprietaire.getNom(), proprietaire.getMail(), proprietaire.getMdp(), proprietaire.getDtn());

            if (rowsAffected < 1) {
                throw new DataAccessException("Invalid inscription, verify your fields") {};
            }
            return true;
        } catch (DataAccessException e) {
            throw new Exception("Error during inscription", e);
        }
    }
}
