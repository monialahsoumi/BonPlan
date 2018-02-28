/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

/**
 *
 * @author Monia
 */


public class Visiteur {
    private int id ;
     private String pseudo;
    private String email;
    private String password;
     private String nom ; 
     private String prenom ;
     private String role  ;
     private String pdp; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     
     public Visiteur() {
    }

	public Visiteur(int id, String pseudo, String email, String password, String nom, String prenom, String pdp) {
		this.id = id;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.pdp = pdp;
	}

    public Visiteur(String pseudo, String email, String password, String nom, String prenom, String role, String pdp) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.pdp = pdp;
    }

    public Visiteur(String pseudo, String email, String password, String nom, String prenom, String pdp) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.pdp = pdp;
    }

    public Visiteur(String pseudo, String email, String nom, String prenom) {
        this.pseudo = pseudo;
        this.email= email;
        this.nom = nom;
        this.prenom = prenom;
    }

    
    
  //getter and setter
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPdp() {
        return pdp;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }
  
     
    


    
   
}
