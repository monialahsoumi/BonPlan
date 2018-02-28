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
public class Abonner {

	private int idAb;
	private int idUser;
	private int idPlan;

	public Abonner() {
	}

	public Abonner(int idAb, int idUser, int idPlan) {
		this.idAb = idAb;
		this.idUser = idUser;
		this.idPlan = idPlan;
	}

	public Abonner(int idUser, int idPlan) {
		this.idUser = idUser;
		this.idPlan = idPlan;
	}

	public int getIdAb() {
		return idAb;
	}

	public void setIdAb(int idAb) {
		this.idAb = idAb;
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

}
