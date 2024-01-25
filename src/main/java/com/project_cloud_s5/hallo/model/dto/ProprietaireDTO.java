package com.project_cloud_s5.hallo.model.dto;

import java.time.LocalDate;

public class ProprietaireDTO {
    private String nom;
    private String mail;
    private String mdp;
    private LocalDate dtn;
    private String cmdp;




    public ProprietaireDTO(String nom, String mail, String mdp, LocalDate dtn, String cmdp) {
        setNom(nom);
        setMail(mail);
        setMdp(mdp);
        setDtn(dtn);
        setCmdp(cmdp);
    }

    public ProprietaireDTO() {}

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
    public LocalDate getDtn() {
        return dtn;
    }

    public void setDtn(LocalDate dtn) {
        this.dtn = dtn;
    }

    public String getCmdp() {
        return cmdp;
    }

    public void setCmdp(String cmdp) {
        this.cmdp = cmdp;
    }

}
