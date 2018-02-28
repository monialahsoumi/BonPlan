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
public class Commentaire {

    private int idAvis;
    private int iduserV;
    private int iduserP;
    private String commentaire;
    private Date dateComment;

    public Commentaire(int iduserV, int iduserP, String commentaire, Date dateComment) {
        this.iduserV = iduserV;
        this.iduserP = iduserP;
        this.commentaire = commentaire;
        this.dateComment = dateComment;
    }

    public Commentaire(int idAvis, String commentaire, Date dateComment) {
        this.idAvis = idAvis;
        this.commentaire = commentaire;
        this.dateComment = dateComment;
    }

    public Commentaire( String commentaire, Date dateComment,int iduserV, int iduserP) {
      
          this.commentaire = commentaire;
        this.dateComment = dateComment;
        this.iduserV = iduserV;
        this.iduserP = iduserP;
      
    }

    public Commentaire(String commentaire, int idAvis) {

        this.commentaire = commentaire;
        this.idAvis = idAvis;

    }

    public Commentaire() {
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public Commentaire(String commentaire, Date dateComment) {
        this.commentaire = commentaire;
        this.dateComment = dateComment;
    }

    public Commentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
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
