/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.services;

import digideramers.amena.models.Gifts;
import digidreamers.amena.utils.MyConnection;
import digidreamers.amena.interfaces.InterfaceCRUD;
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
public class GiftsCRUD implements InterfaceCRUD<Gifts> {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouter(Gifts g) {
        try {
            String req = "INSERT INTO `gifts` (`name`, `description`, `value`,`idC`, `photo`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);

            ps.setString(1, g.getName());
            ps.setString(2, g.getDescription());
            ps.setString(3, g.getValue());
            ps.setInt(4, g.getIdC());
            ps.setString(5, g.getPhoto());
            ps.executeUpdate();
            System.out.println("gift ajouté");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public GiftsCRUD() {
    }

    @Override
    public void modifier(Gifts g) {
        try {
            String req = "UPDATE `gifts` SET `name` = '" + g.getName() + "', `description` = '" + g.getDescription() + "', `value` = '" + g.getValue() + "', `idC`= '" + g.getIdC() + "', `photo` = '" + g.getPhoto() + "' where id = " + g.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("gift updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `gifts`where id= " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("gift deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Gifts> afficher() {
        List<Gifts> list = new ArrayList<>();
        try {
            String req = "Select * from gifts";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Gifts g = new Gifts();
                g.setId(RS.getInt(1));
                g.setName(RS.getString(2));
                g.setDescription(RS.getString(3));
                g.setValue(RS.getString(4));
                g.setIdC(RS.getInt(5));
                g.setPhoto(RS.getString(6));

                list.add(g);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Gifts getByID(int id) {
        Gifts g = new Gifts();
        try {
            String req = "Select * from gifts where id = " + id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                g.setId(RS.getInt(1));
                g.setName(RS.getString(2));
                g.setDescription(RS.getString(3));
                g.setValue(RS.getString(4));
                g.setIdC(RS.getInt(5));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return g;

    }

    public List<Gifts> filterByComp(int i) {
        List<Gifts> list = new ArrayList<>();

        try {
            String req = "Select * from gifts where idC=" + i;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Gifts g = new Gifts();
                g.setId(RS.getInt(1));
                g.setName(RS.getString(2));
                g.setDescription(RS.getString(3));
                g.setValue(RS.getString(4));
                g.setIdC(RS.getInt(5));

                list.add(g);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }

}
