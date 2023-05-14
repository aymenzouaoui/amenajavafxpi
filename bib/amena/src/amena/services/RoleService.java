/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.services;

import amena.interfaces.InterfaceCRUD;
import amena.model.Role;
import amena.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aymen
 */
public class RoleService implements InterfaceCRUD<Role> {

    Connection cnx = MyConnection.getInstance().getConnection();
    Statement stm;

    public RoleService() {
        try {
            stm = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //methode d ajouter un role 

    @Override
    public void ajouter(Role role) {

        try {
            String querry = "INSERT INTO `role`( `role`) VALUES ('" + role.getRole() + "')";
            stm.executeUpdate(querry);
            //sddq
        } catch (SQLException ex) {
            System.out.println("Personne non ajouté");
        }
    }

    
    
    //methode pour modifeir le role
    
    
    @Override
    public void modifier(Role role) {

        try {
            String querry = "UPDATE `role` SET `role`='" + role.getRole() + "' WHERE `id`=" + role.getId();
            stm.executeUpdate(querry);
            //sddq
            System.out.println("Role ajouté");
        } catch (SQLException ex) {
            System.out.println("Role non ajouté");
        }
    }

    
    //methode pour supprimer un role
    @Override
    public void supprimer(int id) {

        try {
            String querry = "DELETE FROM `role` WHERE id=" + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(querry);
            System.out.println("Role deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    @Override
    public List<Role> afficher() {
        List<Role> roles = new ArrayList<Role>();
        try {
            String query = "SELECT * FROM role";
            PreparedStatement statement = cnx.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String role = result.getString("role");
                Role r = new Role(id, role);
                roles.add(r);
                System.out.println(role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role getByID(int id) {
        Role role = null;
        try {
            String query = "SELECT * FROM role WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String roleName = result.getString("role");
                role = new Role(id, roleName);
                System.out.println("ce role est()" + role);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return role;
    }

}
