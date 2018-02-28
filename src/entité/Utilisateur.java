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
public class Utilisateur {
	private String password;
    public static String pseudo;
	private boolean status_login;
	private String role;

	public Utilisateur() {
	}

	
	
	public Utilisateur(String pseudo,String password, boolean status_login, String role) {
		this.pseudo=pseudo;
		this.password = password;
		this.status_login = status_login;
		this.role = role;
	}
    

	
	


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getPseudo() {
		return pseudo;
	}

	public Utilisateur(String password, String pseudo) {
		this.password = password;
		this.pseudo = pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public Utilisateur( String pseudo) {
   this.pseudo = pseudo;}

	public boolean getStatus_login() {
		return status_login;
	}

	public void setStatus_login(boolean status_login) {
		this.status_login = status_login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
