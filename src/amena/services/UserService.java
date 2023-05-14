/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.services;

import static amena.gui.ProfilController.semail;
import amena.interfaces.InterfaceCRUD;
import amena.model.User;
import amena.model.ValidationT;
import amena.utils.MyConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
public class UserService implements InterfaceCRUD<User> {

    Connection cnx = MyConnection.getInstance().getConnection();
    Statement stm;
    User loggeduser;

    public UserService() throws SQLException {
        stm = cnx.createStatement();
    }

    public UserService(String nom, String prenom) {

    }

    @Override
    public void ajouter(User u) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        int stat = 0;
        if (u.isStatus()) {
            stat = 1;
        }
        try {
            // Hash the password
            String password = u.getMot_pass();
            String hashedPassword = hashPassword(password);
            u.setMot_pass(hashedPassword);

            String query = "INSERT INTO `user` "
                    + "( `nom`, `prenom`, `adress`, `cin`, `date_naissance`, `date_creation_c`, `status`, `roles`, `password`, `email`, `token`, `score`, `numtel`,`image`,`compte_ex`, `token_ex`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, u.getNom());
            statement.setString(2, u.getPrenom());
            statement.setString(3, u.getAdress());
            statement.setString(4, u.getCin());
            statement.setDate(5, u.getDate_naissance());
            statement.setDate(6, date);
            statement.setInt(7, stat);
            statement.setString(8, u.getRole());
            statement.setString(9, u.getMot_pass());
            statement.setString(10, u.getEmail());
            statement.setString(11, u.getToken());
            statement.setString(12, u.getScore());
            statement.setString(13, u.getNum());
            statement.setString(14, u.getImage());
            statement.setDate(15, u.getCompteExpirationDate());
            statement.setDate(16, u.getTokenExpirationDate());

            statement.executeUpdate();

            System.out.println("User added successfully");
        } catch (SQLException | NoSuchAlgorithmException ex) {
            System.out.println("Failed to add user: " + ex.getMessage());
            // log or rethrow the exception
        }
    }

    @Override
    public void modifier(User u) {
        try {
            // Hash the new password (if provided)
            String password = u.getMot_pass();
            if (password != null && !password.isEmpty()) {
                String hashedPassword = hashPassword(password);
                u.setMot_pass(hashedPassword);
            }

            String query = "UPDATE `user` SET "
                    + "`nom`='" + u.getNom() + "', "
                    + "`prenom`='" + u.getPrenom() + "', "
                    + "`adress`='" + u.getAdress() + "', "
                    + "`cin`='" + u.getCin() + "', "
                    + "`date_naissance`='" + u.getDate_naissance() + "', "
                    + "`status`=" + (u.isStatus() ? 1 : 0) + ", "
                    + "`roles`='" + u.getRole() + "', "
                    // + "`motPass`='" + u.getMot_pass() + "', "
                    + "`email`='" + u.getEmail() + "', "
                    + "`token`='" + u.getToken() + "', "
                    + "`score`='" + u.getScore() + "', "
                    + "`numtel`='" + u.getNum() + "', "
                    // + "`token_timer`='" + u.getTokenExpirationDate() + "', "
                    + "`image`='" + u.getImage() + "', "
                    + "`compte_ex`='" + u.getCompteExpirationDate() + "', "
                    + "`token_ex`='" + u.getCompteExpirationDate() + "' "
                    + "WHERE `id`=" + u.getId();

            Statement statement = cnx.createStatement();
            int rowsUpdated = statement.executeUpdate(query);

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("Failed to update user");
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            System.out.println("Failed to update user: " + ex.getMessage());
            // log or rethrow the exception
        }

    }

    public void modifierPassword(User u) {
        try {
            // Hash the new password (if provided)
            String password = u.getMot_pass();
            if (password != null && !password.isEmpty()) {
                String hashedPassword = hashPassword(password);
                u.setMot_pass(hashedPassword);
            }

            String query = "UPDATE `user` SET "
                    + "`nom`='" + u.getNom() + "', "
                    + "`prenom`='" + u.getPrenom() + "', "
                    + "`adress`='" + u.getAdress() + "', "
                    + "`cin`='" + u.getCin() + "', "
                    //+ "`date_naissance`='" + u.getDate_naissance() + "', "
                    + "`status`=" + (u.isStatus() ? 1 : 0) + ", "
                    + "`roles`='" + u.getRole() + "', "
                    + "`password`='" + u.getMot_pass() + "', "
                    + "`email`='" + u.getEmail() + "', "
                    + "`token`='" + u.getToken() + "', "
                    + "`score`='" + u.getScore() + "', "
                    + "`numtel`='" + u.getNum() + "', "
                    + "`compte_ex`='" + u.getCompteExpirationDate() + "', "
                    + "`token_ex`='" + u.getCompteExpirationDate() + "', "
                    + "`image`='" + u.getImage() + "' "
                    + "WHERE `id`=" + u.getId();

            Statement statement = cnx.createStatement();
            int rowsUpdated = statement.executeUpdate(query);

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("Failed to update user");
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            System.out.println("Failed to update user: " + ex.getMessage());
            // log or rethrow the exception
        }

    }
    
    /****************/
    public void modifierscore(int score) {
        try {
            // Hash the new password (if provided)
           UserService u = new UserService();
           User p = u.getUserByEmai(semail);

            String query = "UPDATE `user` SET `score`='" + score + "' WHERE `id`=" + p.getId();

            Statement statement = cnx.createStatement();
            int rowsUpdated = statement.executeUpdate(query);

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("Failed to update user");
            }
        } catch (SQLException ex) {
            System.out.println("Failed to update user: " + ex.getMessage());
            // log or rethrow the exception
        }

    }
    /***************************/

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> afficher() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from user";
            Statement stm = cnx.createStatement();

            ResultSet RS = stm.executeQuery(req);
            while (RS.next()) {
                User p = new User();
                p.setId(RS.getInt("id"));
                p.setNom(RS.getString("nom"));

                p.setPrenom(RS.getString(6));
                p.setAdress(RS.getString(7));
                p.setCin(RS.getString(8));
                p.setDate_naissance(RS.getDate(9));
                p.setDate_creation_c(RS.getDate(10));
                p.setRole(RS.getString(3));
                p.setEmail(RS.getNString(2));
                p.setTokenExpirationDate(RS.getDate(17));
                p.setImage(RS.getString(15));
                p.setStatus(RS.getBoolean(11));
                

                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("d");
        return list;
    }

    public List<User> afficher2(String email) {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user` WHERE `email`='" + email + "'";
            Statement stm = cnx.createStatement();

            ResultSet RS = stm.executeQuery(req);
            while (RS.next()) {
                User p = new User();
                
                p.setId(RS.getInt("id"));
                p.setNom(RS.getString("nom"));

                p.setPrenom(RS.getString(6));
                p.setAdress(RS.getString(7));
                p.setCin(RS.getString(5));
                p.setDate_naissance(RS.getDate(9));
                p.setDate_creation_c(RS.getDate(10));
                p.setRole(RS.getString(3));
                p.setEmail(RS.getNString(2));
                p.setTokenExpirationDate(RS.getDate(17));
                p.setImage(RS.getString(15));
                p.setStatus(RS.getBoolean(11));
                
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

   public boolean userVer(int id) {
    List<ValidationT> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM `validation` WHERE `idu`='" + id + "' AND `valide`='1'";
        Statement stm = cnx.createStatement();
        ResultSet RS = stm.executeQuery(req);
        while (RS.next()) {
            ValidationT p = new ValidationT();
            list.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        
    }
       System.out.println(list.size());
    if(list.size()!=0)
        return true ; 
    return false ; 
}


    
    public User getUserByEmai(String email) {
        User user = null;
        try {
            String query = "SELECT * FROM `user` WHERE `email`=?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdress(rs.getString("adress"));
                user.setCin(rs.getString("cin"));
                user.setDate_naissance(rs.getDate("date_naissance"));
                user.setDate_creation_c(rs.getDate("date_creation_c"));
                user.setStatus(rs.getInt("status") == 1);
                user.setRole(rs.getString("roles"));
                user.setMot_pass(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setToken(rs.getString("token"));
                user.setScore(rs.getString("score"));
                user.setNum(rs.getString("numtel"));
                user.setImage(rs.getString("image"));
                user.setCompteExpirationDate(rs.getDate("compte_ex"));
                user.setTokenExpirationDate(rs.getDate("token_ex"));
              
            }
        } catch (SQLException ex) {
            System.out.println("Failed to get user: " + ex.getMessage());
            // log or rethrow the exception
        }
        return user;
    }

    public List<User> getUsersByEmail(String email) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setAdress(rs.getString("adress"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getDate("date_naissance"));
            user.setDate_creation_c(rs.getDate("date_creation_c"));
            user.setStatus(rs.getInt("status") == 1);
            user.setRole(rs.getString("roles"));
            user.setMot_pass(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setToken(rs.getString("token"));
            user.setScore(rs.getString("score"));
            user.setNum(rs.getString("numtel"));
            user.setImage(rs.getString("image"));
            user.setCompteExpirationDate(rs.getDate("compte_ex"));
            user.setTokenExpirationDate(rs.getDate("token_ex"));
            users.add(user);
        }
        return users;
    }

    public User getUserByCIN(String cin) throws SQLException {
        String querry = "SELECT *  FROM `user` WHERE `cin`=" + cin;
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(querry);

        User user = new User();
        while (rs.next()) {

            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setAdress(rs.getString("adress"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getDate("date_naissance"));
            user.setDate_creation_c(rs.getDate("date_creation_c"));
            user.setStatus(rs.getInt("status") == 1);
            user.setRole(rs.getString("roles"));
            user.setMot_pass(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setToken(rs.getString("token"));
            user.setScore(rs.getString("score"));
            user.setNum(rs.getString("numtel"));
            user.setImage(rs.getString("image"));
            user.setCompteExpirationDate(rs.getDate("compte_ex"));
            user.setTokenExpirationDate(rs.getDate("token_ex"));

        }
        return user;
    }

    public User getByID(int id) {
        User user = null;
        try {
            String query = "SELECT * FROM `user` WHERE `id`=?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setAdress(rs.getString("adress"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getDate("date_naissance"));
            user.setDate_creation_c(rs.getDate("date_creation_c"));
            user.setStatus(rs.getInt("status") == 1);
            user.setRole(rs.getString("roles"));
            user.setMot_pass(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setToken(rs.getString("token"));
            user.setScore(rs.getString("score"));
            user.setNum(rs.getString("numtel"));
            user.setImage(rs.getString("image"));
            user.setCompteExpirationDate(rs.getDate("compte_ex"));
            user.setTokenExpirationDate(rs.getDate("token_ex"));
            }
        } catch (SQLException ex) {
            System.out.println("Failed to get user: " + ex.getMessage());
            // log or rethrow the exception
        }
        return user;
    }


    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encodedhash);
    }
    //verifier user

    public boolean FoundUser(String email, String motpass) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE email = ? AND password = ?";
        PreparedStatement pstmt = cnx.prepareStatement(query);

        pstmt.setString(1, email);
        pstmt.setString(2, motpass);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }

        return false;
    }
    /*
  public void ajouter(User user) {
    try {
        String query = "INSERT INTO `user`(`nom`, `prenom`, `adress`, `cin`, `date_naissance`, `date_creation_c`, `status`, `role`, `motPass`, `email`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, user.getNom());
        statement.setString(2, user.getPrenom());
        statement.setString(3, user.getAdress());
        statement.setString(4, user.getCin());
        statement.setDate(5, user.getDate_naissance());
        statement.setDate(6,date);
        statement.setBoolean(7, user.isStatus());
        statement.setInt(8, user.getRole().getId());
        statement.setString(9, user.getMot_pass());
        statement.setString(10, user.getEmail());
        
        statement.executeUpdate();
    } catch (SQLException ex) {
    }
}
     */
 /*
     @Override
    public void ajouter(User u) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        int stat = 0;
        if (u.isStatus()) {
            stat = 1;
        }
        try {
            // hadhage mot ppass
            String password = u.getMot_pass();
            String hashedPassword = hashPassword(password);
            u.setMot_pass(hashedPassword);
            String querry = "INSERT INTO `user` "
                    + "( `nom`, `prenom`, `adress`, `cin`, `date_naissance`, `date_creation_c`, `status`, `role`, `motPass`, `email`, `image`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = cnx.prepareStatement(querry);
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setString(3, u.getAdress());
            stmt.setString(4, u.getCin());
            stmt.setDate(5, u.getDate_naissance());
            stmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
            stmt.setBoolean(7, u.isStatus());
            stmt.setInt(8, u.getRole().getId());
            stmt.setString(9, u.getMot_pass());
            stmt.setString(10, u.getEmail());
            stmt.setBytes(11, u.getImage());
            stmt.executeUpdate();
            System.out.println("user ajoutee");
            //sddq
        } catch (SQLException ex) {
            System.out.println("Personne non ajouté");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
     */
    
   public List<User> getUsersByNom(String nom) {
    List<User> users = new ArrayList<>();
    try {
        String query = "SELECT * FROM `user` WHERE `nom`=?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, nom);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setAdress(rs.getString("adress"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getDate("date_naissance"));
            user.setDate_creation_c(rs.getDate("date_creation_c"));
            user.setStatus(rs.getInt("status") == 1);
            user.setRole(rs.getString("roles"));
            user.setMot_pass(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setToken(rs.getString("token"));
            user.setScore(rs.getString("score"));
            user.setNum(rs.getString("numtel"));
            user.setImage(rs.getString("image"));
            user.setCompteExpirationDate(rs.getDate("compte_ex"));
            user.setTokenExpirationDate(rs.getDate("token_ex"));
            users.add(user);
        }
    } catch (SQLException ex) {
        System.out.println("Failed to get users: " + ex.getMessage());
        // log or rethrow the exception
    }
    return users;
}


}
