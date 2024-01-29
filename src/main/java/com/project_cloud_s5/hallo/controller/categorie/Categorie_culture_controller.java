package com.project_cloud_s5.hallo.controller.categorie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_cloud_s5.hallo.controller.exception.Gestion_exception;
import com.project_cloud_s5.hallo.controller.proprietaire.Proprietaire_controller;
import com.project_cloud_s5.hallo.model.categorie.Categorie_culture;
import com.project_cloud_s5.hallo.model.dto.Categorie_cultureDTO;
import com.project_cloud_s5.hallo.model.proprietaire.Proprietaire;
import com.project_cloud_s5.hallo.service.Categorie_culture_serve;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/categories")
public class Categorie_culture_controller {

    private final Categorie_culture_serve service;
    private static final Logger logger = LoggerFactory.getLogger(Proprietaire_controller.class);
    public Categorie_culture_controller(Categorie_culture_serve servivce)
    {
        this.service = servivce;
    }

    @GetMapping
    public ResponseEntity<Object> getCategorie_cultures() {
        try {
            List<Categorie_culture> list_Categorie_cultures = service.getCategorie_cultures();
            logger.info("Liste des Categorie_cultures récupérée avec succès : {}", list_Categorie_cultures);
            return Gestion_exception.generateResponse("Liste Categorie_cultures", HttpStatus.OK, list_Categorie_cultures);
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la récupération des categories cultures : {}", e.getMessage());
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "Erreur survenue lors de la récupération des Categorie_cultures");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategorie_culture_by_id(@PathVariable("id") String id) throws Exception {
        try {
            int categoryId = Integer.parseInt(id);
            Categorie_culture categorieCulture = service.getCategorie_cultureById(Integer.toString(categoryId));
            return Gestion_exception.generateResponse("Récupération de la catégorie réussie", HttpStatus.OK, categorieCulture);
        } catch (NumberFormatException e) {
            return Gestion_exception.generateResponse("L'ID doit être un nombre entier valide", HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération de la catégorie");
        }
    }

    @PostMapping
    public ResponseEntity<Object> insertCategorie_culture(@RequestBody Categorie_cultureDTO categorie) throws Exception
    {
        try {
            return Gestion_exception.generateResponse("categorie inserter ok: "+categorie.getNom(), HttpStatus.OK ,service.insertCategorie_culture(categorie.getNom()));
        } catch (Exception e) {
            // TODO: handle exception
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,"error survenue lors de insertion categorie");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategorieCulture(@PathVariable("id") String id )throws Exception
    {
        try {
            return Gestion_exception.generateResponse("delete categorie ok", HttpStatus.OK ,service.deleteCategorieCulture(id));
        } catch (Exception e) {
            // TODO: handle exception
            return Gestion_exception.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,"error survenue lors de suppression categorie");
        }
    }
    
}
