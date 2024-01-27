package com.project_cloud_s5.hallo.model.dto;

public class TerrainDTO {
    private int id_terrain;
    private int id_proprietaire;
    private String desc_terrain;
    private String coord_location;
    private double surface;
    private int corbeille;

    private int id_photos_terrain;
    private String photo;
    private double longueur;
    private double largeur;
    private int nombre_parcelle;

    public TerrainDTO() {
    }
    public TerrainDTO(int id_terrain, int id_proprietaire, String desc_terrain, String coord_location, double surface,
            int corbeille, int id_photos_terrain, String photo,double longueur,double largeur,int nombre_parcelle) {
        this.setId_terrain(id_terrain);
        this.setId_proprietaire(id_proprietaire);
        this.setDesc_terrain(desc_terrain);
        this.setCoord_location(coord_location);
        this.setSurface(surface);
        this.setCorbeille(corbeille);
        this.setId_photos_terrain(id_photos_terrain);
        this.setPhoto(photo);
        this.setLongueur(longueur);
        this.setLargeur(largeur);
        this.setNombre_parcelle(nombre_parcelle);

    }
    public int getId_terrain() {
        return id_terrain;
    }
    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }
    public int getId_proprietaire() {
        return id_proprietaire;
    }
    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }
    public String getDesc_terrain() {
        return desc_terrain;
    }
    public void setDesc_terrain(String desc_terrain) {
        this.desc_terrain = desc_terrain;
    }
    public String getCoord_location() {
        return coord_location;
    }
    public void setCoord_location(String coord_location) {
        this.coord_location = coord_location;
    }
    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface) {
        this.surface = surface;
    }
    public int getCorbeille() {
        return corbeille;
    }
    public void setCorbeille(int corbeille) {
        this.corbeille = corbeille;
    }
    public int getId_photos_terrain() {
        return id_photos_terrain;
    }
    public void setId_photos_terrain(int id_photos_terrain) {
        this.id_photos_terrain = id_photos_terrain;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
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
    public int getNombre_parcelle() {
        return nombre_parcelle;
    }
    public void setNombre_parcelle(int nombre_parcelle) {
        this.nombre_parcelle = nombre_parcelle;
    }
}
