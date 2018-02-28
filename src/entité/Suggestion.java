/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

/**
 *
 * @author Ali
 */
public class Suggestion extends Proprietaire{
	private int id_rate;
	private Double rate;
	

	public Suggestion() {}

	public Suggestion(int id_rate ,Double rate, String pseudo, String email, String password, String photoProfil, String nomPlan, String telephone, String description, String categorie, String role, double longiturde, double latitude, String adresse, String ville, int codepostal, boolean validité) {
		super(pseudo, email, password, photoProfil, nomPlan, telephone, description, categorie, role, longiturde, latitude, adresse, ville, codepostal, validité);
		this.id_rate=id_rate;
		this.rate = rate;
	}
	
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}
