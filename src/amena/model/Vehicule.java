/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.model;

/**
 *
 * @author klair
 */
public class Vehicule {
    private int id ; 
    private String type  ; 
    private String immat ;
    private boolean etat ;
    private int chevaux ; 
    private String marque ;
    private String modele ;

   

 

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getModele() {
        return modele;
    }
    private String kilometrage ;
    private String couleur ; 
    private float prix ; 
private String img ; 
     public Vehicule()
     {}
    
    public Vehicule(int id,String type,String immat, boolean etat,String kilometrage, int chevaux, String marque,String modele, String couleur, float prix,String img) {
        this.modele = modele ; 
        this.id = id ; 
        this.type = type ; 
        this.immat = immat;
        this.etat = etat;
        this.chevaux = chevaux;
        this.marque = marque;
        this.kilometrage = kilometrage;
        this.couleur = couleur;
        this.prix = prix;
        this.img = img ;
    }
    public Vehicule(String type,String immat, boolean etat,String kilometrage,int chevaux, String marque,String modele, String couleur, float prix,String img) {
        this.img  = img  ; 
        this.type = type ; 
        this.immat = immat;
        this.etat = etat;
        this.kilometrage = kilometrage;
        this.chevaux = chevaux;
        this.marque = marque;
        this.couleur = couleur;
        this.prix = prix;
                this.modele = modele ; 

    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicule other = (Vehicule) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getImmat() {
        return immat;
    }

    public boolean isEtat() {
        return etat;
    }

    public int getChevaux() {
        return chevaux;
    }

    public String getMarque() {
        return marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public float getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public void setChevaux(int chevaux) {
        this.chevaux = chevaux;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String nbrEspaceType(String x)
    {
        String s= "" ; 
        int n = x.length() ; 
        for(int i=0;i<10-n;i++) 
            s=s+"a" ; 
       return s ; 
    }
    
     public String nbrEspaceImmat(String x)
    {
        String s= "" ; 
        int n = x.length() ; 
        for(int i =0;i<15-n;i++) 
           s=s+"b" ; 
       return s ; 
    }
    
    @Override
    public String toString() {
        return immat ;//+ nbrEspaceImmat(immat) + etat ; // + "   " + chevaux + "   " + marque + "   " + kilometrage + "   " + couleur + "   " + prix ;
    }
    
    
    
}
