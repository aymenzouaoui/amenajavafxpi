/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.services;

import amena.model.User;
import amena.services.UserService;
import amena.utils.MyConnectionn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import test.workshop.model.Colis;
import test.workshop.model.ColisReserver;

/**
 *
 * @author hp
 */
public class ColisReserverCrud {
    Statement ste;
Connection conn = MyConnectionn .getInstance().getConn();


    public void ajouter(ColisReserver r) {
        try {
            PreparedStatement stmt = conn.prepareStatement ( "INSERT INTO `colis_rec`(id_c_id,id_u_id) VALUES  (?,?)");
            stmt.setInt(1, r.getColis().getId());
            stmt.setInt(2, r.getUser().getId());
            stmt.executeUpdate();
            System.out.println("bbbbbbb!!!");
        } catch (SQLException ex) {
            System.out.println("nnnnnns");
        }
    }
public void supprimer(int id)  {
    try {
    PreparedStatement statement1 = conn.prepareStatement("DELETE FROM `colis_rec` WHERE id = ?");
    statement1.setInt(1, id);
    statement1.executeUpdate();
    String sql = "DELETE FROM colisr WHERE id= ?";
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    System.out.println("Colis reserver deleted !");
}catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}



}
