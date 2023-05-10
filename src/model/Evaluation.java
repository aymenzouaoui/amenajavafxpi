/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import amena.model.User;

/**
 *
 * @author Nour Saidi
 */
public class Evaluation {
    private int id;
    private int idClient;
    private int idTransporteur;
    private int note;
    private User user;

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", idClient=" + idClient + ", idTransporteur=" + idTransporteur + ", note=" + note + ", user=" + user + '}';
    }

    public Evaluation(int idClient, int idTransporteur, int note, User user) {
        this.idClient = idClient;
        this.idTransporteur = idTransporteur;
        this.note = note;
        this.user = user;
    }

    public Evaluation(int id, int idClient, int idTransporteur, int note, User user) {
        this.id = id;
        this.idClient = idClient;
        this.idTransporteur = idTransporteur;
        this.note = note;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evaluation(int id, int idClient, int idTransporteur, int note) {
        this.id = id;
        this.idClient = idClient;
        this.idTransporteur = idTransporteur;
        this.note = note;
    }

    public Evaluation(int idClient, int idTransporteur, int note) {
        this.idClient = idClient;
        this.idTransporteur = idTransporteur;
        this.note = note;
    }

    public Evaluation() {
    }

    public Evaluation(int idClient, int idTransporteur) {
        this.idClient = idClient;
        this.idTransporteur = idTransporteur;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdTransporteur() {
        return idTransporteur;
    }

    public void setIdTransporteur(int idTransporteur) {
        this.idTransporteur = idTransporteur;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    
}
