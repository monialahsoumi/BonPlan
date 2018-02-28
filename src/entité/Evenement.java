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
public class Evenement {

    private int idEv;
    private String nomEv;
    private String descEv;
    private Date debutEv;
    private Date finEv;
    private int nbPlace;
    private String typeEv;
    private int idprop;
    private String etat;
    private int nbParticipant;
   
 
 
    public Evenement(String nomEv, String descEv, Date debutEv, Date finEv, int nbPlace, int propid, String type) {
        this.nomEv = nomEv;
        this.descEv = descEv;
        this.debutEv = debutEv;
        this.finEv = finEv;
        this.nbPlace = nbPlace;
        this.typeEv = type;
        this.idprop = propid;
    }

    public Evenement() {
    }
   
    public Evenement(int idEv, String nomEv, String descEv, Date debutEv, Date finEv, int nbPlace, String typeEv) {
        this.idEv = idEv;
        this.nomEv = nomEv;
        this.descEv = descEv;
        this.debutEv = debutEv;
        this.finEv = finEv;
        this.nbPlace = nbPlace;
        this.typeEv = typeEv;
       

    }

	public Evenement(String nomEv, String descEv, Date debutEv, Date finEv, int nbPlace, int nbParticipant) {
		this.nomEv = nomEv;
		this.descEv = descEv;
		this.debutEv = debutEv;
		this.finEv = finEv;
		this.nbPlace = nbPlace;
		this.nbParticipant = nbParticipant;
		//this.idEv=idEv;
	}
    
    

    public Evenement(String nomEv, String descEv, int nbPlaces, String type, int propid) {
        this.nomEv = nomEv;
        this.descEv = descEv;
        this.nbPlace = nbPlaces;
        this.typeEv = type;
        this.idprop = propid;
    }

    public Evenement(String nomEv, String descEv, Date debutEv, Date finEv, int nbPlace, String typeEv) {
        this.nomEv = nomEv;
        this.descEv = descEv;
        this.debutEv = debutEv;
        this.finEv = finEv;
        this.nbPlace = nbPlace;
        this.typeEv = typeEv;
        
    }

	public Evenement(int idEv, String nomEv, String descEv, Date debutEv, Date finEv, int nbPlace, String typeEv, int nbParticipant) {
		this.idEv = idEv;
		this.nomEv = nomEv;
		this.descEv = descEv;
		this.debutEv = debutEv;
		this.finEv = finEv;
		this.nbPlace = nbPlace;
		this.typeEv = typeEv;
		this.nbParticipant = nbParticipant;
	}

	

	public Evenement(int idEv, String nomEv, String descEv, Date debutEv, Date finEv, int nbPlace, String typeEv, int idprop, String etat, int nbParticipant) {
		this.idEv = idEv;
		this.nomEv = nomEv;
		this.descEv = descEv;
		this.debutEv = debutEv;
		this.finEv = finEv;
		this.nbPlace = nbPlace;
		this.typeEv = typeEv;
		this.idprop = idprop;
		this.etat = etat;
		this.nbParticipant = nbParticipant;
	}

	public Evenement(String nomEv, String descEv, Date debutEv, Date finEv, int nbPlace, String typeEv, int idprop, String etat, int nbParticipant) {
		this.nomEv = nomEv;
		this.descEv = descEv;
		this.debutEv = debutEv;
		this.finEv = finEv;
		this.nbPlace = nbPlace;
		this.typeEv = typeEv;
		this.idprop = idprop;
		this.etat = etat;
		this.nbParticipant = nbParticipant;
	}

    public int getIdEv() {
        return idEv;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public String getNomEv() {
        return nomEv;
    }

    public void setNomEv(String nomEv) {
        this.nomEv = nomEv;
    }

    public String getDescEv() {
        return descEv;
    }

    public void setDescEv(String descEv) {
        this.descEv = descEv;
    }

    public Date getDebutEv() {
        return debutEv;
    }

    public void setDebutEv(Date debutEv) {
        this.debutEv = debutEv;
    }

    public Date getFinEv() {
        return finEv;
    }

    public void setFinEv(Date finEv) {
        this.finEv = finEv;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public int getProp() {
        return idprop;
    }

    public void setProp(int idprop) {
        this.idprop = idprop;
    }

    public String getTypeEv() {
        return typeEv;
    }

    public void setTypeEv(String typeEv) {
        this.typeEv = typeEv;
    }

    public int getIdprop() {
        return idprop;
    }

    public void setIdprop(int idprop) {
        this.idprop = idprop;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNbParticipant() {
        return nbParticipant;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
    }

	@Override
	public String toString() {
		return "Evenement{" + "idEv=" + idEv + ", nomEv=" + nomEv + ", descEv=" + descEv + ", debutEv=" + debutEv + ", finEv=" + finEv + ", nbPlace=" + nbPlace + ", typeEv=" + typeEv + ", idprop=" + idprop + ", etat=" + etat + ", nbParticipant=" + nbParticipant + '}';
	}
    

}
