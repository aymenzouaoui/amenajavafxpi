/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.services;

import amena.interfaces.InterfaceCRUD;
import amena.model.Vehicule;
import amena.utils.MyConnectionn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author klair
 * @param <T>
 */
public class VehiculeCRUD implements InterfaceCRUD<Vehicule> {

    Statement ste;
    Connection conn = MyConnectionn.getInstance().getConn();

    
    public boolean verifMat(String mat)
    {
      List<String> list = new ArrayList<>();
        try {
            String req = "Select marque from vehicule where immat = '" + mat + "'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                list.add(RS.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        HashSet<String> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        if(list.size()!=0)
            return false ; 
        return true;    
    }
    
    public void ajouter(Vehicule v) {
        try {
            String req = "INSERT INTO `vehicule` (`type`, `immat`, `etat`, `kilometrage`, `chevaux`, `marque`, `modele`, `couleur`, `prix`,`img`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            System.out.println(v.getModele());
            ps.setString(1, v.getType());
            ps.setString(2, v.getImmat());
            ps.setBoolean(3, v.isEtat());
            ps.setString(4, v.getKilometrage());
            ps.setInt(5, v.getChevaux());
            ps.setString(6, v.getMarque());
            ps.setString(7, v.getModele());
            ps.setString(8, v.getCouleur());
            ps.setFloat(9, v.getPrix());
            ps.setString(10, v.getImg());

            ps.executeUpdate();
            System.out.println("Vehicule ajouté!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public VehiculeCRUD() {
    }
    
    public void modifier(Vehicule v) {
        int inetat;
        if (v.isEtat()) {
            inetat = 1;
        } else {
            inetat = 0;
        }
        try {
            String req = "UPDATE `vehicule` SET `type` = '" + v.getType() + "', `immat` = '" + v.getImmat() + "', `etat` = '" + inetat + "', `kilometrage` = '" + v.getKilometrage() + "', `chevaux` = '" + v.getChevaux() + "', `marque` = '" + v.getMarque() + "', `modele` = '" + v.getModele() + "', `couleur` = '" + v.getCouleur() + "', `prix` = '" + v.getPrix() + "', `img` = '" + v.getImg() + "' WHERE idV = " + v.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("vehicule updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier_etat(int id) {
  
        try {
            String req = "UPDATE `vehicule` SET `etat` = 0 WHERE idV = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("vehicule updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `vehicule` WHERE idV = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("vehicule deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Vehicule> afficher_nonReserve() {
        List<Vehicule> list = new ArrayList<>();
        try {
            String req = "Select * from vehicule";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                if (RS.getBoolean(4) == false) {
                    Vehicule v = new Vehicule();
                    v.setId(RS.getInt(1));
                    v.setType(RS.getString(2));
                    v.setImmat(RS.getString(3));
                    v.setEtat(RS.getBoolean(4));
                    v.setKilometrage(RS.getString(5));
                    v.setChevaux(RS.getInt(6));
                    v.setMarque(RS.getString(7));
                    v.setModele(RS.getString(8));
                    v.setCouleur(RS.getString(9));
                    v.setPrix(RS.getFloat(10));
                    v.setImg(RS.getString(11));
                    list.add(v);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Vehicule> afficher() {
        List<Vehicule> list = new ArrayList<>();
        try {
            String req = "Select * from vehicule";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Vehicule v = new Vehicule();
                v.setId(RS.getInt(1));
                v.setType(RS.getString(2));
                v.setImmat(RS.getString(3));
                v.setEtat(RS.getBoolean(4));
                v.setKilometrage(RS.getString(5));
                v.setChevaux(RS.getInt(6));
                v.setMarque(RS.getString(7));
                v.setModele(RS.getString(8));
                v.setCouleur(RS.getString(9));
                v.setPrix(RS.getFloat(10));
                v.setImg(RS.getString(11));
                list.add(v);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<String> affMar(String t) {
        List<String> list = new ArrayList<>();
        try {
            String req = "Select marque from vehicule where type = '" + t + "'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                list.add(RS.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        HashSet<String> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    public List<String> affMod(String t) {
        List<String> list = new ArrayList<>();
        try {
            String req = "Select modele from vehicule where marque = '" + t + "'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                list.add(RS.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     HashSet<String> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        return list;    }

    @Override
    public Vehicule getByID(int id) {
        Vehicule v = new Vehicule();
        try {
            String req = "Select * from vehicule where idV = " + id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                v.setId(RS.getInt(1));
                v.setType(RS.getString(2));
                v.setImmat(RS.getString(3));
                v.setEtat(RS.getBoolean(4));
                v.setKilometrage(RS.getString(5));
                v.setChevaux(RS.getInt(6));
                v.setMarque(RS.getString(7));
                v.setModele(RS.getString(8));
                v.setCouleur(RS.getString(9));
                v.setPrix(RS.getFloat(10));
                v.setImg(RS.getString(11));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return v;

    }

    public List<Vehicule> filterByEtat(int e) {
        List<Vehicule> list = new ArrayList<>();

        try {
            String req = "Select * from vehicule where etat = " + e;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Vehicule v = new Vehicule();
                v.setId(RS.getInt(1));
                v.setType(RS.getString(2));
                v.setImmat(RS.getString(3));
                v.setEtat(RS.getBoolean(4));
                v.setKilometrage(RS.getString(5));
                v.setChevaux(RS.getInt(6));
                v.setMarque(RS.getString(7));
                v.setModele(RS.getString(8));
                v.setCouleur(RS.getString(9));
                v.setPrix(RS.getFloat(10));
                       v.setImg(RS.getString(11));

                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

     public List<Vehicule> filterByMat(String mat) {
        List<Vehicule> list = new ArrayList<>();

        try {
            String req = "Select * from vehicule where immat = '" + mat +"'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Vehicule v = new Vehicule();
                v.setId(RS.getInt(1));
                v.setType(RS.getString(2));
                v.setImmat(RS.getString(3));
                v.setEtat(RS.getBoolean(4));
                v.setKilometrage(RS.getString(5));
                v.setChevaux(RS.getInt(6));
                v.setMarque(RS.getString(7));
                v.setModele(RS.getString(8));
                v.setCouleur(RS.getString(9));
                v.setPrix(RS.getFloat(10));
                v.setImg(RS.getString(11));

                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }
     
     public List<Vehicule> filterByMod(String mod) {
        List<Vehicule> list = new ArrayList<>();

        try {
            String req = "Select * from vehicule where modele = '" + mod +"'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Vehicule v = new Vehicule();
                v.setId(RS.getInt(1));
                v.setType(RS.getString(2));
                v.setImmat(RS.getString(3));
                v.setEtat(RS.getBoolean(4));
                v.setKilometrage(RS.getString(5));
                v.setChevaux(RS.getInt(6));
                v.setMarque(RS.getString(7));
                v.setModele(RS.getString(8));
                v.setCouleur(RS.getString(9));
                v.setPrix(RS.getFloat(10));
                v.setImg(RS.getString(11));

                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }
     public List<Vehicule> filterByType(String type) {
        List<Vehicule> list = new ArrayList<>();

        try {
            String req = "Select * from vehicule where type = '" + type +"'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Vehicule v = new Vehicule();
                v.setId(RS.getInt(1));
                v.setType(RS.getString(2));
                v.setImmat(RS.getString(3));
                v.setEtat(RS.getBoolean(4));
                v.setKilometrage(RS.getString(5));
                v.setChevaux(RS.getInt(6));
                v.setMarque(RS.getString(7));
                v.setModele(RS.getString(8));
                v.setCouleur(RS.getString(9));
                v.setPrix(RS.getFloat(10));
                v.setImg(RS.getString(11));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }
     public List<Vehicule> filterByMar(String mar) {
        List<Vehicule> list = new ArrayList<>();

        try {
            String req = "Select * from vehicule where marque = '" + mar +"'";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Vehicule v = new Vehicule();
                v.setId(RS.getInt(1));
                v.setType(RS.getString(2));
                v.setImmat(RS.getString(3));
                v.setEtat(RS.getBoolean(4));
                v.setKilometrage(RS.getString(5));
                v.setChevaux(RS.getInt(6));
                v.setMarque(RS.getString(7));
                v.setModele(RS.getString(8));
                v.setCouleur(RS.getString(9));
                v.setPrix(RS.getFloat(10));
                                v.setImg(RS.getString(11));

                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }
    
    public ArrayList<String> remplirmarque() throws MalformedURLException, ProtocolException, IOException {
        String url = "https://carapi.app/api/makes";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Extract make names from the response and add them to an ArrayList
        ArrayList<String> makeNames = new ArrayList<>();
        String responseStr = response.toString();
        Pattern pattern = Pattern.compile("\\{\"id\":\\d+,\"name\":\"(\\w+)\"");
        Matcher matcher = pattern.matcher(responseStr);
        while (matcher.find()) {
            String makeName = matcher.group(1);
            //   if(!remplirmod(makeName).isEmpty())
            makeNames.add(makeName);
        }
        return makeNames;
    }

    public ArrayList<String> remplirmod(String mod) {
        ArrayList<String> modelList = new ArrayList<String>();
        try {
            String url = "https://carapi.app/api/models?verbose=yes&year=2020&make=" + mod;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // Parse the response string to extract the model names
            String responseStr = response.toString();
            int startIndex = responseStr.indexOf("\"name\":");
            while (startIndex != -1) {
                startIndex += 8;
                int endIndex = responseStr.indexOf("\"", startIndex);
                String modelName = responseStr.substring(startIndex, endIndex);
                modelList.add(modelName);
                startIndex = responseStr.indexOf("\"name\":", endIndex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (modelList.isEmpty()) {
            modelList.add("vide");
        }
        return modelList;
    }

}
