package com.project_cloud_s5.hallo.model.terrain;

public class Terrain {
    
    private int id_terrain;
    private int id_proprietaire;
    private String desc_terrain;
    private String coord_location;
    private double surface;
    private int corbeille;
    
    private Terrain(){}
    
    private Terrain(int id,int id_proprietaire,String desc,String coord_location,double surface,int corb){
        setId_terrain(id);
        setId_proprietaire(id_proprietaire);
        setDesc_terrain(desc);
        setCoord_location(coord_location);
        setSurface(surface);
        setCorbeille(corb);
    }

    public Terrain(int id_proprietaire, String desc_terrain, String coord_location, double surface) {
        this.setId_proprietaire(id_proprietaire);
        this.setDesc_terrain(desc_terrain);
        this.setCoord_location(coord_location);
        this.setSurface(surface);
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


}
