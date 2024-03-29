package com.project_cloud_s5.hallo.controller.proprietaire;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project_cloud_s5.hallo.controller.exception.Gestion_exception;
import com.project_cloud_s5.hallo.model.Token.Token;
import com.project_cloud_s5.hallo.model.dto.ProprietaireDTO;
import com.project_cloud_s5.hallo.model.proprietaire.Messagerie;
import com.project_cloud_s5.hallo.model.proprietaire.MessagerieProprietaire;
import com.project_cloud_s5.hallo.model.proprietaire.Proprietaire;
import com.project_cloud_s5.hallo.service.Messagerie_serve;
import com.project_cloud_s5.hallo.service.Proprietaire_serve;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@CrossOrigin
@RequestMapping("api/proprietaires")
public class Proprietaire_controller {
    @Autowired
    Messagerie_serve messagerieService;
    private final Proprietaire_serve service;
    private static final Logger logger = LoggerFactory.getLogger(Proprietaire_controller.class);

    public Proprietaire_controller(Proprietaire_serve servivce) {
        this.service = servivce;
    }

    @GetMapping
    public ResponseEntity<Object> get_all_proprietaires() {
        try {
            List<Proprietaire> list_proprietaires = service.getProprietaires();
            logger.info("Liste des propriétaires récupérée avec succès : {}", list_proprietaires);
            return Gestion_exception.generateResponse("Liste proprietaires", HttpStatus.OK, list_proprietaires);
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la récupération des propriétaires : {}", e.getMessage());
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur survenue lors de la récupération des propriétaires");
        }
    }

    @GetMapping("/token")
    public ResponseEntity<Object> getProprietaireId(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            String idEnvoyeur = Integer.toString(new Token().ToToken(token).getUtilisateur());
            Proprietaire list_proprietaires = service.getProprietaires(idEnvoyeur);
            logger.info("Proprietaire récupérée avec succès : {}", list_proprietaires);
            return Gestion_exception.generateResponse("proprietaire", HttpStatus.OK, list_proprietaires);
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la récupération des propriétaires : {}", e.getMessage());
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur survenue lors de la récupération des propriétaires");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> seLogin(@RequestBody ProprietaireDTO proprietaire) throws Exception {
        try {
            return Gestion_exception.generateResponse("proprietaire connected: " + proprietaire.getMail(),
                    HttpStatus.OK, service.seLogin(proprietaire.getMail(), proprietaire.getMdp()));
        } catch (Exception e) {
            // TODO: handle exception
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                    "error survenue lors de proprietaire selogin");
        }
    }

    @PostMapping("/inscrire")
    public ResponseEntity<Object> inscrire(@RequestBody ProprietaireDTO proprietaire) throws Exception {
        java.sql.Date date = java.sql.Date.valueOf(proprietaire.getDtn());
        if (calculateAge(date.toLocalDate()) < 12)
            return Gestion_exception.generateResponse("must be at least 12 years old ", HttpStatus.BAD_REQUEST,
                    "Invalid date of birth");
        if (proprietaire.getMail().split("@").length < 2)
            return Gestion_exception.generateResponse("Mail must be contains @", HttpStatus.BAD_REQUEST,
                    "Invalid mail");
        try {
            return Gestion_exception.generateResponse("registre proprietaire", HttpStatus.OK, service.inscrire(
                    new Proprietaire(proprietaire.getNom(), proprietaire.getMail(), proprietaire.getMdp(), date),
                    proprietaire.getCmdp()));
        } catch (Exception e) {
            // TODO: handle exception
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
                    "error survenue lors de proprietaire s'inscrire");
        }
    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - birthDate.getYear();
    }

    // --------------------------------------------------------------------////STRING
    // TOKEN JSON
    @PostMapping("messagerie")
    public ResponseEntity<Object> nouveauMessage(@RequestBody Messagerie messagerie, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            String idEnvoyeur = Integer.toString(new Token().ToToken(token).getUtilisateur());
            messagerie.setIdEnvoyeur(idEnvoyeur);
            messagerieService.nouveauMessage(messagerie);
            return Gestion_exception.generateResponse("Message envoye", HttpStatus.OK, messagerie);
        } catch (Exception exception) {
            System.out.println("Erreur: " + exception.getMessage());
            exception.printStackTrace();
            return Gestion_exception.generateResponse("Message invalid", HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getMessage());
        }
    }

    @PostMapping("auth")
    public ResponseEntity<Object> auth(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            System.out.println("---------------------------------------------- "+Integer.toString(new Token().ToToken(token).getUtilisateur()));
            return Gestion_exception.generateResponse("Token valid", HttpStatus.OK,Integer.toString(new Token().ToToken(token).getUtilisateur()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return Gestion_exception.generateResponse("Message invalid", HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getMessage());
        }
    }


    @PostMapping("discussions")
    public ResponseEntity<Object> discussions(@RequestBody Messagerie messagerie, @RequestHeader("Authorization") String authorizationHeader ) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            String idEnvoyeur = Integer.toString(new Token().ToToken(token).getUtilisateur());
            System.err.println(messagerie.getIdReceveur());
            List<Messagerie> discussions = messagerieService.getDiscussions(idEnvoyeur, messagerie.getIdReceveur());
            List<Proprietaire> proprietaires = service.getProprietaires();
            List<MessagerieProprietaire> list_discussions = new MessagerieProprietaire()
                    .joinMessagerieProprietaire(discussions, proprietaires);
            return Gestion_exception.generateResponse("discussions", HttpStatus.OK, list_discussions);
        } catch (Exception exception) {
            System.out.println("Erreur: " + exception.getMessage());
            exception.printStackTrace();
            return Gestion_exception.generateResponse("discussions error", HttpStatus.OK, exception.getMessage());
        }
    }

    @PostMapping("discussions/last/{id_receiver}")
    public ResponseEntity<Object> getLastMessagesForReceiver(@PathVariable("id_receiver") String id_receiver, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            String id_receiverToken = Integer.toString(new Token().ToToken(token).getUtilisateur());
            List<Messagerie> discussions = messagerieService.getLastMessagesForReceiver(id_receiverToken);
            List<Proprietaire> proprietaires = service.getProprietaires();
            List<MessagerieProprietaire> list_discussions = new MessagerieProprietaire()
                    .joinMessagerieProprietaire(discussions, proprietaires);
            return Gestion_exception.generateResponse("discussions", HttpStatus.OK, list_discussions);
        } catch (Exception exception) {
            System.out.println("Erreur: " + exception.getMessage());
            exception.printStackTrace();
            return Gestion_exception.generateResponse("last discussions error", HttpStatus.OK, exception.getMessage());
        }
    }
}
