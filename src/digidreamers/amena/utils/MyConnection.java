/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ahlem
 */
public class MyConnection {
    
   private static Connection conn; //DB Credations
    
String url = "jdbc:mysql://localhost:3306/amena";
String user = "root";
String pwd = "";
private static MyConnection instance;
    private MyConnection() {
        try {
            conn=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie <3");
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");        }
    }

    public static MyConnection getInstance() {
        if(instance==null){
            instance= new MyConnection();
        }
        return instance;
    }
 
    
    public Connection getConn(){
        return MyConnection.getInstance().conn;
    }
    
}
