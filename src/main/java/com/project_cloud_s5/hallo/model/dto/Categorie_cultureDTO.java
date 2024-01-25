package com.project_cloud_s5.hallo.model.dto;
public class Categorie_cultureDTO {
    private int id_categorie_culture;
    private String nom;
    private int corbeille;
    
    public Categorie_cultureDTO(int id_categorie_culture, String nom, int corbeille) {
        this.setId_categorie_culture(id_categorie_culture);
        this.setNom(nom);
        this.setCorbeille(corbeille);
    }

    public Categorie_cultureDTO(String nom) {
        this.setNom(nom);
    }

    public int getId_categorie_culture() {
        return id_categorie_culture;
    }

    public void setId_categorie_culture(int id_categorie_culture) {
        this.id_categorie_culture = id_categorie_culture;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCorbeille() {
        return corbeille;
    }

    public void setCorbeille(int corbeille) {
        this.corbeille = corbeille;
    }

    public Categorie_cultureDTO() {
    }
}
