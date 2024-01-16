package com.project_cloud_s5.hallo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.Proprietaire_dao;
import com.project_cloud_s5.hallo.model.Proprietaire;

@Service
public class Proprietaire_serve {
    private final Proprietaire_dao dao;
    @Autowired 
    public Proprietaire_serve(Proprietaire_dao dao)
    {
        this.dao = dao;
    }   
    public List<Proprietaire> getProprietaires() {
        return dao.getProprietaires();
    }

    public Proprietaire seLogin(String mail, String mdp) throws Exception
    {
        try {
            return dao.seLogin(mail, mdp);
        } catch (Exception e) {
            throw e;
        }
    }
}
