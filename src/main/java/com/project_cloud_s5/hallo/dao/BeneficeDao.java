package com.project_cloud_s5.hallo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project_cloud_s5.hallo.model.State.Benefice;
import com.project_cloud_s5.hallo.model.State.V_planteUtilise;

@Repository
public class BeneficeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Benefice> getBenefice(int anne, int mois) {
        String sql = "select * from benefice where anne = " + anne;
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Benefice.class));
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> getAnne_exist() {
        String sql = "select * from anne_exist";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("anne"));
        } catch (Exception e) {
            throw e;
        }
    }

}
