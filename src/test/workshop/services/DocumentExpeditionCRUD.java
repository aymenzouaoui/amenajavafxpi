/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.services;

import java.sql.Connection;
import amena.interfaces.InterfaceCRUD;
import amena.utils.MyConnectionn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import test.workshop.model.Colis;
import test.workshop.model.DocumentExpedition;

/**
 *
 * @author hp
 */
public class DocumentExpeditionCRUD implements InterfaceCRUD<DocumentExpedition> {
    private Connection conn = MyConnectionn.getInstance().getConn();

    @Override
    public void ajouter(DocumentExpedition d) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO documentexpedition (dateSignature, colis_id,description ,statut) VALUES (?,?,?,?)");
            stmt.setObject(1, d.getDateSignature());
            stmt.setInt(2, d.getId_colis().getId());
            stmt.setString(3, d.getDescription());
            stmt.setString(4, d.getStatut());
            stmt.executeUpdate();
            System.out.println("Document d'expédition ajouté");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String sql = "DELETE FROM documentexpedition WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Document d'expédition supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(DocumentExpedition d) {
        try {
            String sql = "UPDATE documentexpedition SET dateSignature = ?, colis_id = ?, statut = ?,description = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, d.getDateSignature());
            preparedStatement.setInt(2, d.getId_colis().getId());
            preparedStatement.setString(3, d.getStatut());
            preparedStatement.setString(4, d.getDescription());
            preparedStatement.setInt(5, d.getId());
            preparedStatement.executeUpdate();
            System.out.println("Document d'expédition modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<DocumentExpedition> afficher() {
        List<DocumentExpedition> list = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM documentexpedition");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DocumentExpedition d = new DocumentExpedition();
                d.setId(resultSet.getInt("id"));
                d.setDateSignature(resultSet.getDate("dateSignature"));
                d.setStatut(resultSet.getString("statut"));
                d.setDescription(resultSet.getString("description"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DocumentExpedition getByID(int id) {
    DocumentExpedition d = null;
    String query = "SELECT * FROM documentexpedition WHERE id = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            d = new DocumentExpedition();
            d.setId(resultSet.getInt("id"));
            d.setDateSignature(resultSet.getDate("dateSignature"));
            d.setDescription(resultSet.getString("description"));
            // Créer un objet Colis avec l'id correspondant
            int colisId = resultSet.getInt("colis_id");
            Colis colis = new Colis();
            colis.setId(colisId);
            
            d.setId_colis(colis);
            d.setStatut(resultSet.getString("statut"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return d;
}

    public List<DocumentExpedition> trierParDateExpedition() {
    List<DocumentExpedition> documentsTries = new ArrayList<>();
    try {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM documentexpedition ORDER BY dateExpedition ASC");
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            DocumentExpedition doc = new DocumentExpedition();
            doc.setId(result.getInt("id"));
            doc.setStatut(result.getString("statut"));
            int colisId = result.getInt("colis_id");
            Colis colis = new Colis();
            colis.setId(colisId);
            doc.setId_colis(colis);
            doc.setDateSignature(result.getDate("dateSignature"));
            documentsTries.add(doc);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return documentsTries;
}
public List<DocumentExpedition> trier(String critere) {
    List<DocumentExpedition> documentsTrie = new ArrayList<>();
    try {
        String requeteSQL = "SELECT * FROM documentexpedition ORDER BY " + critere;
        PreparedStatement stmt = conn.prepareStatement(requeteSQL);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            DocumentExpedition d = new DocumentExpedition();
            d.setId(result.getInt("id"));
            d.setDateSignature(result.getDate("dateSignature"));
            int colisId = result.getInt("colis_id");
                Colis colis = new Colis();
                colis.setId(colisId);
            
                d.setId_colis(colis);
            d.setStatut(result.getString("statut"));
            documentsTrie.add(d);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return documentsTrie;
}
    public List<DocumentExpedition> afficher2() {
        List<DocumentExpedition> list = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT dateSignature,colis_id,statut FROM documentexpedition");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DocumentExpedition d = new DocumentExpedition();
                d.setDateSignature(resultSet.getDate("dateSignature"));
                int colisId = resultSet.getInt("colis_id");
                Colis colis = new Colis();
                colis.setId(colisId);
            
                d.setId_colis(colis);
                d.setStatut(resultSet.getString("statut"));
                d.setDescription(resultSet.getString("description"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}