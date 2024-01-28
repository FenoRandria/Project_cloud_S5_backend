package com.project_cloud_s5.hallo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.Proprietaire_dao;
import com.project_cloud_s5.hallo.model.Token.Token;
import com.project_cloud_s5.hallo.model.proprietaire.Proprietaire;

@Service
public class Proprietaire_serve {
    private final Proprietaire_dao dao;

    @Autowired
    public Proprietaire_serve(Proprietaire_dao dao) {
        this.dao = dao;
    }

    public List<Proprietaire> getProprietaires() {
        return dao.getProprietaires();
    }

    public String seLogin(String mail, String mdp) throws Exception {
        Token token = new Token();
        try {
            Proprietaire props = dao.seLogin(mail, mdp);
            return token.CreerToken(props.getId());
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean inscrire(Proprietaire proprietaire, String confirmMdp) throws Exception {
        try {
            if (proprietaire != null) {
                if (proprietaire.getNom().isEmpty() || proprietaire.getNom() == "")
                    throw new Exception("Nom not found");
                if (proprietaire.getMail().isEmpty() || proprietaire.getMail() == "")
                    throw new Exception("Invalid Mail");
                if (proprietaire.getMdp().isEmpty() || proprietaire.getMdp() == ""
                        || proprietaire.getMdp().compareTo(confirmMdp) != 0)
                    throw new Exception("Invalid password");
            }
            return dao.inscrire(proprietaire);
        } catch (Exception e) {
            throw e;
            // TODO: handle exception
        }
    }
}
