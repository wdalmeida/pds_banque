package edu.god.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Florian
 */
public class AccessDB_server {

    /**
     *
     * @param request Connects to the database and execute the query sent by an
     * other function. This makes UPDATE statements.
     */
    public static void sendUpdateRequest(String request) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/god_banque", "root", "");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            Statement stmt = con.createStatement();
            System.out.println("Soit la requete envoyee par le serveur " + request);
            stmt.executeUpdate(request);
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de l execution de la requete");
        }
    }

    /**
     *
     * @param request Connects to the database and execute the query sent by an
     * other function. This makes a SELECT query.
     * @return Returns the result of the request
     */
    public static String sendQueryRequest(String request) {
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
            System.out.println("res = " + resultat);
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de l execution de la requete");
        }
        return resultat;
    }

    public static ResultSet sendSearchRequest(String[] data) {
        ResultSet resultat = null;
        String query = "SELECT * FROM Customer WHERE last_Name_Customer =? AND first_Name_Customer=? ;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = con.prepareStatement(query);
            queryPrep.setString(1, data[0]);
            queryPrep.setString(2, data[1]);
            //  queryPrep.setString(3, data[2])
            ResultSet rs = queryPrep.executeQuery();
            if (rs.first()) {
              //  customers.add(rs.getRow());
                System.out.println(rs.getRow());
               resultat=rs;
            }
            System.out.println("res = " + resultat);
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de l execution de la requete");
        }
        return resultat;
    }
}
