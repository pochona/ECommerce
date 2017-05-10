/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ameli
 */

public class connexionSimple {
    public static void main(String[] args) {      
        try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver O.K.");

        String url = "jdbc:mysql://localhost:3306/jee?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String passwd = "";

        Connection connec = DriverManager.getConnection(url, user, passwd);
        System.out.println("Connexion effective !");         

        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
}