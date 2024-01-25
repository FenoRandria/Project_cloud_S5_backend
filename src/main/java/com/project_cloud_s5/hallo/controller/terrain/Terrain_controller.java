package com.project_cloud_s5.hallo.controller.terrain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project_cloud_s5.hallo.model.terrain.Terrain;
import com.project_cloud_s5.hallo.service.Terrain_serve;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/terrain")
public class Terrain_controller {

    private final Terrain_serve service;
    public Terrain_controller(Terrain_serve servivce)
    {
        this.service = servivce;
    }
    @GetMapping("/all")
    public List<Terrain> get_all_Terrains() {
        List<Terrain> list_Terrains = null;
        try {
            list_Terrains = service.getTerrains();

            // return ResponseHandler.generateResponse("Liste Terrain",HttpStatus.OK,list_Terrains)
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list_Terrains;
    }

    @PostMapping("/insert")
    public void insertTerrain(@RequestParam int idproprio, @RequestParam int nbparcelle, @RequestParam String desc, @RequestParam String coord,@RequestParam double longueur,@RequestParam double largeur) throws Exception
    {
        service.insertTerrain(idproprio, nbparcelle, desc, coord, longueur, largeur);
        
    }

    @GetMapping("/allbyId")
    public Terrain getTerrainById(@RequestParam String id) {
       return service.getTerrainById(id);
    }

    @DeleteMapping("/delete")
    public void deleteterrain(@RequestParam int idterrain)throws Exception{
        service.deleteterrain(idterrain);
    } 

    @PostMapping("/update/description")
    public void updateTerrainDesc(@RequestParam String description, @RequestParam String idterrain)throws Exception{
        service.updateTerrainDesc(description, idterrain);
    }

    @PostMapping("/update/surface")
    public void updateTerrainSurface(@RequestParam double longueur,@RequestParam double largeur,@RequestParam  String idterrain)throws Exception{
        service.updateTerrainSurface(longueur, largeur, idterrain);
    }

    @DeleteMapping("/delete/photo")
    public void deletepicture(@RequestParam String idpic,@RequestParam  String idterrain)throws Exception{
        service.deletepicture(idpic, idterrain);
    } 

    @PostMapping("/insert/photo")
    public void insertpicture(@RequestParam String picname,@RequestParam  String idterrain)throws Exception{
        
        service.insertpicture(picname, idterrain);
    }


}
