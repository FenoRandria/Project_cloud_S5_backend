package com.project_cloud_s5.hallo.controller.parcelle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_cloud_s5.hallo.controller.exception.Gestion_exception;
import com.project_cloud_s5.hallo.model.Token.Token;
import com.project_cloud_s5.hallo.model.dto.ParcelleDTO;
import com.project_cloud_s5.hallo.model.parcelle.Parcelle;
import com.project_cloud_s5.hallo.service.Parcelle_serve;

import java.util.List;

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
@RequestMapping("api/parcelles")
public class Parcelle_controller {
    private final Parcelle_serve service;

    public Parcelle_controller(Parcelle_serve servivce) {
        this.service = servivce;
    }

    @GetMapping
    public ResponseEntity<Object> getAllParcelles() {
        try {
            List<Parcelle> parcelles = service.getParcelles();
            return Gestion_exception.generateResponse("Liste des parcelles récupérée avec succès", HttpStatus.OK,
                    parcelles);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la récupération de la liste des parcelles",
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> insertParcelle(@RequestBody ParcelleDTO parcelleDTO) {
        try {
            service.insertParcelle(parcelleDTO.getId_terrain(), parcelleDTO.getLongueur(), parcelleDTO.getLargeur(),
                    parcelleDTO.getRendement());
            return Gestion_exception.generateResponse("Parcelle insérée avec succès", HttpStatus.OK, null);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de l'insertion de la parcelle",
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParcelleById(@PathVariable("id") String id) {
        try {
            // int idUser = new Token().ToToken(token).getUtilisateur();
            Parcelle parcelle = service.getParcelleById(id);
            return Gestion_exception.generateResponse("Parcelle récupérée avec succès", HttpStatus.OK, parcelle);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la récupération de la parcelle par ID",
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/surface")
    public ResponseEntity<Object> updateSurface(@RequestBody ParcelleDTO parcelleDTO) {
        try {
            service.updateSurface(parcelleDTO.getId_parcelle(), parcelleDTO.getLongueur(), parcelleDTO.getLargeur());
            return Gestion_exception.generateResponse("Surface de la parcelle mise à jour avec succès", HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la mise à jour de la surface de la parcelle",
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/rendement")
    public ResponseEntity<Object> updateRendement(@RequestBody ParcelleDTO parcelleDTO) {
        try {
            service.updateRendement(parcelleDTO.getId_parcelle(), parcelleDTO.getRendement());
            return Gestion_exception.generateResponse("Rendement de la parcelle mis à jour avec succès", HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la mise à jour du rendement de la parcelle",
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParcelle(@PathVariable("id") int id) {
        try {
            service.deleteParcelle(id);
            return Gestion_exception.generateResponse("Parcelle supprimée avec succès", HttpStatus.OK, null);
        } catch (Exception e) {
            return Gestion_exception.generateResponse("Erreur lors de la suppression de la parcelle",
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
