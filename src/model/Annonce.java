/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;




/**
 *
 * @author Nour Saidi
 */
import amena.model.User;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import test.workshop.model.Colis;
public class Annonce {
private int id_annonce,prix;
private String type,ville_dep,ville_arr, date_dep,date_arr,description;

    public Annonce(int id_annonce, int prix, String type, String ville_dep, String ville_arr, String date_dep, String date_arr, String description, User user) {
        this.id_annonce = id_annonce;
        this.prix = prix;
        this.type = type;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.description = description;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Annonce(int prix, String type, String ville_dep, String ville_arr, String date_dep, String date_arr, String description, User user) {
        this.prix = prix;
        this.type = type;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.description = description;
        this.user = user;
    }
private User user;
private Colis colis;

    public Colis getColis() {
        return colis;
    }

    public void setColis(Colis colis) {
        this.colis = colis;
    }

    public Annonce(int prix, String type, String ville_dep, String ville_arr, String date_dep, String date_arr, String description, User user, Colis colis) {
        this.prix = prix;
        this.type = type;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.description = description;
        this.user = user;
        this.colis = colis;
    }

    public Annonce(int id_annonce, int prix, String type, String ville_dep, String ville_arr, String date_dep, String date_arr, String description, User user, Colis colis) {
        this.id_annonce = id_annonce;
        this.prix = prix;
        this.type = type;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.description = description;
        this.user = user;
        this.colis = colis;
    }
  

    
    public Annonce() {
    }

    public Annonce(int id_annonce, int prix, String type, String ville_dep, String ville_arr, String date_dep, String date_arr, String description) {
        this.id_annonce = id_annonce;
        this.prix = prix;
        this.type = type;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.description = description;
    }

    public Annonce(String ville_dep, String ville_arr) {
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
    }

    public Annonce(int id_annonce, String ville_dep, String ville_arr, String date_dep, String date_arr, String description) {
        this.id_annonce = id_annonce;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.description = description;
    }

    public Annonce(int id_annonce, int prix, String ville_dep, String ville_arr, String date_dep, String date_arr, String description) {
        this.id_annonce = id_annonce;
        this.prix = prix;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.description = description;
    }

    public Annonce(int prix) {
        this.prix = prix;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVille_dep() {
        return ville_dep;
    }

    public void setVille_dep(String ville_dep) {
        this.ville_dep = ville_dep;
    }

    public String getVille_arr() {
        return ville_arr;
    }

    public void setVille_arr(String ville_arr) {
        this.ville_arr = ville_arr;
    }

    public String getDate_dep() {
        return date_dep;
    }

    public void setDate_dep(String date_dep) {
        this.date_dep = date_dep;
    }

    public String getDate_arr() {
        return date_arr;
    }

    public void setDate_arr(String date_arr) {
        this.date_arr = date_arr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Annonce(int id_annonce, String ville_dep, String ville_arr) {
        this.id_annonce = id_annonce;
        this.ville_dep = ville_dep;
        this.ville_arr = ville_arr;
    }

    public Annonce(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id_annonce=" + id_annonce + ", prix=" + prix + ", type=" + type + ", ville_dep=" + ville_dep + ", ville_arr=" + ville_arr + ", date_dep=" + date_dep + ", date_arr=" + date_arr + ", description=" + description + ", user=" + user + ", colis=" + colis + '}';
    }

    

   
   

}
