/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import amena.model.User;
import test.workshop.model.Colis;

/**
 *
 * @author Nour Saidi
 */
public class Reaction {

    private int id;
private User user;
private Colis coli ;
private Annonce annonce ;


    public int getId() {
        return id;
    }

    public Reaction() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Colis getColi() {
        return coli;
    }

    public void setColi(Colis coli) {
        this.coli = coli;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Reaction(User user, Colis coli, Annonce annonce) {
        this.user = user;
        this.coli = coli;
        this.annonce = annonce;
    }

    @Override
    public String toString() {
        return "Reaction{" + "id=" + id + ", user=" + user + ", coli=" + coli + ", annonce=" + annonce + '}';
    }



    
    
    
      
}
