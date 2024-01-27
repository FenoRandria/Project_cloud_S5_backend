package com.project_cloud_s5.hallo.model.parcelle;

public class Parcelle 
{
    private int id_parcelle;
    private int id_terrain;
    private double surface;
    private double rendement;

    public Parcelle(){}

    public Parcelle(int id,int idterrain,double sur,double rend)
    {
        setId_parcelle(id);
        setId_terrain(idterrain);
        setSurface(sur);
        setRendement(rend);
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


}
