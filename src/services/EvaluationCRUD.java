/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.InterfaceCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Annonce;
import model.Evaluation;
import amena.utils.MyConnectionn;



/**
 *
 * @author Nour Saidi
 */
public class EvaluationCRUD implements InterfaceCRUD <Evaluation> {
     
     Statement ste;
    Connection conn = MyConnectionn.getInstance().getConn();
    
   
      public void ajouterE( double note, Evaluation e) {
        try {
            String req = "INSERT INTO `evaluation`( `idClient`, `idTransporteur`, `note`) VALUES ('"+e.getIdClient()+"','"+e.getIdTransporteur()+"','"+note+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Evaluation ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Evaluation non ajouté");
                      }
 }

    @Override
    public void modifier(Evaluation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evaluation> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void ajouter(Evaluation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public double calculerScoreTransporteur(int idTransporteur) {
    double score = 0;
    int count = 0;

    try {
        // Récupérer toutes les évaluations associées à ce transporteur
        String query = "SELECT note FROM evaluation WHERE idTransporteur = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, idTransporteur);
        ResultSet resultSet = statement.executeQuery();

        // Calculer la moyenne des évaluations
        while (resultSet.next()) {
            score += resultSet.getDouble("note");
            count++;
        }

        if (count > 0) {
            score /= count;
        }

        // Mettre à jour l'attribut "score" dans la table utilisateur
        String updateQuery = "UPDATE user SET score = ? WHERE idTransporteur = ?";
        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        updateStatement.setDouble(1, score);
        updateStatement.setInt(2, idTransporteur);
        updateStatement.executeUpdate();

    } catch (SQLException ex) {
        System.out.println("Erreur lors du calcul du score du transporteur: " + ex.getMessage());
    }

    return score;
}

        
    }
    

