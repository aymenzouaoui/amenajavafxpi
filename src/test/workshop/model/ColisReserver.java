/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.model;

import amena.model.User;
import test.workshop.model.Colis;

/**
 *
 * @author hp
 */
public class ColisReserver {
    private int id ;
    private User user ;
    private Colis colis;

    public ColisReserver(int id, User user, Colis colis) {
        this.id = id;
        this.user = user;
        this.colis = colis;
    }

    public ColisReserver() {
    }

    public ColisReserver(User user, Colis colis) {
        this.user = user;
        this.colis = colis;
    }

    public int getId() {
        return id;
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

    public Colis getColis() {
        return colis;
    }

    public void setColis(Colis colis) {
        this.colis = colis;
    }

    @Override
    public String toString() {
        return "ColisReserver{" + "id=" + id + ", user=" + user + ", colis=" + colis + '}';
    }
    
    
    
    
}
