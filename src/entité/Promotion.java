/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author Meyssa
 */
public class Promotion {

    private int idPromo;
    private String description;
    private String urlPromo;
    private Date dateDebut;
    private Date dateFin;
    private int proprietaire_id;

    public Promotion() {
    }

    public Promotion(int idPromo, String description, String urlPromo, Date dateDebut, Date dateFin) {
        this.idPromo = idPromo;
        this.description = description;
        this.urlPromo = urlPromo;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Promotion(String description, Date dateDébut, Date dateFin, String urlPromo, int proprietaire_id) {
        this.description = description;
        this.urlPromo = urlPromo;
        this.dateDebut = dateDébut;
        this.dateFin = dateFin;
        this.proprietaire_id = proprietaire_id;
    }

    public Promotion(String description, String urlPromo, int proprietaire_id) {
        this.description = description;
        this.urlPromo = urlPromo;
        this.proprietaire_id = proprietaire_id;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlPromo() {
        return urlPromo;
    }

    public void setUrlPromo(String urlPromo) {
        this.urlPromo = urlPromo;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDébut) {
        this.dateDebut = dateDébut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getProprietaire_id() {
        return proprietaire_id;
    }

    public void setProprietaire_id(int proprietaire_id) {
        this.proprietaire_id = proprietaire_id;
    }

    @Override
    public String toString() {
        return "Promotion{" + "description=" + description + ", urlPromo=" + urlPromo + ", dateD\u00e9but=" + dateDebut + ", dateFin=" + dateFin + ", proprietaire_id=" + proprietaire_id + '}';
    }

    public ImageView getImage() {
        ImageView image = new ImageView( this.getUrlPromo());
//        System.out.println("http://" + this.getUrlPromo());
        image.setFitHeight(80);
        image.setFitWidth(80);
        return image;
    }

}
