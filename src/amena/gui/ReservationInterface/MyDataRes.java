package amena.gui.ReservationInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author klair
 */
    public class MyDataRes {
        private  String date_deb;
        private  String date_fin;
        private  String etat ;
        private int idtrans,id,idVeh ; 
    
     
        

        public MyDataRes(int id,String date_deb, String date_fin, String etat,int idtrans,int idVeh ) {
            this.id=id ; 
            this.date_deb = date_deb;
            this.date_fin = date_fin;
            this.etat = etat;
            this.idtrans = idtrans;
            this.idVeh=idVeh ; 
        }

    public int getIdVeh() {
        return idVeh;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   

    public void setIdtrans(int idtrans) {
        this.idtrans = idtrans;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public int getIdtrans() {
        return idtrans;
    }

    public int getId() {
        return id;
    }

      
    }
