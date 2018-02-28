/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

import java.sql.Date;

/**
 *
 * @author firas
 */
public class Reservation {

    private int id;
    private int nbrPlace;
    private Date dateReservation;
    private int telephone;
    private String heure;
    private String etat;
    private String email;
    private String description;
    private String nomPlan;
    private int iduserV;
    private int iduserP;

    public Reservation(String etat, String description, int id) {

        this.etat = etat;
        this.description = description;
        this.id = id;
    }

    public Reservation(int nbrPlace, Date dateReservation, int telephone, String heure, String etat, String email) {
        this.nbrPlace = nbrPlace;
        this.dateReservation = dateReservation;
        this.telephone = telephone;
        this.heure = heure;
        this.etat = etat;
        this.email = email;
    }

    public Reservation(String etat) {
        this.etat = etat;
    }

    public Reservation() {
    }

    public Reservation(int id, int nbrPlace, Date dateReservation, int telephone, String heure, String etat, String email) {
        this.id = id;
        this.nbrPlace = nbrPlace;
        this.dateReservation = dateReservation;
        this.telephone = telephone;
        this.heure = heure;
        this.etat = etat;
        this.email = email;
    }

    public Reservation(int nbrPlace, int telephone, String heure) {
        this.nbrPlace = nbrPlace;
        this.telephone = telephone;
        this.heure = heure;
    }

    
    public Reservation(int nbrPlace, int telephone, String heure, int iduserV,int iduserP,Date dateReservation) {
        this.nbrPlace = nbrPlace;
        this.telephone = telephone;
        this.heure = heure;
        this.iduserV=iduserV;
        this.iduserP=iduserP;
        this.dateReservation=dateReservation;
    }

    public Reservation(int nbrPlace, Date dateReservation, int telephone, String heure) {
        this.nbrPlace = nbrPlace;
        this.dateReservation = dateReservation;
        this.telephone = telephone;
        this.heure = heure;
    }

    public Reservation(String description, int id, int nbrPlace, Date dateReservation, int telephone, String heure, String etat, String nomPlan) {
        this.id = id;
        this.nbrPlace = nbrPlace;
        this.dateReservation = dateReservation;
        this.telephone = telephone;
        this.heure = heure;
        this.etat = etat;
        this.nomPlan = nomPlan;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomPlan() {
        return nomPlan;
    }

    public void setNomPlan(String nomPlan) {
        this.nomPlan = nomPlan;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIduserV() {
        return iduserV;
    }

    public void setIduserV(int iduserV) {
        this.iduserV = iduserV;
    }

    public int getIduserP() {
        return iduserP;
    }

    public void setIduserP(int iduserP) {
        this.iduserP = iduserP;
    }

    
}
