package com.project_cloud_s5.hallo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.Plante_dao;
import com.project_cloud_s5.hallo.model.plante.Plante;
import com.project_cloud_s5.hallo.model.plante.PlanteWithCategorie;

@Service
public class Plante_serve 
{
    private final Plante_dao dao;
    @Autowired 
    public Plante_serve(Plante_dao dao)
    {
        this.dao = dao;
    }   
    public List<PlanteWithCategorie> getPlantes() {
        return dao.getPlantes();
    }
    public List<PlanteWithCategorie> getPlantesByCategorie(String idcategorie) {
        return dao.getPlantesByIdCategorie(idcategorie);
    }
    public List<PlanteWithCategorie> getPlantesGames() {
        return dao.getPlantesGames();
    }
    public Plante getPlandeByid(int id){
        return dao.getPlanteById(id);
    }
    public int insertplante(int idcat,String nom,double prixachat,double prixvente){
       return dao.insertplante(idcat, nom, prixachat,prixvente);
    }
    public int updateNom_plante(int idplante,String nom)  {
        return dao.updateNom_plante(idplante, nom);
    }
    public int updatePrix(int idplante,double prix)  {
        return dao.updatePrix(idplante, prix);
    }
    public int updatePlaceingamemaker(int idplante,int plc)  {
        return dao.updatePlaceingamemaker(idplante,plc);
    }
    public int updateSpritePlante(int idplante,String npm)  {
        return dao.updateSpritePlante(idplante, npm);
    }
    public int deleteplante(int idplante)throws Exception{
        return dao.deleteplante(idplante);
    }

}
