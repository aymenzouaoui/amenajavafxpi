/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui.LocationInterface;

import javafx.scene.image.Image;

/**
 *
 * @author klair
 */
    class MyData {
        private  String title;
        private  String description;
        private  Image image,etat;
        private int id ; 
    public Image getEtat() {
        return etat;
    }
        

        public MyData(int id,String title, String description, Image image,Image etat) {
            this.id=id ; 
            this.title = title;
            this.description = description;
            this.image = image;
            this.etat = etat;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

    public int getId() {
        return id;
    }

        public Image getImage() {
            return image;
        }
    }
