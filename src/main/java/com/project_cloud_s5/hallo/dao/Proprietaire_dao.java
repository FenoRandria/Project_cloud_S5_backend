package com.project_cloud_s5.hallo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.Proprietaire;

@Repository
public class Proprietaire_dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Proprietaire> getProprietaires() {
        String sql = "select * from proprietaire where corbeil != 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Proprietaire.class));
    }

    public Proprietaire seLogin(String mail, String mdp) throws Exception {
        if (mail == null || mail.isEmpty() || !mail.contains("@")) {
            throw new Exception("Invalid email");
        }
        String sql = "SELECT * FROM proprietaire WHERE mail = ?";

        try {
            Proprietaire proprietaire = jdbcTemplate.queryForObject(sql, new Object[]{mail}, new BeanPropertyRowMapper<>(Proprietaire.class));

            if (proprietaire==null) {
                throw new Exception("Compte inexist sur mail: "+mail);
            }

            if (proprietaire != null && mdp.equals(proprietaire.getMdp())) {
                return proprietaire;
            } else {
                throw new Exception("Invalid password");
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
