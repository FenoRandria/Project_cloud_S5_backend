package com.project_cloud_s5.hallo.model.plante;

public class Plante {
    
    private int id_plante;
    private int id_categorie_culture;
    private String nom_plante;
    private double prixachat;
    private double prixvente;
    private String sprite_plante;
    private int placeingamemaker;
    
    public Plante(int id_plante, int id_categorie_culture, String nom_plante, double prixachat, double prixvente,
            String sprite_plante, int placeingamemaker) {
        set_Idplante(id_plante);
        setId_categorie_culture(id_categorie_culture);
        setNom_plante(nom_plante);
        setPrixachat(prixachat);
        setPrixvente(prixvente);
        setSprite_plante(sprite_plante);
        setPlaceingamemaker(placeingamemaker);
    }


    private Plante(){}


    public int getId_plante() {
        return id_plante;
    }
    public void set_Idplante(int id_type_plante) {
        this.id_plante = id_type_plante;
    }
    public int getId_categorie_culture() {
        return id_categorie_culture;
    }
    public void setId_categorie_culture(int id_categorie_culture) {
        this.id_categorie_culture = id_categorie_culture;
    }
    public String getNom_plante() {
        return nom_plante;
    }
    public void setNom_plante(String nom_plante) {
        this.nom_plante = nom_plante;
    }
    public double getPrixachat() {
        return prixachat;
    }
    public void setPrixachat(double prixachat) {
        this.prixachat = prixachat;
    }
    public double getPrixvente() {
        return prixvente;
    }
    public void setPrixvente(double prixvente) {
        this.prixvente = prixvente;
    }
    public String getSprite_plante() {
        return sprite_plante;
    }
    public void setSprite_plante(String sprite_plante) {
        this.sprite_plante = sprite_plante;
    }
    public int getPlaceingamemaker() {
        return placeingamemaker;
    }
    public void setPlaceingamemaker(int placeingamemaker) {
        this.placeingamemaker = placeingamemaker;
    }


}
