/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.model;

import amena.model.User;

/**
 *
 * @author aymen
 */
public class ValidationT {

    private String imageA;
    private String imageb;
    private int id;
    private User user;
    private boolean valid;

    public ValidationT(String imageA, String imageb, User user, boolean valid) {
        this.imageA = imageA;
        this.imageb = imageb;
        this.user = user;
        this.valid = valid;
    }

    public ValidationT(String imageA, String imageb, int id, User user, boolean valid) {
        this.imageA = imageA;
        this.imageb = imageb;
        this.id = id;
        this.user = user;
        this.valid = valid;
    }

    public ValidationT() {
    }

    @Override
    public String toString() {
        return "ValidationT{" + "imageA=" + imageA + ", imageb=" + imageb + ", id=" + id + ", user=" + user + ", valid=" + valid + '}';
    }

    public String getImageA() {
        return imageA;
    }

    public void setImageA(String imageA) {
        this.imageA = imageA;
    }

    public String getImageb() {
        return imageb;
    }

    public void setImageb(String imageb) {
        this.imageb = imageb;
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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
