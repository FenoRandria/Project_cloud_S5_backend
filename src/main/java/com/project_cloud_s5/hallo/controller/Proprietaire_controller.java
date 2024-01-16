package com.project_cloud_s5.hallo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_cloud_s5.hallo.model.Proprietaire;
import com.project_cloud_s5.hallo.service.Proprietaire_serve;

import java.util.List;

import org.springframework.http.HttpStatus;
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
    public List<Proprietaire> get_all_proprietaires() {
        List<Proprietaire> list_proprietaires = null;
        try {
            list_proprietaires = service.getProprietaires();

            // return ResponseHandler.generateResponse("Liste proprietaire",HttpStatus.OK,list_proprietaires)
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list_proprietaires;
    }
    @PostMapping("/login")
    public Proprietaire seLogin(@RequestParam String mail, @RequestParam String mdp) throws Exception
    {
        try {
            return service.seLogin(mail, mdp);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }
    
}
