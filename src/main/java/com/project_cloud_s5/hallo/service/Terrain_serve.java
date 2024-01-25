package com.project_cloud_s5.hallo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.Terrain_dao;
import com.project_cloud_s5.hallo.model.terrain.Terrain;

@Service
public class Terrain_serve {
    private final Terrain_dao dao;
    @Autowired 
    public Terrain_serve(Terrain_dao dao)
    {
        this.dao = dao;
    }   
    public List<Terrain> getTerrains() {
        return dao.getTerrains();
    }
    public Terrain getTerrainById(String id) {
        return dao.getTerrainById(id);
    }
    public void insertTerrain(int idproprio,int nbparcelle,String desc,String coord,double longueur,double largeur)throws Exception{
        dao.insertTerrain(idproprio, nbparcelle, desc, coord, longueur, largeur);
    }
    public void deleteterrain(int idterrain)throws Exception{
        dao.deleteterrain(idterrain);
    }
    public void updateTerrainDesc(String newtext, String idterrain)throws Exception{
        dao.updateTerrainDesc(newtext, idterrain);
    }
    public void updateTerrainSurface(double longe,double larg, String idterrain)throws Exception{
        dao.updateTerrainSurface(longe,larg, idterrain);
    }
    public void deletepicture(String idpic, String idterrain)throws Exception{
        dao.deletepicture(idpic, idterrain);
    }  
    public void insertpicture(String picname, String idterrain)throws Exception{
        dao.insertpicture(picname, idterrain);
    }
}
