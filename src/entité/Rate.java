/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

/**
 *
 * @author firas
 */
public class Rate {

    private int idRate;
    private double rate;
    private int iduserV;
    private int iduserP;

    public Rate() {
    }

    public Rate(int iduserV, int iduserP,double rate) {
        
        this.iduserV = iduserV;
        this.iduserP = iduserP;
        this.rate = rate;
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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
