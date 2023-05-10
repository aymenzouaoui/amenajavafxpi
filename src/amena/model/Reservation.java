/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.model;
import java.sql.Date;


/**
 *
 * @author klair
 */
public class Reservation {
    
    private int idRes ; 
    private int idVeh ; 
    private int idTrans ; 
    private Date date_deb ; 
    private Date date_fin ; 
    private float somme ;
    private String etat ;  
    public Reservation(int idVeh, int idTrans, Date date_deb, Date date_fin, float somme,String etat) {
        this.idVeh = idVeh;
        this.idTrans = idTrans;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.somme = somme;
        this.etat  =etat ; 
    }
 public Reservation(int idRes,int idVeh, int idTrans, Date date_deb, Date date_fin, float somme,String etat) {
        this.idRes = idRes ;    
        this.idVeh = idVeh;
        this.idTrans = idTrans;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.somme = somme;
        this.etat  =etat ; 

    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public Reservation() {
    }

    public int getIdRes() {
        return idRes;
    }

    public int getIdVeh() {
        return idVeh;
    }

    public int getIdTrans() {
        return idTrans;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public float getSomme() {
        return somme;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public void setIdVeh(int idVeh) {
        this.idVeh = idVeh;
    }

    public void setIdTrans(int idTrans) {
        this.idTrans = idTrans;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setSomme(float somme) {
        this.somme = somme;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idRes=" + idRes + ", idVeh=" + idVeh + ", idTrans=" + idTrans + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", somme=" + somme + "\n}";
    }
    
    
    
}
