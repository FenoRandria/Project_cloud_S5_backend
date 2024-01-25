package com.project_cloud_s5.hallo.controller.parcelle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_cloud_s5.hallo.model.parcelle.Parcelle;
import com.project_cloud_s5.hallo.service.Parcelle_serve;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/parcelle")
public class Parcelle_controller {
    private final Parcelle_serve service;
    public Parcelle_controller(Parcelle_serve servivce)
    {
        this.service = servivce;
    }

    @GetMapping("/get")
    public List<Parcelle> get_all_Parcelles() {
        List<Parcelle> list_Parcelles = null;
        try {
            list_Parcelles = service.getParcelles();

            // return ResponseHandler.generateResponse("Liste Parcelle",HttpStatus.OK,list_Parcelles)
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list_Parcelles;
    }

    @PostMapping("/insert")
    public void insertParcelle(@RequestParam int idterrain,@RequestParam double longueur,@RequestParam double largeur,@RequestParam double rendement) throws Exception
    {
        service.insertParcelle(idterrain, longueur, largeur, rendement);
        
    }

    @GetMapping("/getById")
    public Parcelle getParcelleByid(@RequestParam String id) {
        return service.getParcelleById(id);
    }

    @GetMapping("/getByTerrain")
    public List<Parcelle> getByTerrain(@RequestParam String idterrain) {
        return service.getParcellesByTerrain(idterrain);
    }

    @PostMapping("/updateSurface")
    public void updateSurface(@RequestParam int idparcelle,@RequestParam double longueur,@RequestParam double largeur)throws Exception{
        service.updateSurface(idparcelle, longueur, largeur);
    }

    @PostMapping("/updateRendement")
    public void updateSurface(@RequestParam int id,@RequestParam double rendement)throws Exception{
        service.updateRendement(id, rendement);
    }

    @PostMapping("/delete")
    public void deleteparcelle(@RequestParam int id)throws Exception{
        service.deleteparcelle(id);
    }
    
}
