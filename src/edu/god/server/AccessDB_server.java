package edu.god.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

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

    /**
     * Return customers by the last and first name and the postal code
     *
     * @param data
     * @return res ArrayList<String>
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static JSONObject getCustomer(String[] data) throws SQLException, ClassNotFoundException {
        String query = "SELECT id_Customer,title_Customer,last_Name_Customer,first_Name_Customer,street_Customer"
                + ",pc_Customer,city_Customer,phone_Customer,email_Customer,birthday_Customer,nationality_Customer"
                + " FROM Customer WHERE last_Name_Customer =? AND first_Name_Customer=? AND pc_Customer=? ; ";
        JSONObject obj = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = con.prepareStatement(query);
            queryPrep.setString(1, data[0]);
            queryPrep.setString(2, data[1]);
            queryPrep.setString(3, data[2]);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount();
                    rs.beforeFirst();
                    while (rs.next()) {
                        String test[] = new String[nbColumn];
                        for (int i = 0; i < nbColumn; i++) {
                            test[i] = rs.getString(i + 1);
                        }
                        obj.put(rs.getRow(), Arrays.toString(test));
                    }
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return obj;
    }

    public static int getUserConnexion(String[] data) throws ClassNotFoundException {
        int tmp = 0;
        String query = "SELECT id_User FROM User Natural Join Consultant where login_User=? AND pwd_User=? ;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = con.prepareStatement(query);
            queryPrep.setString(1, data[0]);
            queryPrep.setString(2, data[1]);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    tmp = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return tmp;
    }

    /**
     * Return the id, the type, the date, the rate,the monthly and the duration
     * for all the sim of the given customer
     *
     * @param idCustomer String
     * @return res ArrayList<String>
     * @throws java.lang.ClassNotFoundException
     */
    public static JSONObject getDateTypeSims(String idCustomer) throws ClassNotFoundException {
        String query = "SELECT id_Sim, description_LoanRef, date_Sim,percentage_Rate,monthly_Sim, duration_Sim  FROM LoanSimulation "
                + "JOIN LoanRef on LoanRef.id_LoanRef=LoanSimulation.id_LoanRef "
                + "JOIN Rate on Rate.id_Rate = LoanSimulation.id_Rate "
                + "WHERE id_Customer =? AND now() > DATE_SUB(now(), INTERVAL 6 MONTH); ";
        JSONObject obj = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = con.prepareStatement(query);
            queryPrep.setString(1, idCustomer);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount();
                    rs.beforeFirst();
                    while (rs.next()) {
                        String test[] = new String[nbColumn];
                        for (int i = 0; i < nbColumn; i++) {
                            test[i] = rs.getString(i + 1);
                        }
                        obj.put(rs.getRow(), Arrays.toString(test));
                    }
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return obj;
    }

    /**
     * Return the customer id with all the parameter required to insert him
     *
     * @param data
     * @return res String
     * @throws java.lang.ClassNotFoundException
     */
    public static String getIDCustomer(String data[]) throws ClassNotFoundException {
        String query = "SELECT id_Customer"
                + " FROM Customer WHERE title_Customer=? AND last_Name_Customer=? AND first_Name_Customer=? AND salary_Customer=? AND street_Customer=?"
                + " AND pc_Customer=? AND city_Customer=? AND phone_Customer=? AND email_Customer=? AND birthday_Customer=? AND nationality_Customer=? "
                + " AND nationality_Customer=? AND id_Consultant=? AND statut=? ; ";
        String res = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = con.prepareStatement(query);
            queryPrep.setString(1, data[0]);
            queryPrep.setString(2, data[1]);
            queryPrep.setString(3, data[2]);
            queryPrep.setString(4, data[3]);
            queryPrep.setString(5, data[4]);
            queryPrep.setString(6, data[5]);
            queryPrep.setString(7, data[6]);
            queryPrep.setString(8, data[7]);
            queryPrep.setString(9, data[8]);
            queryPrep.setString(10, data[9]);
            queryPrep.setString(11, data[10]);
            queryPrep.setString(12, data[11]);
            queryPrep.setString(13, data[12]);
            queryPrep.setString(15, data[13]);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res = rs.getString(1);
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    /**
     * Return the last and the first name of a customer
     *
     * @param idCustomer String
     * @return res Strin[]
     * @throws java.lang.ClassNotFoundException
     */
    public static String[] getLastFirstNameCustomer(String idCustomer) throws ClassNotFoundException {
        String query = "SELECT title_Customer, last_Name_Customer, first_Name_Customer"
                + " FROM Customer WHERE id_Customer =? ; ";
        String res[] = new String[3];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = con.prepareStatement(query);
            queryPrep.setString(1, idCustomer);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res[0] = rs.getString(1);
                    res[1] = rs.getString(2);
                    res[2] = rs.getString(3);
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    /**
     * Return all the type of loan
     *
     * @return res ArrayList<String>
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static String[] getLoanType() throws SQLException, ClassNotFoundException {
        String[] res = null;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT description_LoanRef FROM LoanRef ;");
        if (rs.first()) {
            rs.last();
            int nbRow = rs.getRow();
            rs.beforeFirst();
            int i = 0;
            res = new String[nbRow];
            while (rs.next()) {
                res[i] = rs.getString("description_LoanRef");

                i++;
            }
        }
        return res;
    }

    /**
     * Return all the simulation for a simulation
     *
     * @param idSim String
     * @return res ArrayList<String>
     */
    public static String[] getSimByID(String idSim) throws ClassNotFoundException {
        String query = "SELECT id_Sim,capital_Sim,amount_Sim,monthly_Sim,duration_Sim,date_Sim,statut_Sim,amount_Insurance, description_LoanRef,percentage_Rate, id_Customer "
                + " FROM LoanSimulation NATURAL JOIN LoanRef,Rate,Insurance WHERE id_Sim=? ;";
        String[] res = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idSim);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount();
                    res = new String[nbColumn];
                    for (int i = 0; i < nbColumn; i++) {
                        res[i] = rs.getString(i + 1);
                    }
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    /**
     * The method update a simulation in the table LoanSimulation with the given
     * parameter
     *
     * @param data
     * @return int
     * @throws SQLException
     */
    public static int updateLoanSim(Object[] data) throws SQLException, ClassNotFoundException {
        String query = "UPDATE LoanSimulation set capital_Sim=?,amount_Sim=?,monthly_Sim=?, duration_Sim=?, date_Sim=?, statut_Sim=?, id_Consultant=?, id_Insurance=? ,id_Rate=?, id_LoanRef=? WHERE id_Sim=? ;";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, data[0].toString());
        queryPrep.setString(2, data[1].toString());
        queryPrep.setString(3, data[2].toString());
        queryPrep.setString(4, data[3].toString());
        queryPrep.setString(5, data[4].toString());
        queryPrep.setString(6, data[5].toString());
        queryPrep.setLong(7, (long) data[6]);
        queryPrep.setString(8, data[7].toString());
        queryPrep.setString(9, data[8].toString());
        queryPrep.setString(10, data[9].toString());
        queryPrep.setString(11, data[10].toString());

        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();
    }

    /**
     * The method insert a new simulation in the database
     *
     * @param capital String
     * @param amount String
     * @param monthly String
     * @param duration String
     * @param date String
     * @param statut String
     * @param idConsultant int
     * @param idCustomer String
     * @param idInsurance String
     * @param idRate String
     * @param loanType String
     * @return
     * @throws SQLException
     */
    public static int insertLoanSim(String[] data) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO LoanSimulation (capital_Sim,amount_Sim,monthly_Sim,duration_Sim,date_Sim,statut_Sim,id_Consultant,id_Customer,id_Insurance,id_Rate,id_LoanRef) VALUES (?,?,?,?,?,?,?,?,?,?) ; ";
        System.out.println(query);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, data[0]);
        queryPrep.setString(2, data[1]);
        queryPrep.setString(3, data[2]);
        queryPrep.setString(4, data[3]);
        queryPrep.setString(5, data[4]);
        queryPrep.setString(6, data[5]);
        queryPrep.setString(7, data[6]);
        queryPrep.setString(8, data[7]);
        queryPrep.setString(9, data[8]);
        queryPrep.setString(10, data[9]);
        queryPrep.setString(11, data[10]);
        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();
    }

    /**
     * Return the id of the description
     *
     * @param selectedItem String
     * @return res String
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static String getIdLoanType(String selectedItem) throws SQLException, ClassNotFoundException {
        String res = null;
        String query = "SELECT id_LoanRef from LoanRef where description_LoanRef=? ;";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, selectedItem);
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            res = rs.getString("id_LoanRef");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(res);

        return res;
    }


    /**
     * Return the customer id which match with simulation
     *
     * @param id String id of a simulation
     * @return res String
     * @throws SQLException
     */
    public static String getIdCustInSim(String id) throws SQLException, ClassNotFoundException {
        String res = null;
        String query = "SELECT id_Customer from LoanSimulation where id_Sim=? ;";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, id);
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            res = rs.getString("id_Customer");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(res);
        return res;
    }
    
     /**
     * Return a table which contains the delimeter values for a simulation's
     * type
     *
     * @param idType String
     * @return res int[]
     */
    public static int[] getParambyID(String idType) {
        String query = "SELECT min_Amount_LoanRef, max_Amount_LoanRef, min_Duration_LoanRef, max_Duration_LoanRef"
                + " FROM LoanRef WHERE id_LoanRef =? ; ";
        int res[] = new int[4];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idType);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res[0] = rs.getInt(1);
                    res[1] = rs.getInt(2);
                    res[2] = rs.getInt(3);
                    res[3] = rs.getInt(4);
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccessDB_server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
