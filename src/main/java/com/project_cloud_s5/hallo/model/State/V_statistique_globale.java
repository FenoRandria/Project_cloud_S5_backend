package com.project_cloud_s5.hallo.model.State;

public class V_statistique_globale {
    private int total_plantes;
    private double chiffre_affaire;
    private int plante_vendu;
    private int plante_recolters;
    
    public V_statistique_globale() {
    }

    public int getTotal_plantes() {
        return total_plantes;
    }

    public void setTotal_plantes(int total_plantes) {
        this.total_plantes = total_plantes;
    }

    public double getChiffre_affaire() {
        return chiffre_affaire;
    }

    public void setChiffre_affaire(double chiffre_affaire) {
        this.chiffre_affaire = chiffre_affaire;
    }

    public int getPlante_vendu() {
        return plante_vendu;
    }

    public void setPlante_vendu(int plante_vendu) {
        this.plante_vendu = plante_vendu;
    }

    public int getPlante_recolters() {
        return plante_recolters;
    }

    public void setPlante_recolters(int plante_recolters) {
        this.plante_recolters = plante_recolters;
    }

    public V_statistique_globale(int total_plantes, double chiffre_affaire, int plante_vendu, int plante_recolters) {
        this.setTotal_plantes(total_plantes);
        this.setChiffre_affaire(chiffre_affaire);
        this.setPlante_vendu(plante_vendu);
        this.setPlante_recolters(plante_recolters);
    }
}
