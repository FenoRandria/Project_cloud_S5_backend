package com.project_cloud_s5.hallo.controller.plante;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_cloud_s5.hallo.controller.exception.Gestion_exception;
import com.project_cloud_s5.hallo.model.parcelle.Parcelle;
import com.project_cloud_s5.hallo.model.plante.Plante;
import com.project_cloud_s5.hallo.model.plante.PlanteWithCategorie;
import com.project_cloud_s5.hallo.service.Plante_serve;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/plantes")
public class Plante_controller {
    private final Plante_serve service;
    public Plante_controller(Plante_serve servivce)
    {
        this.service = servivce;
    }

    @GetMapping
    public ResponseEntity<Object> get_all_Plantes() {
        try {
            List<PlanteWithCategorie> list_Plantes = service.getPlantes();
            return Gestion_exception.generateResponse("Liste des plantes récupérée avec succès", HttpStatus.OK, list_Plantes);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la récupération de la liste des plantes", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/categories/{id_categorie}")
    public ResponseEntity<Object> getPlantesByCategorie(@PathVariable("id_categorie") String id_categorie) {
        try {
            List<PlanteWithCategorie> list_Plantes = service.getPlantesByCategorie(id_categorie);
            return Gestion_exception.generateResponse("Liste des plantes by categorie récupérée avec succès", HttpStatus.OK, list_Plantes);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la récupération de la liste des plantes", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/games")
    public ResponseEntity<Object> getPlantesGames() {
        try {
            List<PlanteWithCategorie> list_Plantes = service.getPlantesGames();
            return Gestion_exception.generateResponse("Liste des plantes récupérée avec succès", HttpStatus.OK, list_Plantes);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la récupération de la liste des plantes", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlandeByid(@PathVariable("id") int id){
        try {
            return Gestion_exception.generateResponse("plante récupérée avec succès", HttpStatus.OK, service.getPlandeByid(id));
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la récupération de la plante", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> insertplante(@RequestBody Plante plante) throws Exception{
        if (plante == null) throw new Exception("plante null");
        try {
            return Gestion_exception.generateResponse("plante enregistrer avec succès", HttpStatus.OK, service.insertplante(plante.getId_categorie_culture(), plante.getNom_plante(), plante.getPrixachat(),plante.getPrixvente()));
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la récupération de la plante", HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Plante plante)  {
        try {
            if (plante != null) {
                int id = plante.getId_plante();
                service.updatePlaceingamemaker(id, plante.getPlaceingamemaker());
                service.updateSpritePlante(id, plante.getSprite_plante());
                service.updatePrix(id, plante.getPrixvente());
                service.updateNom_plante(id, plante.getNom_plante());
            }
            return Gestion_exception.generateResponse("plante mise a jour avec succès", HttpStatus.OK, "valid");
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la mise a jour de la plante", HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteplante(@RequestParam int id)throws Exception{
        try {
            return Gestion_exception.generateResponse("plante delete avec succès", HttpStatus.OK, service.deleteplante(id));
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la suppression de la plante", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }



    


  
    
}
