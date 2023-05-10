/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digideramers.amena.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import amena.model.User;
import java.sql.Date;

/**
 *
 * @author Ahlem
 */
public class Competition {

    private int id;
    private String title;
    private Date date_deb, date_fin;
    private int type, nbp;
    private User user ;

    public Competition(int id, String title, Date date_deb, Date date_fin, int type, int nbp, User user) {
        this.id = id;
        this.title = title;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.type = type;
        this.nbp = nbp;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Competition(String title, Date date_deb, Date date_fin, int type, int nbp, User user) {
        this.title = title;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.type = type;
        this.nbp = nbp;
        this.user = user;
    }

    public Competition() {
    }

    public Competition(int id, String title, Date date_deb, Date date_fin, int type, int nbp) {
        this.id = id;
        this.title = title;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.type = type;
        this.nbp = nbp;
    }

    public Competition(String title, Date date_deb, Date date_fin, int type, int nbp) {
        this.title = title;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.type = type;
        this.nbp = nbp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNbp() {
        return nbp;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }

    @Override
    public String toString() {
        return title;
        //"Competition{" + "id=" + id + ", title=" + title + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", type=" + type + ", nbp=" + nbp +"}\n";
    }

}
