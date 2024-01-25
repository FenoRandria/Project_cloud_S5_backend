package com.project_cloud_s5.hallo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.Parcelle_dao;
import com.project_cloud_s5.hallo.model.parcelle.Parcelle;

@Service
public class Parcelle_serve {
    private final Parcelle_dao dao;
    @Autowired 
    public Parcelle_serve(Parcelle_dao dao)
    {
        this.dao = dao;
    }   
    public List<Parcelle> getParcelles() {
        return dao.getParcelles();
    }
    public List<Parcelle> getParcellesByTerrain(String id) {
        return dao.getByTerrain(id);
    }
    public Parcelle getParcelleById(String id) {
        return dao.getParcelleByid(id);
    }
    public void insertParcelle(int idterrain,double longueur,double largeur,double rendement) throws Exception{
        dao.insertparcelle(idterrain,longueur,largeur, rendement);
        return;
    }
    public void updateSurface(int idparcelle,double longueur,double largeur)throws Exception{
        dao.updateSurface(idparcelle, longueur, largeur);
    }
    public void updateRendement(int idparcelle,double rendement)throws Exception{
        dao.updateRendement(idparcelle, rendement);
    }
    public void deleteparcelle(int idparcelle)throws Exception{
        dao.deleteparcelle(idparcelle);
    }

}
