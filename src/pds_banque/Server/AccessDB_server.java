package pds_banque.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccessDB_server {

    public static void envoyerRequeteUpdate(String request) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/god_banque", "root", "");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            Statement stmt = con.createStatement();
            System.out.println("Soit la requete envoyee par le serveur " +request);
             stmt.executeUpdate(request);
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de l execution de la requete");
        }
    }  
    
        public static String envoyerRequeteQuery(String request) {
        String resultat = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            
              while (rs.next()) {
                //System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                resultat = (rs.getString(1));
            }
            System.out.println("res = "+resultat);
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de l execution de la requete");
        }
        return resultat;
    }
    
}