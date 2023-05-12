/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.workshop.services;

/**
 *
 * @author hp
 */
import amena.gui.ProfilController;
import static amena.gui.ProfilController.semail;
import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import amena.interfaces.InterfaceCRUD;
import amena.model.User;
import amena.services.UserService;
import test.workshop.model.Colis;
import amena.utils.MyConnectionn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.util.Comparator;
import test.workshop.model.DocumentExpedition;

/**
 *
 * @author hp
 */
public class ColisCRUD implements InterfaceCRUD<Colis> {

    Statement ste;
    Connection conn = MyConnectionn.getInstance().getConn();

    @Override

    /* Fonction d'ajout dans la bd */
    public void ajouter(Colis c) {
        System.out.println(c.getDateExpedition());
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO colis (nomExpediteur, adresseExpediteur, nomDestinataire, adresseDestinataire, poids, statut, dateExpedition,id_u) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, c.getNomExpediteur());
            stmt.setString(2, c.getAdresseExpediteur());
            stmt.setString(3, c.getNomDestinataire());
            stmt.setString(4, c.getAdresseDestinataire());
            stmt.setFloat(5, c.getPoids());
            stmt.setString(6, c.getStatut());
            stmt.setObject(7, c.getDateExpedition());
            stmt.setInt(8, c.getUser().getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Insertion du colis a échoué, aucune ligne affectée.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    @Override
    public void supprimer(int id) {
        try {
            PreparedStatement statement1 = conn.prepareStatement("DELETE FROM documentexpedition WHERE id = ?");
            statement1.setInt(1, id);
            statement1.executeUpdate();
            String sql = "DELETE FROM colis WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Colis deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Colis colis) {
        try {
            String sql = "UPDATE colis SET nomExpediteur = ?, adresseExpediteur = ?, nomDestinataire = ?, adresseDestinataire = ?, poids = ?, statut = ?, dateExpedition = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, colis.getNomExpediteur());
            preparedStatement.setString(2, colis.getAdresseExpediteur());
            preparedStatement.setString(3, colis.getNomDestinataire());
            preparedStatement.setString(4, colis.getAdresseDestinataire());
            preparedStatement.setFloat(5, colis.getPoids());
            preparedStatement.setString(6, colis.getStatut());
            if (colis.getDateExpedition() != null) {
                preparedStatement.setDate(7, (colis.getDateExpedition()));
            } else {
                preparedStatement.setDate(7, null);
            }
            preparedStatement.setInt(8, colis.getId());
            preparedStatement.executeUpdate();
            System.out.println("Colis updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Colis> afficher() {
        List<Colis> list = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from colis");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Colis colis = new Colis(
                        resultSet.getInt("id"),
                        resultSet.getString("nomExpediteur"),
                        resultSet.getString("adresseExpediteur"),
                        resultSet.getString("nomDestinataire"),
                        resultSet.getString("adresseDestinataire"),
                        resultSet.getFloat("poids")
                );
                colis.setStatut(resultSet.getString("statut"));
                colis.setDateExpedition(resultSet.getDate("dateExpedition"));
                list.add(colis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
public List<Colis>listColisN() {
        List<Colis> list = new ArrayList<>();
        try {

            UserService u = new UserService();
            User a = u.getUserByEmai(semail);

            PreparedStatement statement = conn.prepareStatement("Select * from colis  where id_u='" + a.getId() + "' and status = 'en attente'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Colis colis = new Colis(
                        resultSet.getInt("id"),
                        resultSet.getString("nomExpediteur"),
                        resultSet.getString("adresseExpediteur"),
                        resultSet.getString("nomDestinataire"),
                        resultSet.getString("adresseDestinataire"),
                        resultSet.getFloat("poids")
                );

                colis.setStatut(resultSet.getString("statut"));
                colis.setDateExpedition(resultSet.getDate("dateExpedition"));

                list.add(colis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Colis> afficher3() {
        List<Colis> list = new ArrayList<>();
        try {

            UserService u = new UserService();
            User a = u.getUserByEmai(semail);

            PreparedStatement statement = conn.prepareStatement("Select * from colis  where id_u='" + a.getId() + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Colis colis = new Colis(
                        resultSet.getInt("id"),
                        resultSet.getString("nomExpediteur"),
                        resultSet.getString("adresseExpediteur"),
                        resultSet.getString("nomDestinataire"),
                        resultSet.getString("adresseDestinataire"),
                        resultSet.getFloat("poids")
                );

                colis.setStatut(resultSet.getString("statut"));
                colis.setDateExpedition(resultSet.getDate("dateExpedition"));

                list.add(colis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ColisCRUD() {
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Colis getByID(int id) {
        Colis colis = null;
        String query = "SELECT * FROM colis WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                colis = new Colis(
                        resultSet.getInt("id"),
                        resultSet.getString("nomExpediteur"),
                        resultSet.getString("adresseExpediteur"),
                        resultSet.getString("nomDestinataire"),
                        resultSet.getString("adresseDestinataire"),
                        resultSet.getFloat("poids")
                );
                colis.setStatut(resultSet.getString("statut"));
                colis.setDateExpedition(resultSet.getDate("dateExpedition"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colis;
    }

    public Colis getByIDu(int id) {
        Colis colis = null;
        String query = "SELECT * FROM colis WHERE id_u = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                colis = new Colis(
                        resultSet.getInt("id"),
                        resultSet.getString("nomExpediteur"),
                        resultSet.getString("adresseExpediteur"),
                        resultSet.getString("nomDestinataire"),
                        resultSet.getString("adresseDestinataire"),
                        resultSet.getFloat("poids")
                );
                colis.setStatut(resultSet.getString("statut"));
                colis.setDateExpedition(resultSet.getDate("dateExpedition"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colis;
    }

    public List<Colis> trierParDateExpedition() {
        List<Colis> colisTries = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM colis ORDER BY dateExpedition ASC");
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Colis c = new Colis();
                c.setId(result.getInt("id"));
                c.setNomExpediteur(result.getString("nomExpediteur"));
                c.setAdresseExpediteur(result.getString("adresseExpediteur"));
                c.setNomDestinataire(result.getString("nomDestinataire"));
                c.setAdresseDestinataire(result.getString("adresseDestinataire"));
                c.setPoids(result.getFloat("poids"));
                c.setStatut(result.getString("statut"));
                c.setDateExpedition(result.getDate("dateExpedition"));
                colisTries.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return colisTries;
    }

    public List<Colis> trier(String critere) {
        List<Colis> colisTrie = new ArrayList<>();
        try {
            String requeteSQL = "SELECT * FROM colis ORDER BY " + critere;
            PreparedStatement stmt = conn.prepareStatement(requeteSQL);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Colis c = new Colis();
                c.setId(result.getInt("id"));
                c.setNomExpediteur(result.getString("nomExpediteur"));
                c.setAdresseExpediteur(result.getString("adresseExpediteur"));
                c.setNomDestinataire(result.getString("nomDestinataire"));
                c.setAdresseDestinataire(result.getString("adresseDestinataire"));
                c.setPoids(result.getFloat("poids"));
                c.setStatut(result.getString("statut"));
                c.setDateExpedition(result.getDate("dateExpedition"));
                colisTrie.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return colisTrie;
    }

    public List<Colis> afficher2() {
        List<Colis> list = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from colis");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Colis colis = new Colis(
                        resultSet.getString("nomExpediteur"),
                        resultSet.getString("adresseExpediteur"),
                        resultSet.getString("nomDestinataire"),
                        resultSet.getString("adresseDestinataire"),
                        resultSet.getFloat("poids")
                );
                colis.setStatut(resultSet.getString("statut"));
                colis.setDateExpedition(resultSet.getDate("dateExpedition"));
                list.add(colis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Colis> filterByStatut(String statut) {
        List<Colis> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM colis WHERE statut = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, statut);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Colis c = new Colis();
                c.setId(rs.getInt(1));
                c.setNomExpediteur(rs.getString(2));
                c.setAdresseExpediteur(rs.getString(3));
                c.setNomDestinataire(rs.getString(4));
                c.setAdresseDestinataire(rs.getString(5));
                c.setPoids(rs.getFloat(6));
                c.setStatut(rs.getString(7));
                c.setDateExpedition(rs.getDate(8));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    /*public List<Colis> rechercher(double poids, LocalDate dateExp, String statut) {
List<Colis> result = new ArrayList<>();
String req = "SELECT * FROM colis WHERE poids = ? AND dateexpedition = ? AND statut = ?";
try {
PreparedStatement ps = conn.prepareStatement(req);

ps.setDouble(1, poids);
ps.setDate(2, java.sql.Date.valueOf(dateExp));
ps.setString(3, statut);
try (ResultSet resultSet = ps.executeQuery()) {
while (resultSet.next()) {
Colis colis = new Colis(
resultSet.getInt("id_Colis"),
resultSet.getString("nomexpediteur"),
resultSet.getString("adresseexpediteur"),
resultSet.getString("nomdestinataire"),
resultSet.getString("adressedestinataire"),
resultSet.getDouble("poids"),
resultSet.getString("statut"),
resultSet.getDate("dateExpedition").toLocalDate()
);
result.add(colis);
}
}
} catch (SQLException e) {
}
return result;
}*/
 /*public List<Colis> rechercher(float poids, LocalDate dateExp, String statut) {
List<Colis> result = new ArrayList<>();
String query = "SELECT * FROM colis WHERE ";
List<String> conditions = new ArrayList<>();
List<Object> parameters = new ArrayList<>();

if (poids != 0.0) {
conditions.add("poids = ?");
parameters.add(poids);
}

if (dateExp != null) {
conditions.add("dateexpedition = ?");
parameters.add(Date.valueOf(dateExp));
}

if (statut != null) {
conditions.add("statut = ?");
parameters.add(statut);
}

if (conditions.isEmpty()) {
// aucun critère de recherche n'a été spécifié
return result;
}

query += String.join(" AND ", conditions);

String req = "SELECT * FROM colis WHERE poids = ? AND dateexpedition = ? AND statut = ?";
try {
PreparedStatement ps = conn.prepareStatement(req);
int i = 1;
for (Object parameter : parameters) {
if (parameter instanceof Float) {
ps.setDouble(i, (Float) parameter);
} else if (parameter instanceof Date) {
ps.setDate(i, (Date) parameter);
} else if (parameter instanceof String) {
ps.setString(i, (String) parameter);
}
i++;
}
try (ResultSet resultSet = ps.executeQuery()) {
while (resultSet.next()) {
Colis colis = new Colis(
resultSet.getInt("id_Colis"),
resultSet.getString("nomexpediteur"),
resultSet.getString("adresseexpediteur"),
resultSet.getString("nomdestinataire"),
resultSet.getString("adressedestinataire"),
resultSet.getDouble("poids"),
resultSet.getString("statut"),
resultSet.getDate("dateexpedition").toLocalDate()
);
result.add(colis);
}
}
} catch (SQLException e) {
// gérer l'erreur de connexion à la base de données ou d'exécution de la requête
System.out.println(e.getMessage());
}
return result;
}*/
    public List<Colis> rechercher(float poids, LocalDate dateExp, String statut) {
        List<Colis> result = new ArrayList<>();
        String query = "SELECT * FROM colis WHERE ";
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (poids != 0.0f) { // Utiliser 0.0f pour un float
            conditions.add("poids = ?");
            parameters.add(poids);
        }

        if (dateExp != null) {
            conditions.add("dateexpedition = ?");
            parameters.add(Date.valueOf(dateExp));
        }

        if (statut != null) {
            conditions.add("statut = ?");
            parameters.add(statut);
        }

        if (conditions.isEmpty()) {
            // aucun critère de recherche n'a été spécifié
            return result;
        }

        query += String.join(" AND ", conditions);
        String req = "SELECT * FROM colis WHERE poids = ? AND dateexpedition = ? AND statut = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            int i = 1;
            for (Object parameter : parameters) {
                if (parameter instanceof Float) {
                    ps.setFloat(i, (Float) parameter); // Utiliser setFloat pour un float
                } else if (parameter instanceof Date) {
                    ps.setDate(i, (Date) parameter);
                } else if (parameter instanceof String) {
                    ps.setString(i, (String) parameter);
                }
                i++;
            }
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Colis colis = new Colis(
                            resultSet.getInt("id"),
                            resultSet.getString("nomexpediteur"),
                            resultSet.getString("adresseexpediteur"),
                            resultSet.getString("nomdestinataire"),
                            resultSet.getString("adressedestinataire"),
                            resultSet.getFloat("poids"), // Utiliser getFloat pour un float
                            resultSet.getString("statut"),
                            resultSet.getDate("dateexpedition").toLocalDate()
                    );
                    result.add(colis);
                }
            }
        } catch (SQLException e) {
// gérer l'erreur de connexion à la base de données ou d'exécution de la requête
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void modifier2(Colis colis) {
        try {/*
            UserService u = new UserService();
            User a = u.getUserByEmai("mohamed1@gmail.com");*/
            String sql = "UPDATE colis SET nomExpediteur = ?, adresseExpediteur = ?, nomDestinataire = ?, adresseDestinataire = ?, poids = ?, statut = ?, dateExpedition = ? WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, colis.getNomExpediteur());
            preparedStatement.setString(2, colis.getAdresseExpediteur());
            preparedStatement.setString(3, colis.getNomDestinataire());
            preparedStatement.setString(4, colis.getAdresseDestinataire());
            preparedStatement.setFloat(5, colis.getPoids());
            preparedStatement.setString(6, colis.getStatut());
            if (colis.getDateExpedition() != null) {
                preparedStatement.setDate(7, (colis.getDateExpedition()));
            } else {
                preparedStatement.setDate(7, null);
            }
            preparedStatement.setInt(8, colis.getId());
            preparedStatement.executeUpdate();
            System.out.println("Colis updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Colis> afficher4() {
        List<Colis> list = new ArrayList<>();
        try {
            UserService u = new UserService();
            User a = u.getUserByEmai(semail);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM colis JOIN colis_rec ON colis.id = colis_rec.id_c_id WHERE colis_rec.id_u_id='" + a.getId() + "'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Colis colis = new Colis(
                        resultSet.getInt("id"),
                        resultSet.getString("nomExpediteur"),
                        resultSet.getString("adresseExpediteur"),
                        resultSet.getString("nomDestinataire"),
                        resultSet.getString("adresseDestinataire"),
                        resultSet.getFloat("poids")
                );
                colis.setStatut(resultSet.getString("statut"));
                colis.setDateExpedition(resultSet.getDate("dateExpedition"));
                list.add(colis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
