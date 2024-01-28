package com.project_cloud_s5.hallo.model.proprietaire;

import java.util.ArrayList;
import java.util.List;

public class MessagerieProprietaire {
    Messagerie messagerie;
    Proprietaire envoyeur;
    Proprietaire receveur;
    public Messagerie getMessagerie() {
        return messagerie;
    }
    public void setMessagerie(Messagerie messagerie) {
        this.messagerie = messagerie;
    }
    public Proprietaire getEnvoyeur() {
        return envoyeur;
    }
    public void setEnvoyeur(Proprietaire envoyeur) {
        this.envoyeur = envoyeur;
    }
    public Proprietaire getReceveur() {
        return receveur;
    }
    public void setReceveur(Proprietaire receveur) {
        this.receveur = receveur;
    }
    public List<MessagerieProprietaire> joinMessagerieProprietaire(List<Messagerie> messageries, List<Proprietaire> proprietaires) {
        List<MessagerieProprietaire> messagerieProprietaires = new ArrayList<>();
    
        for (Messagerie messagerie : messageries) {
            MessagerieProprietaire messagerieProprietaire = new MessagerieProprietaire();
            messagerieProprietaire.setMessagerie(messagerie);
    
            for (Proprietaire proprietaire : proprietaires) {
                if (messagerie.getIdEnvoyeur().compareTo(Integer.toString(proprietaire.getId()))==0) {
                    messagerieProprietaire.setEnvoyeur(proprietaire);
                }
                if (messagerie.getIdReceveur().compareTo(Integer.toString(proprietaire.getId()))==0) {
                    messagerieProprietaire.setReceveur(proprietaire);
                }
            }
            messagerieProprietaires.add(messagerieProprietaire);
        }
    
        return messagerieProprietaires;
    }
    
}
