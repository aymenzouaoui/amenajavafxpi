/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.services;

import amena.model.User;
import digidreamers.amena.interfaces.InterfaceCRUD;
import digidreamers.amena.utils.MyConnection;
import digideramers.amena.models.Competition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahlem
 */
public class CompetitionCRUD implements InterfaceCRUD<Competition> {
    
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    public CompetitionCRUD() {
    }
    
    @Override
    public void ajouter(Competition c) {
        try {
            String req = "INSERT INTO `competition` (`title`, `date_deb`, `date_fin`, `type` ,`nbp`,`id_uc`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            
            ps.setString(1, c.getTitle());
            ps.setDate(2, c.getDate_deb());
            ps.setDate(3, c.getDate_fin());
            ps.setInt(4, c.getType());
            ps.setInt(5, c.getNbp());
            ps.setObject(6, c.getUser().getId());
            
            ps.executeUpdate();
            System.out.println("comp ajoutée!!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifier(Competition c) {
        
        try {
            String req = "UPDATE `competition` SET `title` = '" + c.getTitle() + "', `date_deb` = '" + c.getDate_deb() + "', `date_fin` = '" + c.getDate_fin() + "', `type` = '" + c.getType() + "', `nbp` = '" + c.getNbp() + "' WHERE id = " + c.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Comp updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `competition` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Competition deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Competition> afficher() {
        List<Competition> list = new ArrayList<>();
        try {
            String req = "Select * from competition";
            Statement st = conn.createStatement();
            
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Competition c = new Competition();
                c.setId(RS.getInt(1));
                c.setTitle(RS.getString(2));
                c.setDate_deb(RS.getDate(3));
                c.setDate_fin(RS.getDate(4));
                c.setType(RS.getInt(5));
                c.setNbp(RS.getInt(6));
                list.add(c);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public Competition getByID(int id) {
        Competition c = new Competition();
        try {
            String req = "Select * from competition where id = " + id;
            Statement st = conn.createStatement();
            
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                c.setId(RS.getInt(1));
                c.setTitle(RS.getString(2));
                c.setDate_deb(RS.getDate(3));
                c.setDate_fin(RS.getDate(4));
                c.setType(RS.getInt(5));
                c.setNbp(RS.getInt(6));
                User user = new User();
                user.setId(RS.getInt(7));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
    public List<Competition> filterByType(int i) {
        List<Competition> list = new ArrayList<>();
        
        try {
            String req = "Select * from competition where type=" + i;
            Statement st = conn.createStatement();
            
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Competition c = new Competition();
                c.setId(RS.getInt(1));
                c.setTitle(RS.getString(2));
                c.setDate_deb(RS.getDate(3));
                c.setDate_fin(RS.getDate(4));
                c.setType(RS.getInt(5));
                c.setNbp(RS.getInt(6));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
        
    }
    
    public List<String> titreComp() {
        List<String> list = new ArrayList<>();
        try {
            String req = "Select Title from competition";
            Statement st = conn.createStatement();
            
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                
                list.add(RS.getString(1));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public int convertTitleToId(String title) {
        try {
            String req = "Select * from competition where title= '" + title + "'";
            Statement st = conn.createStatement();
            
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                
                return (RS.getInt(1));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
}
