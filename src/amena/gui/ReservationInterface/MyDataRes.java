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
        private  Image image ;
        private int idtrans,id,idVeh ; 
    
        public Image getImage() {
        return image;
    }
        

        public MyDataRes(int id,String date_deb, String date_fin, Image image,int idtrans,int idVeh ) {
            this.id=id ; 
            this.date_deb = date_deb;
             this.date_fin = date_fin;
            this.image = image;
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

    public void setImage(Image image) {
        this.image = image;
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
