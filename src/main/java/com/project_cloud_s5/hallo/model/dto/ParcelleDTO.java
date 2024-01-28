package com.project_cloud_s5.hallo.model.dto;

public class ParcelleDTO {
    private int id_parcelle;
    private int id_terrain;
    private double surface;
    private double rendement;
    private double longueur;
    private double largeur;
    
    public ParcelleDTO() {
    }
    public ParcelleDTO(int id_parcelle, int id_terrain, double surface, double rendement, double longueur,
            double largeur) {
        this.setId_parcelle(id_parcelle);
        this.setId_terrain(id_terrain);
        this.setSurface(surface);
        this.setRendement(rendement);
        this.setLongueur(longueur);
        this.setLargeur(largeur);
    }
    public int getId_parcelle() {
        return id_parcelle;
    }
    public void setId_parcelle(int id_parcelle) {
        this.id_parcelle = id_parcelle;
    }
    public int getId_terrain() {
        return id_terrain;
    }
    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }
    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface) {
        this.surface = surface;
    }
    public double getRendement() {
        return rendement;
    }
    public void setRendement(double rendement) {
        this.rendement = rendement;
    }
    public double getLongueur() {
        return longueur;
    }
    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }
    public double getLargeur() {
        return largeur;
    }
    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }
}

