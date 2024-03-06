package com.project_cloud_s5.hallo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project_cloud_s5.hallo.dao.Parcelle_dao;
import com.project_cloud_s5.hallo.model.categorie.Categorie_culture;
import com.project_cloud_s5.hallo.model.parcelle.Parcelle;

@Service
public class Parcelle_serve {

    private final Parcelle_dao parcelleDao;

    @Autowired
    public Parcelle_serve(Parcelle_dao parcelleDao) {
        this.parcelleDao = parcelleDao;
    }

    public List<Parcelle> getParcelles() {
        return parcelleDao.getParcelles();
    }

    public List<Parcelle> getParcellesByTerrain(String id) {
        return parcelleDao.getByTerrain(id);
    }

    public Parcelle getParcelleById(String id) {
        return parcelleDao.getParcelleById(id);
    }
    public List<Parcelle> getParcelleByIdTerrain(String idTerrain) {
        return parcelleDao.getByTerrain(idTerrain);
    }
    

    public int insertParcelle(int idTerrain, double longueur, double largeur, double rendement) throws Exception {
        try {
            return parcelleDao.insertParcelle(idTerrain, longueur, largeur, rendement);
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion de la parcelle", e);
        }
    }

    public int updateSurface(int idParcelle, double longueur, double largeur) throws Exception {
        try {
            if (longueur<largeur) throw new Exception("error produite update surface parcelle : longueur inferieur largeur");
            return parcelleDao.updateSurface(idParcelle, longueur, largeur);
        } catch (Exception e) 
        {
            throw new Exception("Erreur lors de la mise à jour de la surface de la parcelle", e);
        }
    }

    public int updateRendement(int idParcelle, double rendement) throws Exception {
        try {
            return parcelleDao.updateRendement(idParcelle, rendement);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la mise à jour du rendement de la parcelle", e);
        }
    }

    public int deleteParcelle(int idParcelle) throws Exception {
        try {
            return parcelleDao.deleteParcelle(idParcelle);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la suppression de la parcelle", e);
        }
    }
    public List<Categorie_culture> getParcelleCategorieCultureById(String id) throws Exception {
        try {
            return parcelleDao.getParcelleCategorieCultureById(id);
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("Erreur lors de la recuperation list categories cultures dans parcelle", e);
        }
    }

    public List<Categorie_culture> getParcelleTerrainCategorie() throws Exception {
        try {
            return parcelleDao.getParcelleTerrainCategorie();
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("Erreur lors de la recuperation list categories cultures dans parcelle et terrain", e);
        }
    }

    
}
