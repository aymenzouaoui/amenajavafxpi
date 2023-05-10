/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.InterfaceCRUD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Reaction;
import amena.utils.MyConnectionn;

/**
 *
 * @author Nour Saidi
 */
public class ReactionCRUD implements InterfaceCRUD<Reaction> {

    Statement ste;
    Connection conn = MyConnectionn.getInstance().getConn();

    // @Override
    public void ajouter(Reaction r) {
        try {
            String req = "INSERT INTO `reactions`( `id_a`, `id_u`,`id_c`) VALUES ('" + r.getAnnonce().getId_annonce() + "','" + r.getUser().getId() + "','" + r.getColi().getId() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reaction ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Reaction non ajouté");
        }
    }

    //////////////////////
    //  @Override
    /* public void modifier(Reaction r) {
        try {
            
           String req = "UPDATE `reactions` SET `id_a` = '"+r.getId_a()+"',`id_u`='"+r.getId_u()+"',`id_c`='"+r.getId_c()+"' WHERE reactions.id = " +r.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reaction updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    /////////////////////////////////
    //  @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reactions` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Annonce deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reaction t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reaction> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
////////////////////////
// @Override
/*  public List<Reaction> afficher() {
       List<Reaction> list = new ArrayList<>();
        try {
            String req = "Select * from reactions";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reaction r = new Reaction();
             r.setId(RS.getInt("id"));
             r.setId_a(RS.getInt("id_a"));
             r.setId_u(RS.getInt("id_u"));
             r.setId_c(RS.getInt("id_c"));
            
             
           
             list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}*/
 /*
     public List<Annonce> afficherD() {
       List<Annonce> list = new ArrayList<>();
        try {
            String req = "Select * from annonces where type='Demande'";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Annonce a = new Annonce();
           //a.setId_annonce(RS.getInt("id_annonce"));
             a.setVille_dep(RS.getString("ville_dep"));
             a.setVille_arr(RS.getString("ville_arr"));
             a.setDate_dep(RS.getString("date_dep"));
             a.setDate_arr(RS.getString("date_arr"));
             a.setPrix(RS.getInt("prix"));
             a.setDescription(RS.getString("description"));
           
             list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    /* public Annonce getByID(int id_annonce)
    {
        Annonce a = new Annonce();
        try {
        String req = "Select * from annonces where id_annonce = "+id_annonce;
        Statement st = conn.createStatement();
        
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
             a.setType (RS.getString(1));
             a.setVille_dep(RS.getString(2));
             a.setVille_arr(RS.getString(3));
             a.setDate_dep(RS.getString(4));
             a.setDate_arr(RS.getString(5));
             a.setPrix(RS.getInt(6));
             a.setDescription(RS.getString(7));
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a ; 
        
    }
     
      public List<Annonce> filterByType(String type)
    {
        List<Annonce>list = new ArrayList<>();

        try {
        String req = "Select * from annonces where type = '"+type+"'";
        Statement st = conn.createStatement();
        
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
        Annonce a = new Annonce();
        a.setId_annonce(RS.getInt(1));
        a.setType(RS.getString(2));
        a.setVille_dep(RS.getString(3));
        a.setVille_arr(RS.getString(4));
        a.setDate_dep(RS.getString(5));
        a.setDate_arr(RS.getString(6));
        a.setPrix(RS.getInt(7));
        list.add(a);
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list ; 
         
    }*/
