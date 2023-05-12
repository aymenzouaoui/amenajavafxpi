/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import amena.utils.MyConnectionn ;

/**
 *
 * @author hp
 */
public class DocumentExpedition {
    private int id;
    private Date dateSignature;
    private Colis id_colis;
    private String statut;
    private String description;

    public DocumentExpedition(int id, Date dateSignature, Colis id_colis, String statut, String text) {
        this.id = id;
        this.dateSignature = Date.valueOf(LocalDate.now());
        this.id_colis = id_colis;
        this.statut = "non signé";
        this.description=text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public DocumentExpedition(int id, Date dateSignature, Colis id_colis, String statut) {
        this.id = id;
        this.dateSignature = Date.valueOf(LocalDate.now());
        this.id_colis = id_colis;
        this.statut = "non signé";
    }
        public DocumentExpedition(int id, Date dateSignature,String statut) {
        this.id = id;
        this.dateSignature = Date.valueOf(LocalDate.now());
        this.statut = statut;
    }
     public DocumentExpedition(int id,Colis id_colis, String statut) {
        this.id = id;
        this.id_colis = id_colis;
        this.statut = "non signé";
    }
     public DocumentExpedition(int id,String statut) {
        this.id = id;
        this.statut = "non signé";
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    public Colis getId_colis() {
        return id_colis;
    }

    public void setId_colis(Colis id_colis) {
        this.id_colis = id_colis;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

public DocumentExpedition ( ){
}

@Override
public String toString() {
        return "Document d'expedition{" + "id=" + id + ", DateSignature=" +dateSignature+ ", Status=" +statut+ "Id_Colis="+id_colis+"}/n";
    }
/*                 Trie Document  par critere souhaité            */
/*
public List<DocumentExpedition> trier(String critere) {
    Statement ste;
    Connection conn = MyConnectionn.getInstance().getConn();
    List<DocumentExpedition> documentsTrie = new ArrayList<>();
    try {
        String requeteSQL = "SELECT * FROM documentexpedition ORDER BY " + critere;
        PreparedStatement stmt = conn.prepareStatement(requeteSQL);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            DocumentExpedition d = new DocumentExpedition();
            d.setId(result.getInt("id"));
            d.setDateSignature(result.getDate("dateSignature"));
            d.setId_colis(result.getInt("colis_id"));
            d.setStatut(result.getString("statut"));
            documentsTrie.add(d);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return documentsTrie;
}*/
}

