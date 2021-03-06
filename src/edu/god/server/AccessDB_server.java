package edu.god.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
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
            //Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "root", "");
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
        String query = "SELECT id_Sim, description_LoanRef, date_Sim,percentage_rate,monthly_Sim, duration_Sim  FROM LoanSimulation "
                + "JOIN LoanRef on LoanRef.id_LoanRef=LoanSimulation.id_LoanRef "
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
        String query = "SELECT id_Sim,capital_Sim,amount_Sim,monthly_Sim,duration_Sim,date_Sim,statut_Sim,percentage_insurance, description_LoanRef,percentage_rate, id_Customer "
                + " FROM LoanSimulation NATURAL JOIN LoanRef WHERE id_Sim=? ;";
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
        String query = "UPDATE LoanSimulation set capital_Sim=?,amount_Sim=?,monthly_Sim=?, duration_Sim=?, date_Sim=?, statut_Sim=?, id_Consultant=?, percentage_insurance=? ,percentage_rate=?, id_LoanRef=? WHERE id_Sim=? ;";
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
     * @param data
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static int insertLoanSim(String[] data) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO LoanSimulation (capital_Sim,amount_Sim,monthly_Sim,duration_Sim,date_Sim,statut_Sim,id_Consultant,id_Customer,percentage_insurance,percentage_rate,id_LoanRef) VALUES (?,?,?,?,?,?,?,?,?,?,?) ; ";
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

    public static String getSalaryOfCustomer(Object[] data) throws ClassNotFoundException, SQLException {
        String query = "select salary_Customer From Customer where id_Customer=?;";
        System.out.println("methode getSalaryCustomer idCustomer = "+ data[0]);
        String res = null;
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, data[0].toString());
        try (ResultSet rs = queryPrep.executeQuery()) {
            if (rs.first()) {
                res = rs.getString("salary_Customer");
            }
            System.out.println("requete = " + queryPrep.toString());
            System.out.println(res);
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    public static String[] getTypeLoanCustomer(String idC) throws ClassNotFoundException, SQLException {
        String query = "SELECT Distinct description_LoanRef "
                + "FROM LoanRef Natural Join LoanSimulation "
                + "where id_Customer=? AND now( ) > DATE_SUB( now( ) , INTERVAL 6 MONTH );";
        String[] res = null;
        System.out.println("GetTypeLoanCustomer");
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        PreparedStatement queryPrep = con.prepareStatement(query);
        queryPrep.setString(1, idC);
        try (ResultSet rs = queryPrep.executeQuery()) {
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
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    public static JSONObject getSimulationsLoanOfCustomer(String[] data) throws ClassNotFoundException {
        String query = query = "select description_LoanRef,capital_Sim,percentage_Rate,percentage_Insurance,duration_Sim "
                + "From LoanRef Natural Join LoanSimulation where id_Customer=? AND description_LoanRef=? "
                + "AND now() > DATE_SUB(now(),INTERVAL 6 MONTH);";
        String[] res = null;
        JSONObject obj = new JSONObject();
        DecimalFormat df = new DecimalFormat("0.00");
        double monthlyInterestRate = 0, monthlyPayment = 0, annualPayment = 0;
        int cpt = 1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, data[0]);
            queryPrep.setString(2, data[1]);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount() + 3;

                    rs.beforeFirst();
                    while (rs.next()) {
                        String test[] = new String[nbColumn];
                        test[0] = Integer.toString(cpt);
                        test[1] = rs.getString(1);
                        test[2] = rs.getString(2);
                        test[3] = rs.getString(3);
                        monthlyInterestRate = Integer.parseInt(rs.getString(3)) / 12.0;
                        monthlyPayment = Integer.parseInt(rs.getString(2)) * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -Integer.parseInt(rs.getString(5))))) + Integer.parseInt(rs.getString(4));
                        test[4] = df.format(monthlyPayment).replace(",",".");
                        test[5] = rs.getString(4);
                        test[6] = rs.getString(5);

                        annualPayment = monthlyPayment * Double.parseDouble(rs.getString(5));
                        test[7] = df.format(annualPayment).replace(",",".");
                        System.out.println("mensualite = " + df.format(monthlyPayment).replace(",","."));
                        System.out.println("total = " + df.format(annualPayment).replace(",","."));
                        obj.put(rs.getRow(), Arrays.toString(test));
                        cpt++;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return obj;
    }

    /**
     *
     * @param idConsultant
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static String getIdAgency(String idConsultant) throws SQLException, ClassNotFoundException {
        String res = null;
        String query = "SELECT id_Agency FROM Consultant where id_Consultant=? ;";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, idConsultant);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            res = resultat.getString("id_Agency");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(res);
        return res;
    }

    public static String getIdPc(String idAgency) throws SQLException, ClassNotFoundException {
        String res = null;
        String query = "SELECT id_Rate_PC FROM Agency where id_Agency=? ;";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            res = resultat.getString("id_Rate_PC");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(res);
        return res;
    }

    public static float[] getRatePc(String idPc) {
        String query = "SELECT ParentRatesVehicles, ParentRatesConsumption ,ParentRatesPersonel, ParentRatesProperty, ParentRatesRepurchase, ParentRatesProject "
                + "FROM Rate_Parent_company where id_Rate_PC=? ;";
        float res[] = new float[6];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idPc);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res[0] = rs.getFloat(1);
                    res[1] = rs.getFloat(2);
                    res[2] = rs.getFloat(3);
                    res[3] = rs.getFloat(4);
                    res[4] = rs.getFloat(5);
                    res[5] = rs.getFloat(6);
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

    public static float[] getRateAg(String idAgency) {
        String query = "SELECT RatesConsumption , RatesProject , RatesPersonel , RatesProperty , RatesRepurchase , RatesVehicles "
                + "FROM Agency where id_Agency=? ; ";
        float res[] = new float[6];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.30.9:3306/god_banque", "god_banque", "God123Banque");
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idAgency);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res[0] = rs.getFloat(1);
                    res[1] = rs.getFloat(2);
                    res[2] = rs.getFloat(3);
                    res[3] = rs.getFloat(4);
                    res[4] = rs.getFloat(5);
                    res[5] = rs.getFloat(6);
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
