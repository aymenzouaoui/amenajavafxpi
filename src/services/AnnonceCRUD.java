/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import amena.model.User;
import interfaces.InterfaceCRUD;
import model.Annonce;
import amena.utils.MyConnectionn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.workshop.model.Colis;
import test.workshop.services.ColisCRUD;

/**
 *
 * @author Nour Saidi
 */
public class AnnonceCRUD implements InterfaceCRUD<Annonce> {

    Statement ste;
    Connection conn = MyConnectionn.getInstance().getConn();

    // @Override
    public void ajouter(Annonce a) {
        try {
            String req = "INSERT INTO annonces (type, ville_dep, ville_arr, date_dep, date_arr, prix, description, ida_U,id_colis_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, a.getType());
            ps.setString(2, a.getVille_dep());
            ps.setString(3, a.getVille_arr());
            ps.setString(4, a.getDate_dep());
            ps.setString(5, a.getDate_arr());
            ps.setDouble(6, a.getPrix());
            ps.setString(7, a.getDescription());
            ps.setInt(8, a.getUser().getId());
              ps.setInt(9, a.getColis().getId());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Annonce ajoutée!");
            } else {
                System.out.println("Erreur: Annonce non ajoutée");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur: Annonce non ajoutée");
            ex.printStackTrace();
        }
    }

    
    public void ajouter2(Annonce a) {
        try {
            String req = "INSERT INTO annonces (type, ville_dep, ville_arr, date_dep, date_arr, prix, description, ida_U,id_colis_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, a.getType());
            ps.setString(2, a.getVille_dep());
            ps.setString(3, a.getVille_arr());
            ps.setString(4, a.getDate_dep());
            ps.setString(5, a.getDate_arr());
            ps.setDouble(6, a.getPrix());
            ps.setString(7, a.getDescription());
            ps.setInt(8, a.getUser().getId());
              ps.setInt(9, 2);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Annonce ajoutée!");
            } else {
                System.out.println("Erreur: Annonce non ajoutée");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur: Annonce non ajoutée");
            ex.printStackTrace();
        }
    }
//////////////////////
//  @Override
    public void modifier(Annonce a) {
        try {
            String req = "UPDATE annonces SET ville_dep = '" + a.getVille_dep() + "',`ville_arr`='" + a.getVille_arr() + "',`date_dep`='" + a.getDate_dep() + "',`date_arr`='" + a.getDate_arr() + "',`prix`='" + a.getPrix() + "',`description`='" + a.getDescription() + "', ida_U`='" + a.getUser().getId() + "','idColis`='"+a.getColis().getId()+"' WHERE annonces.id_annonce = " + a.getId_annonce();

            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Annonce mise à jour !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /////////////////////////////////
    //  @Override
    public void supprimer(int id_annonce) {
        try {
            String req = "DELETE FROM annonces WHERE id_annonce = " + id_annonce;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Annonce deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    ////////////////////////
    // @Override
    public List<Annonce> afficher() {
        List<Annonce> list = new ArrayList<>();
        try {
            String req = "Select * from annonces";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Annonce a = new Annonce();
                a.setId_annonce(RS.getInt("id_annonce"));
                a.setType(RS.getString("Type"));
                a.setVille_dep(RS.getString("ville_dep"));
                a.setVille_arr(RS.getString("ville_arr"));
                a.setDate_dep(RS.getString("date_dep"));
                a.setDate_arr(RS.getString("date_arr"));
                a.setPrix(RS.getInt("prix"));
                a.setDescription(RS.getString("description"));
Colis colis = new Colis();
            colis.setId(RS.getInt("id_colis_id"));
            a.setColis(colis);
                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

     public List<Annonce> AfficherD() {
        List<Annonce> list = new ArrayList<>();
        try {
            String req = "Select * from annonces where type='Demande'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Annonce a = new Annonce();
                //a.setId_annonce(RS.getInt("id_annonce"));
                a.setId_annonce(RS.getInt("id_annonce"));
                a.setType(RS.getString("Type"));
                a.setVille_dep(RS.getString("ville_dep"));
                a.setVille_arr(RS.getString("ville_arr"));
                a.setDate_dep(RS.getString("date_dep"));
                a.setDate_arr(RS.getString("date_arr"));
                a.setPrix(RS.getInt("prix"));
                a.setDescription(RS.getString("description"));
                ColisCRUD cr = new ColisCRUD();
                a.setColis(cr.getByID(RS.getInt("id_colis_id")));

                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
        public List<Annonce> AfficherO() {
        List<Annonce> list = new ArrayList<>();
        try {
            String req = "Select * from annonces where type='Offre'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Annonce a = new Annonce();
                //a.setId_annonce(RS.getInt("id_annonce"));
                a.setId_annonce(RS.getInt("id_annonce"));
                a.setType(RS.getString("Type"));
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
    public List<Annonce> afficherD() {
        List<Annonce> list = new ArrayList<>();
        try {
            String req = "Select * from annonces where type='Demande'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Annonce a = new Annonce();
                //a.setId_annonce(RS.getInt("id_annonce"));
                a.setVille_dep(RS.getString("ville_dep"));
                a.setVille_arr(RS.getString("ville_arr"));
                a.setDate_dep(RS.getString("date_dep"));
                a.setDate_arr(RS.getString("date_arr"));
                a.setPrix(RS.getInt("prix"));
                a.setDescription(RS.getString("description"));
                ColisCRUD cr = new ColisCRUD();
                a.setColis(cr.getByID(RS.getInt("id_colis_id")));
                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public Annonce getByID(int id_annonce) {
        try {
            String req = "SELECT * FROM annonces WHERE id_annonce = " + id_annonce;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);

            if (rs.next()) {
                Annonce a = new Annonce();
                a.setId_annonce(rs.getByte("id_annonce"));
                a.setType(rs.getString("type"));
                a.setVille_dep(rs.getString("ville_dep"));
                a.setVille_arr(rs.getString("ville_arr"));
                a.setDate_dep(rs.getString("date_dep"));
                a.setDate_arr(rs.getString("date_arr"));
                a.setPrix(rs.getInt("prix"));
                a.setDescription(rs.getString("description"));
                 ColisCRUD cr = new ColisCRUD();
                a.setColis(cr.getByID(rs.getInt("id_colis_id")));

                return a;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
    
    public Annonce getByIdbb(int id) {
    Annonce annonce = null;
    try {
        String req = "SELECT * FROM annonces WHERE id_annonce = ?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            annonce = new Annonce();
            annonce.setId_annonce(rs.getInt("id_annonce"));
            annonce.setType(rs.getString("type"));
            annonce.setVille_dep(rs.getString("ville_dep"));
            annonce.setVille_arr(rs.getString("ville_arr"));
            annonce.setDate_dep(rs.getString("date_dep"));
            annonce.setDate_arr(rs.getString("date_arr"));
            annonce.setPrix(rs.getInt("prix"));
            annonce.setDescription(rs.getString("description"));
            User user = new User();
            user.setId(rs.getInt("ida_U"));
            annonce.setUser(user);
            ColisCRUD cr = new ColisCRUD();
                annonce.setColis(cr.getByID(rs.getInt("id_colis_id")));
        }
        rs.close();
        ps.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return annonce;
}
    
    

    public List<Annonce> filterByType(String type) {
        List<Annonce> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM annonces WHERE type = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Annonce a = new Annonce();
                a.setId_annonce(rs.getInt(1));
                a.setType(rs.getString(2));
                a.setVille_dep(rs.getString(3));
                a.setVille_arr(rs.getString(4));
                a.setDate_dep(rs.getString(5));
                a.setDate_arr(rs.getString(6));
                a.setPrix(rs.getInt(7));

                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}