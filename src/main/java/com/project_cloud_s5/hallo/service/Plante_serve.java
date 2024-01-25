package com.project_cloud_s5.hallo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.Plante_dao;
import com.project_cloud_s5.hallo.model.plante.Plante;

@Service
public class Plante_serve {
    private final Plante_dao dao;
    @Autowired 
    public Plante_serve(Plante_dao dao)
    {
        this.dao = dao;
    }   
    public List<Plante> getPlantes() {
        return dao.getPlantes();
    }
    public List<Plante> getPlantesByCategorie(String idcategorie) {
        return dao.getPlantesByIdCategorie(idcategorie);
    }
    public List<Plante> getPlantesGames() {
        return dao.getPlantesGames();
    }
    public Plante getPlandeByid(int id){
        return dao.getPlanteById(id);
    }
    public void insertplante(int idcat,String nom,double prixachat,double prixvente){
        dao.insertplante(idcat, nom, prixachat,prixvente);
    }
    public void updateNom_plante(int idplante,String nom)  {
        dao.updateNom_plante(idplante, nom);
    }
    public void updatePrix(int idplante,double prix)  {
        dao.updatePrix(idplante, prix);
    }
    public void updatePlaceingamemaker(int idplante,int plc)  {
        dao.updatePlaceingamemaker(idplante,plc);
    }
    public void updateSpritePlante(int idplante,String npm)  {
        dao.updateSpritePlante(idplante, npm);
    }
    public void deleteplante(int idplante)throws Exception{
        dao.deleteplante(idplante);
    }

}
