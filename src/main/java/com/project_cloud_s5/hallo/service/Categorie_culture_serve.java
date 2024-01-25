package com.project_cloud_s5.hallo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.project_cloud_s5.hallo.dao.Categorie_culture_dao;
import com.project_cloud_s5.hallo.model.categorie.Categorie_culture;

@Service
public class Categorie_culture_serve {
    private final Categorie_culture_dao dao;
    @Autowired 
    public Categorie_culture_serve(Categorie_culture_dao dao)
    {
        this.dao = dao;
    }   
    public List<Categorie_culture> getCategorie_cultures() {
        return dao.getCategorie_cultures();
    }
    public Categorie_culture getCategorie_cultureById(String id) throws Exception {
        try {
            if (id.equals("")) {
                throw new IllegalArgumentException("L'ID Invalid n'est pas etre null");
            }
            return dao.getCategorie_cultureByid(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("L'ID doit être un nombre entier valide", e);
        }
    }
    public int insertCategorie_culture(String nomcategorie)throws Exception{
        if (nomcategorie.equals("")) {
            throw new Exception("nom categorie invalid n'est pas etre null");
        }
        return dao.insertCategorie_culture(nomcategorie);
    }
    public int deleteCategorieCulture(String id)throws Exception{
        try {
            Integer.parseInt(id);
            if (id.equals("")) {
                throw new IllegalArgumentException("L'ID Invalid n'est pas etre null");
            }
            return dao.deleteCategorieCulture(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("L'ID doit être un nombre entier valide", e);
        }
    }
}
