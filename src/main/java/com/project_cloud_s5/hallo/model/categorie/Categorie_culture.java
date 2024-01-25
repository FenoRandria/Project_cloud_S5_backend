package com.project_cloud_s5.hallo.model.categorie;

public class Categorie_culture {
    
    private int id_categorie_culture;
    private String nomcategorie;
    private int corbeille;

    public Categorie_culture(){}

    public Categorie_culture(int id,String nomcategorie){}

    public int getId_categorie_culture() {
        return id_categorie_culture;
    }
    public void setId_categorie_culture(int id_categorie_culture) {
        this.id_categorie_culture = id_categorie_culture;
    }
    public String getNomcategorie() {
        return nomcategorie;
    }
    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public int getCorbeille() {
        return corbeille;
    }

    public void setCorbeille(int corbeille) {
        this.corbeille = corbeille;
    }


    


}
