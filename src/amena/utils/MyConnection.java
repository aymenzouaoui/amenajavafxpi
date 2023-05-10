/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aymen
 */
public class MyConnection {
 
     final static String URL = "jdbc:mysql://127.0.0.1:3306/amena";
    final static String LOGIN = "root";
    final static String PWD = "";
    static MyConnection instance = null;
    private Connection cnx;

    private MyConnection() {

        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PWD);
            System.out.println("cnx etablie  ");
        } catch (SQLException e) {
            System.out.println("erreur de cnx ");

        }
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return cnx;
    }

}
