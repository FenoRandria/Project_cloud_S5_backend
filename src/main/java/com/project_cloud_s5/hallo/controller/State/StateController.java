package com.project_cloud_s5.hallo.controller.State;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_cloud_s5.hallo.controller.exception.Gestion_exception;
import com.project_cloud_s5.hallo.model.State.Benefice;
import com.project_cloud_s5.hallo.model.State.V_planteUtilise;
import com.project_cloud_s5.hallo.model.proprietaire.Proprietaire;
import com.project_cloud_s5.hallo.service.BeneficeService;
import com.project_cloud_s5.hallo.service.V_PlanteVenteService;

@RestController
@CrossOrigin
@RequestMapping("api/statistiques")
public class StateController {

    @Autowired
    private final V_PlanteVenteService servePLante;

    @Autowired
    private final BeneficeService serveBen;

    public StateController(V_PlanteVenteService servePlante, BeneficeService serveBen) {
        this.servePLante = servePlante;
        this.serveBen = serveBen;
    }

    @GetMapping("/plante-utiliser")
    public ResponseEntity<Object> getStatePlanteUtiliser() {
        try {
            List<V_planteUtilise> ls = servePLante.getPlanteUtilise();
            // Logger.info("Liste des propriétaires récupérée avec succès : {}",
            // list_proprietaires);
            return Gestion_exception.generateResponse("PLante Utiliser ", HttpStatus.OK, ls);
        } catch (Exception e) {
            // logger.error("Une erreur s'est produite lors de la récupération des
            // propriétaires : {}", e.getMessage());
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur survenue lors de la récupération des Plantes Utilser");
        }
    }

    @GetMapping("/plante-utiliser/{limit}")
    public ResponseEntity<Object> getStatePlanteUtiliserTop(@PathVariable("limit") int limit) {
        try {
            List<V_planteUtilise> ls = servePLante.getTopPlante(limit);
            // Logger.info("Liste des propriétaires récupérée avec succès : {}",
            // list_proprietaires);
            return Gestion_exception.generateResponse("PLante Utiliser ", HttpStatus.OK, ls);
        } catch (Exception e) {
            // logger.error("Une erreur s'est produite lors de la récupération des
            // propriétaires : {}", e.getMessage());
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur survenue lors de la récupération des Plantes Utilser");
        }
    }

    @GetMapping("/benefice/{mois}&&{anne}")
    public ResponseEntity<Object> getStateBenefice(@PathVariable("mois") int mois, @PathVariable("anne") int anne) {
        try {
            List<Benefice> ls = serveBen.getBenefice(mois, anne);
            // Logger.info("Liste des propriétaires récupérée avec succès : {}",
            // list_proprietaires);
            return Gestion_exception.generateResponse("Benefice ", HttpStatus.OK, ls);
        } catch (Exception e) {
            // logger.error("Une erreur s'est produite lors de la récupération des
            // propriétaires : {}", e.getMessage());
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur survenue lors de la récupération des Plantes Utilser");
        }
    }
}
