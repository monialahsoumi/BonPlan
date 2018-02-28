/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

import java.sql.Date;

/**
 *
 * @author Meyssa
 */
public class DemandeEvent {
    private int idDemande;
    private String nom;
    private String description;
    private Date debut;
    private Date fin;
    private int nbPlace;
    private String typeEvent;
    private  int idDemandeur;
    private  int idProp;
    private String pseudoV;
    private boolean validite;

    
    public DemandeEvent() {
    }

    public DemandeEvent(int idDemande, String nom, String description, Date debut, Date fin, int nbPlace, String typeEvent, int idDemandeur, int idProp) {
        this.idDemande = idDemande;
        this.nom = nom;
        this.description = description;
        this.debut = debut;
        this.fin = fin;
        this.nbPlace = nbPlace;
        this.typeEvent = typeEvent;
        this.idDemandeur = idDemandeur;
        this.idProp = idProp;
    }

    public DemandeEvent(String nom, String description, Date debut, Date fin, int nbPlace, String typeEvent, int idDemandeur, int idProp) {
        this.nom = nom;
        this.description = description;
        this.debut = debut;
        this.fin = fin;
        this.nbPlace = nbPlace;
        this.typeEvent = typeEvent;
        this.idDemandeur = idDemandeur;
        this.idProp = idProp;
    }

    public DemandeEvent(int idDemande, String nom, String description, int nbPlace, String typeEvent, int idDemandeur, int idProp) {
        this.idDemande = idDemande;
        this.nom = nom;
        this.description = description;
        this.nbPlace = nbPlace;
        this.typeEvent = typeEvent;
        this.idDemandeur = idDemandeur;
        this.idProp = idProp;
    }

    public DemandeEvent(int idDemande, String nom, String description, Date debut, Date fin, int nbPlace, String typeEvent, int idDemandeur, int idProp, String pseudoV) {
        this.idDemande = idDemande;
        this.nom = nom;
        this.description = description;
        this.debut = debut;
        this.fin = fin;
        this.nbPlace = nbPlace;
        this.typeEvent = typeEvent;
        this.idDemandeur = idDemandeur;
        this.idProp = idProp;
        this.pseudoV = pseudoV;
    }

    public DemandeEvent(int idDemande, String nom, String description, Date debut, Date fin, int nbPlace, String typeEvent, int idDemandeur, int idProp, String pseudoV, boolean validite) {
        this.idDemande = idDemande;
        this.nom = nom;
        this.description = description;
        this.debut = debut;
        this.fin = fin;
        this.nbPlace = nbPlace;
        this.typeEvent = typeEvent;
        this.idDemandeur = idDemandeur;
        this.idProp = idProp;
        this.pseudoV = pseudoV;
        this.validite = validite;
    }
    
   

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public int getIdDemandeur() {
        return idDemandeur;
    }

    public void setIdDemandeur(int idDemandeur) {
        this.idDemandeur = idDemandeur;
    }

    public int getIdProp() {
        return idProp;
    }

    public void setIdProp(int idProp) {
        this.idProp = idProp;
    }

    public String getPseudoV() {
        return pseudoV;
    }

    public void setPseudoV(String pseudoV) {
        this.pseudoV = pseudoV;
    }

    public boolean isValidite() {
        return validite;
    }

    public void setValidite(boolean validite) {
        this.validite = validite;
    }

    @Override
    public String toString() {
        return "DemandeEvent{" + "idDemande=" + idDemande + ", nom=" + nom + ", description=" + description + ", debut=" + debut + ", fin=" + fin + ", nbPlace=" + nbPlace + ", idDemandeur=" + idDemandeur + ", idProp=" + idProp + '}';
    }

    
    
}
