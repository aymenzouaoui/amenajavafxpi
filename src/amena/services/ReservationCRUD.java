/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.services;

import static amena.gui.ProfilController.semail;
import amena.interfaces.InterfaceCRUD ;
import amena.model.Reservation;
import amena.model.User;
import amena.model.Vehicule;
import amena.utils.MyConnectionn;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author klair
 * @param <T>
 */
public class ReservationCRUD implements InterfaceCRUD<Reservation> {

    Statement ste;
    Connection conn = MyConnectionn.getInstance().getConn();
    
    public  List<Reservation> afficher_cl() throws SQLException {
        UserService u = new UserService();
        User p = u.getUserByEmai(semail);
        System.out.println(p);
       List<Reservation>list = new ArrayList<>();
        try {
            String req = "Select * from reservation where id_trans_id = '"+p.getId() +"'";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
            Reservation r = new Reservation();
             r.setIdRes(RS.getInt(1));
             r.setIdVeh(RS.getInt(6));
             r.setIdTrans(RS.getInt(7));
             r.setDate_deb(RS.getDate(2));
             r.setDate_fin(RS.getDate(3));
             r.setSomme(RS.getFloat(4));
              r.setEtat(RS.getString(5));

             list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
     public  List<Reservation> afficher_ByVeh(int veh) throws SQLException {
        UserService u = new UserService();
        User p = u.getUserByEmai(semail);
        System.out.println(p);
       List<Reservation>list = new ArrayList<>();
        try {
            String req = "Select * from reservation where idV = '" +veh +"'";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
            Reservation r = new Reservation();
             r.setIdRes(RS.getInt(1));
             r.setIdVeh(RS.getInt(6));
             r.setIdTrans(RS.getInt(7));
             r.setDate_deb(RS.getDate(2));
             r.setDate_fin(RS.getDate(3));
             r.setSomme(RS.getFloat(4));
              r.setEtat(RS.getString(5));

             list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    
    
    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO `reservation` (`idV`, `id_trans_id`, `date_deb`, `date_fin`, `somme` , `etat`) VALUES (?,?,?,?,?,?)";
        //    String req2 = "UPDATE `vehicule` SET `etat` = '1'  WHERE id = " + r.getIdVeh();
            
          //  PreparedStatement ps2=conn.prepareStatement(req2);
            // ps2.executeUpdate();

            PreparedStatement ps=conn.prepareStatement(req);
             System.out.println(r.getIdVeh());
            ps.setInt(1, r.getIdVeh());
            ps.setInt(2, r.getIdTrans());
            ps.setDate(3, r.getDate_deb());
            ps.setDate(4, r.getDate_fin());
            ps.setFloat(5, r.getSomme());
            ps.setString(6, r.getEtat());

             ps.executeUpdate();
            System.out.println("Reservation ajouté!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();                    
        }   
 }

    public ReservationCRUD() {
    }
    
    public void modifier(Reservation r) {   
        try {
            String req = "UPDATE `reservation` SET `idV` = '" + r.getIdVeh() + "', `Date_Deb` = '" + r.getDate_deb()+ "', `date_fin` = '" + r.getDate_fin() + "', `somme` = '" + r.getSomme()+"', `etat` = '" + r.getEtat()+ "' WHERE idRes = " + r.getIdRes();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reservation` WHERE idRes = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    
    @Override
    public  List<Reservation> afficher() {
       List<Reservation>list = new ArrayList<>();
        try {
            String req = "Select * from reservation";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
            Reservation r = new Reservation();
            r.setIdRes(RS.getInt(1));
             r.setIdVeh(RS.getInt(6));
             r.setIdTrans(RS.getInt(7));
             r.setDate_deb(RS.getDate(2));
             r.setDate_fin(RS.getDate(3));
             r.setSomme(RS.getFloat(4));
              r.setEtat(RS.getString(5));

             list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
        
    @Override
    public Reservation getByID(int id)
    {
        Reservation r = new Reservation();
        try {
        String req = "Select * from reservation where idRes = "+id;
        Statement st = conn.createStatement();
        
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
             r.setIdRes(RS.getInt(1));
             r.setIdVeh(RS.getInt(6));
             r.setIdTrans(RS.getInt(7));
             r.setDate_deb(RS.getDate(2));
             r.setDate_fin(RS.getDate(3));
             r.setSomme(RS.getFloat(4));
              r.setEtat(RS.getString(5));

        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r ; 
        
    }
    
    public List<Reservation> filterByDate_fin(Date d)
    {
        List<Reservation>list = new ArrayList<>();

        try {
        String req = "Select * from reservation where date_fin = '"+d+"'";
        Statement st = conn.createStatement();
        ResultSet RS= st.executeQuery(req);
        while(RS.next()){
        Reservation r = new Reservation();
        r.setIdRes(RS.getInt(1));
             r.setIdVeh(RS.getInt(6));
             r.setIdTrans(RS.getInt(7));
             r.setDate_deb(RS.getDate(2));
             r.setDate_fin(RS.getDate(3));
             r.setSomme(RS.getFloat(4));
              r.setEtat(RS.getString(5));
        list.add(r);
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list ;         
    }
   public void modifier_etat(Reservation v,String et) {
  
        try {
            String req = "UPDATE `reservation` SET `etat` = '" + et + "' WHERE idRes = " + v.getIdRes();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
