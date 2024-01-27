package com.project_cloud_s5.hallo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Terrain> getTerrainsInvalid() {
        return dao.getTerrainsInvalid();
    }
    public int validateTerrain(String idterrain)throws Exception{
        try {
            if (idterrain.equals("")) throw new Exception("Error terrain inexistant");
            Integer.parseInt(idterrain);
            return dao.validateTerrain(idterrain);
        } catch (Exception e) {
            throw new Exception("Error terrain inexistant lors de suppression de terrain",e);
        }
    }
    public Terrain getTerrainById(String id) throws Exception{
        try {
            if (id.equals("")) {
                throw new IllegalArgumentException("ID ne peut pas être null ou vide");
            }
            return dao.getTerrainById(id);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du terrain par ID", e);
        }
    }
    public List<String> getTerrainsPhotos(String id) throws Exception{
        try {
            if (id.equals("")) {
                throw new IllegalArgumentException("ID ne peut pas être null ou vide");
            }
            List<String> result = dao.getTerrainsPhotos(id);
            if (result.equals(null)) {
                throw new Exception("Aucun terrain trouvé avec l'ID : " + id);
            }
            return result;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du terrain par ID", e);
        }
    }

    public int insertTerrain(Terrain terrain,int nbparcelle)throws Exception{
        try {
            if (terrain.getId_proprietaire()<0) 
            throw new Exception("Error insertion terrain proprietaire inexistant");
            return dao.insertTerrain(terrain,nbparcelle);
        } catch (Exception e) {
            throw new Exception("Error terrain inexistant lors de recuperation de terrain",e);
        }
    }
    public int deleteterrain(String idterrain)throws Exception{
        try {
            if (idterrain.equals("")) throw new Exception("Error terrain inexistant");
            int result = dao.deleteterrain(Integer.parseInt(idterrain));
            if(result>0) return result; 
            else throw new Exception("invalid delete this terrain: terrain invalidate");
        } catch (Exception e) {
            throw new Exception("Error terrain inexistant lors de suppression de terrain",e);
        }
    }
    public int updateTerrainDesc(String newtext, String idterrain)throws Exception{
        
        try {
            if (idterrain.equals("")) throw new Exception("Error terrain inexistant");
            return dao.updateTerrainDesc(newtext, idterrain);
        } catch (Exception e) {
            throw new Exception("Error terrain inexistant lors de mise à jour de terrain",e);
        }
    }
    public int updateTerrainSurface(double longe,double larg, String idterrain)throws Exception{
        try {
            if (idterrain.equals("")) throw new Exception("Error terrain inexistant");
            return dao.updateTerrainSurface(longe,larg,idterrain);
        } catch (Exception e) {
            throw new Exception("Error terrain inexistant lors de mise à jour de terrain",e);
        }
    }
    public int deletepicture(String idpic, String idterrain)throws Exception{
        try {
            if (idterrain.equals("")) throw new Exception("Error terrain inexistant");
            return dao.deletepicture(idpic, idterrain);
        } catch (Exception e) {
            throw new Exception("Error terrain inexistant lors de suppression de picture terrain",e);
        }
    }  
    public int insertpicture(String photo, String id_terrain)throws Exception{
        try {
            if (id_terrain.equals("")) throw new Exception("Error terrain inexistant");
            int isNumber = Integer.parseInt(id_terrain);
            return dao.insertpicture(photo, id_terrain);
        } catch (Exception e) {
            throw new Exception("Error terrain inexistant lors de l'insertion picture terrain",e);
        }
    }
}
