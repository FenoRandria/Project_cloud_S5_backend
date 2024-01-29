package com.project_cloud_s5.hallo.model.plante;

public class PlanteWithCategorie {

    private int id_plante;
    private String nom_plante;
    private double prixachat;
    private double prixvente;
    private String sprite_plante;
    private int placeingamemaker;
    private int id_categorie_culture;
    private String nomcategorie;
    private int corbeille;
    public PlanteWithCategorie(int id_plante, String nom_plante, double prixachat, double prixvente,
            String sprite_plante, int placeingamemaker, int id_categorie_culture, String nomcategorie, int corbeille) {
        this.id_plante = id_plante;
        this.nom_plante = nom_plante;
        this.prixachat = prixachat;
        this.prixvente = prixvente;
        this.sprite_plante = sprite_plante;
        this.placeingamemaker = placeingamemaker;
        this.id_categorie_culture = id_categorie_culture;
        this.nomcategorie = nomcategorie;
        this.corbeille = corbeille;
    }
    public PlanteWithCategorie() {
    }
    public int getId_plante() {
        return id_plante;
    }
    public void setId_plante(int id_plante) {
        this.id_plante = id_plante;
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
