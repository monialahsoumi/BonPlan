/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

/**
 *
 * @author gaalo
 */
public class Reclamer {
	 private int idUser;
    private int idPlan;
    private String type;
     private String nivRec ; 
     private String Description ;
	 private String etat;
	 private int idRec;
	
	public Reclamer(int idRec ,int idUser, int idPlan,String etat, String type, String nivRec, String Description) {
		this.idRec=idRec;
		this.idUser = idUser;
		this.idPlan = idPlan;
		this.etat = etat;
		this.type = type;
		this.nivRec = nivRec;
		this.Description = Description;
	}
		public Reclamer(int idUser, int idPlan,String etat, String type, String nivRec, String Description) {
		this.idUser = idUser;
		this.idPlan = idPlan;
		this.etat = etat;
		this.type = type;
		this.nivRec = nivRec;
		this.Description = Description;
	}
			public Reclamer(String etat) {
		
		this.etat = etat;

	}

	public Reclamer(String type,String Description,String nivRec, String etat) {
		this.type = type;
		this.nivRec = nivRec;
		this.Description = Description;
		this.etat = etat;
	}
			
			
			

	public Reclamer(int idUser, String type, String nivRec, String Description) {
		this.idUser = idUser;
		this.type = type;
		this.nivRec = nivRec;
		this.Description = Description;
	}

	
		
		

	

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNivRec() {
		return nivRec;
	}

	public void setNivRec(String nivRec) {
		this.nivRec = nivRec;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getIdRec() {
		return idRec;
	}

	public void setIdRec(int idRec) {
		this.idRec = idRec;
	}

	
	 
	
}
