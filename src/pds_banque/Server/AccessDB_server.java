package pds_banque.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class AccessDB_server {

    public static void insertionCustomer(String request) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/god_banque", "root", "");
            Statement stmt = con.createStatement();
            System.out.println(request);
             stmt.executeUpdate(request);
            con.close();

        } catch (Exception e) {
            System.out.println("Erreur lors de l execution de la requete");
        }
    }   
}