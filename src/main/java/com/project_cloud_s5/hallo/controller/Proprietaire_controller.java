package com.project_cloud_s5.hallo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_cloud_s5.hallo.model.Proprietaire;
import com.project_cloud_s5.hallo.service.Proprietaire_serve;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/proprietaire")
public class Proprietaire_controller {
    private final Proprietaire_serve service;
    public Proprietaire_controller(Proprietaire_serve servivce)
    {
        this.service = servivce;
    }
    @GetMapping("/all")
    public ResponseEntity<Object> get_all_proprietaires() {
        try {
            List<Proprietaire> list_proprietaires = service.getProprietaires();
            return Gestion_exception.generateResponse("Liste proprietaires", HttpStatus.OK ,list_proprietaires);
        } catch (Exception e) {
            // TODO: handle exception
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,"error survenue lors de proprietaire");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Object> seLogin(@RequestParam String mail, @RequestParam String mdp) throws Exception
    {
        try {
            return Gestion_exception.generateResponse("proprietaire connected: "+mail, HttpStatus.OK ,service.seLogin(mail, mdp));
        } catch (Exception e) {
            // TODO: handle exception
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,"error survenue lors de proprietaire selogin");
        }
    }
    @PostMapping("/inscrire")
    public ResponseEntity<Object> inscrire(@RequestParam String nom, @RequestParam String mail, @RequestParam String mdp, @RequestParam String cmdp,@RequestParam String dtn) throws Exception
    {
        java.sql.Date date = java.sql.Date.valueOf(dtn);
        if (calculateAge(date.toLocalDate()) < 12) 
            return Gestion_exception.generateResponse("must be at least 12 years old ", HttpStatus.BAD_REQUEST ,"Invalid date of birth");
        if (mail.split("@").length < 2) 
            return Gestion_exception.generateResponse("Mail must be contains @", HttpStatus.BAD_REQUEST ,"Invalid mail");
        try {
            return Gestion_exception.generateResponse("registre proprietaire", HttpStatus.OK ,service.inscrire(new Proprietaire(0,nom,mail,mdp,date,0),cmdp));
        } catch (Exception e) {
            // TODO: handle exception
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,"error survenue lors de proprietaire s'inscrire");
        }

        
    }

    private int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - birthDate.getYear();
    }
   

    
}
