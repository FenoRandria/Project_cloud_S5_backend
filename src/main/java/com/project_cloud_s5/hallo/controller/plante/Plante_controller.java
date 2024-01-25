package com.project_cloud_s5.hallo.controller.plante;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_cloud_s5.hallo.model.plante.Plante;
import com.project_cloud_s5.hallo.service.Plante_serve;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/Plante")
public class Plante_controller {
    private final Plante_serve service;
    public Plante_controller(Plante_serve servivce)
    {
        this.service = servivce;
    }

    @GetMapping("/get")
    public List<Plante> get_all_Plantes() {
        List<Plante> list_Plantes = null;
        try {
            list_Plantes = service.getPlantes();

            // return ResponseHandler.generateResponse("Liste Plante",HttpStatus.OK,list_Plantes)
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list_Plantes;
    }

    @GetMapping("/getByCategorie")
    public List<Plante> getPlantesByCategorie(@RequestParam String idcategorie) {
        return service.getPlantesByCategorie(idcategorie);
    }

    @GetMapping("/getforgames")
    public List<Plante> getPlantesGames() {
        return service.getPlantesGames();
    }

    @GetMapping("/getbyid")
    public Plante getPlandeByid(@RequestParam int id){
        return service.getPlandeByid(id);
    }

    @PostMapping("/insert")
    public void insertplante(@RequestParam int idcategorie,@RequestParam String nom,@RequestParam double prixachat,@RequestParam double prixvente){
        service.insertplante(idcategorie, nom, prixachat,prixvente);
    }

    @PostMapping("/update/nom_plante")
    public void updateNom_plante(@RequestParam int id,@RequestParam String nom)  {
        service.updateNom_plante(id, nom);
    }

    @PostMapping("/update/prix")
    public void updatePrix(@RequestParam int id,@RequestParam double prix)  {
        service.updatePrix(id, prix);
    }

    @PostMapping("/update/placeingamemaker")
    public void updatePlaceingamemaker(@RequestParam int id,@RequestParam int place)  {
        service.updatePlaceingamemaker(id, place);
    }
    
    @PostMapping("/update/spriteplante")
    public void updateSpritePlante(@RequestParam int id,@RequestParam String sprite)  {
        service.updateSpritePlante(id, sprite);
    }

    @PostMapping("/delete")
    public void deleteplante(@RequestParam int id)throws Exception{
        service.deleteplante(id);
    }



    


  
    
}
