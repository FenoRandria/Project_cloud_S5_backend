package com.project_cloud_s5.hallo.model;
import java.util.Date;

public class Proprietaire {
    private int id;
    private String nom;
    private String mail;
    private String mdp;
    private Date dtn;
    private int corbeil;


    public Proprietaire(int id, String nom, String mail, String mdp, Date dtn, int corbeil) {
        setId(id);
        setNom(nom);
        setMail(mail);
        setMdp(mdp);
        setDtn(dtn);
        setCorbeil(corbeil);
    }
    public Proprietaire() {}

    public int getId() {
        return id;
    }

    


    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public int getCorbeil() {
        return corbeil;
    }

    public void setCorbeil(int corbeil) {
        this.corbeil = corbeil;
    }
}
