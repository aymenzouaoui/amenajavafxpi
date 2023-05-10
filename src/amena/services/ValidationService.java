/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.services;

import amena.model.Message;
import amena.model.User;
import amena.model.ValidationT;
import amena.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aymen
 */
public class ValidationService {

    Connection cnx = MyConnection.getInstance().getConnection();
    Statement stm;
    User loggeduser;

    public ValidationService() throws SQLException {
        stm = cnx.createStatement();
    }

    public void ajouter(ValidationT m) {
        try {
            String query = "INSERT INTO `validation`(`imageA`, `imageB`, `idu`, `valide`) VALUES (?,?,?,?)";

            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, m.getImageA());
            ste.setString(2, m.getImageb());
            ste.setInt(3, m.getUser().getId());
            ste.setBoolean(4, false);

            ste.executeUpdate();

            System.out.println("Validation ajouter!!");
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public void supprimer(int id) {
        try {

            String query = "DELETE FROM validation id = " + id;;
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Validation suprimer!");
            st.executeUpdate(query);

            System.out.println("Validation deleted !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   public void modifier(ValidationT m) {
    try {
        String query = "UPDATE validation SET imageA=?, imageB=?, idu=?, valide=? WHERE id=?";
        PreparedStatement ste = cnx.prepareStatement(query);
        ste.setString(1, m.getImageA());
        ste.setString(2, m.getImageb());
        ste.setInt(3, m.getUser().getId());
        ste.setBoolean(4, m.isValid());
        ste.setInt(5, m.getUser().getId());
        ste.executeUpdate();
        System.out.println("Validation modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

}
