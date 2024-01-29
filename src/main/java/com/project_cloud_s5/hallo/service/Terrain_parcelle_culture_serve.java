package com.project_cloud_s5.hallo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.Terrain_dao;
import com.project_cloud_s5.hallo.model.terrain.Terrain;

@Service
public class Terrain_parcelle_culture_serve {

    private final Terrain_serve terrain;
    private final Parcelle_serve parcelle;
    private final Categorie_culture_serve culture;
    private final Plante_serve plante;

    @Autowired 
    public Terrain_parcelle_culture_serve(Terrain_serve terrain,Parcelle_serve parcelle,Categorie_culture_serve culture,Plante_serve plante)
    {
        this.terrain = terrain;
        this.parcelle = parcelle;
        this.culture = culture;
        this.plante = plante;
    }   
    public List getTerrain_parcelle_culture() throws Exception
    {
        List list = new ArrayList<>();
        try {
            list.add(terrain.getTerrains());
            list.add(parcelle.getParcelles());
            list.add(culture.getCategorie_cultures());
            list.add(plante.getPlantes());
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
}
