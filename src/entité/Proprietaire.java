/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ali
 */
public class Proprietaire {
	private int id;
    private String pseudo;
    private String email;
    private String password;
    private String photoProfil;
    private String nomPlan;
    private String telephone;
    private String description;
    private String categorie;
    private String role;
	private double longiturde;
	private double latitude;
	private String adresse;
	private String ville;
	private int codepostal;
	private boolean validité;
	
	
    
    //constructeur :
	
    public Proprietaire() {
    }
    
    public Proprietaire( String pseudo, String email, String password, String photoProfil, String nomPlan, String adresse, String telephone, String description, String categorie, String role,boolean validité) {
        
		this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.photoProfil = photoProfil;
        this.nomPlan = nomPlan;
        this.telephone = telephone;
        this.description = description;
        this.categorie = categorie;
        this.role = role;
		this.validité=validité;
    }

	public Proprietaire(String pseudo, String email, String password, String photoProfil, String nomPlan, String telephone, String description, String categorie, String role, double longiturde, double latitude, String adresse, String ville, int codepostal, boolean validité) {
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.photoProfil = photoProfil;
		this.nomPlan = nomPlan;
		this.telephone = telephone;
		this.description = description;
		this.categorie = categorie;
		this.role = role;
		this.longiturde = longiturde;
		this.latitude = latitude;
		this.adresse = adresse;
		this.ville = ville;
		this.codepostal = codepostal;
		this.validité = validité;
	}
	
	
	public Proprietaire(String photoProfil, String nomPlan,String pseudo) {
		this.photoProfil = photoProfil;
		this.nomPlan = nomPlan;
		this.pseudo=pseudo;
	}

	public Proprietaire(String nomPlan) {
		this.nomPlan = nomPlan;
	}

	public Proprietaire(String pseudo , String email, String password, String photoProfil, String nomPlan, String telephone, String description, String categorie, String role) {
		this.pseudo = pseudo ;
		this.email = email;
		this.password = password;
		this.photoProfil = photoProfil;
		this.nomPlan = nomPlan;
		this.telephone = telephone;
		this.description = description;
		this.categorie = categorie;
		this.role = role;
		
	}

	public Proprietaire(String pseudo, String categorie, String adresse, String telephone) {
		this.pseudo = pseudo;
		this.telephone = telephone;
		this.categorie = categorie;
		this.adresse = adresse;
	}

	public Proprietaire(String pseudo, String email, String nomPlan, String telephone, String adresse, String categorie) {
		this.pseudo = pseudo;
		this.email = email;
		this.nomPlan = nomPlan;
		this.telephone = telephone;
		this.categorie = categorie;
		this.adresse = adresse;
	}
	
	public Proprietaire(String email, String photoProfil, String nomPlan, String adresse, String telephone, String description, String categorie) {
		this.email = email;
		this.photoProfil = photoProfil;
		this.nomPlan = nomPlan;
		this.telephone = telephone;
		this.description = description;
		this.categorie = categorie;
		this.adresse = adresse;
	}
	
	public Proprietaire(int id, String pseudo, String email, String password, String photoProfil, String nomPlan, String telephone, String description, String categorie, String role) {
        this.id=id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.photoProfil = photoProfil;
        this.nomPlan = nomPlan;
        this.telephone = telephone;
        this.description = description;
        this.categorie = categorie;
        this.role = role;

    }
	
	
	
		public Proprietaire(int id, String email, String photoProfil, String nomPlan, String adresse, String telephone, String description, String categorie) {
        this.id=id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.photoProfil = photoProfil;
        this.nomPlan = nomPlan;
        this.telephone = telephone;
        this.description = description;
        this.categorie = categorie;
        this.role = role;

    }
	
    //get & set :

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

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

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public String getNomPlan() {
        return nomPlan;
    }

    public void setNomPlan(String nomPlan) {
        this.nomPlan = nomPlan;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	
	public boolean getValidité() {
		return validité;
	}

	public void setValidité(boolean validité) {
		this.validité = validité;
	}

	public ImageView getImage(){
		ImageView i = new ImageView("file:///"+photoProfil);
		i.setFitHeight(50);
		i.setFitWidth(50);
		return i;
	}
	
	public double getLongiturde() {
		return longiturde;
	}

	public void setLongiturde(double longiturde) {
		this.longiturde = longiturde;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(int codepostal) {
		this.codepostal = codepostal;
	}
	
  // Méthode ToString :
    @Override
    public String toString() {
        return "Proprietaire{" + "pseudo=" + pseudo + ", email=" + email + ", password=" + password + ", photoProfil=" + photoProfil + ", nomPlan=" + nomPlan +", telephone=" + telephone + ", description=" + description + ", categorie=" + categorie + ", role=" + role + '}';
    }

    @Override
    public int hashCode() {
        int hash = 0 ;
        hash = 125 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proprietaire other = (Proprietaire) obj;
        return Objects.equals(this.password, other.password);
    }
	
	

    

}
